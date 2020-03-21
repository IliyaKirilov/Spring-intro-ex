package com.example.springintroexercise.services;


import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.entities.Book;
import com.example.springintroexercise.entities.Category;
import com.example.springintroexercise.enums.AgeRestriction;
import com.example.springintroexercise.enums.EditionType;
import com.example.springintroexercise.repositoies.BookRepository;
import com.example.springintroexercise.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.example.springintroexercise.GlobalConstants.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private Random random;
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final FileUtil fileUtil;
    private final CategoryService categoryService;


    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, FileUtil fileUtil, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.fileUtil = fileUtil;
        this.categoryService = categoryService;
        this.random = new Random();
    }


    @Override
    public void seedBooks() throws IOException {

        if (this.bookRepository.count() != 0) {
            return;
        }


        String[] data = this.fileUtil.readFileContent(BOOKS_FILE_PATH);

        Arrays.stream(data).forEach(e -> {
            String[] tokens = e.split("\\s+");

            Author author = this.getRandomAuthor();
            EditionType editionType = EditionType.values()[Integer
                    .parseInt(tokens[0])];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

            LocalDate releaseDate =
            LocalDate.parse(tokens[1], formatter);

            int copies = Integer.parseInt(tokens[2]);

            BigDecimal price = new BigDecimal(tokens[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(tokens[4])];


            String title = getTitleByTokens(tokens);


            Book book = new Book();

            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);


            Set<Category> categories = this.getRandomCategories();

            book.setCategories(categories);


            this.bookRepository.saveAndFlush(book);

        });


    }

    @Override
    public List<Book> getAllBooksAfter2000() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        LocalDate releaseDate =
                LocalDate.parse("31/12/2000", formatter);

        return this.bookRepository.findAllByReleaseDateAfter(releaseDate);

    }

    @Override
    public List<Book> booksFromGeorgePowell() {
        return this.bookRepository.booksFromGeorgePowell();
    }

    private String getTitleByTokens(String[] params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 5; i < params.length; i++) {
            sb.append(params[i]).append(" ");
        }




        return sb.toString().trim();



    }

    private Set<Category> getRandomCategories() {
        int randomNumber = this.random.nextInt(this.categoryService.getAllCategoriesCount());


        Set<Category> categories = new LinkedHashSet<>();


        for (int i = 1; i <= randomNumber ; i++) {
            categories.add(this.categoryService.findCategoryById(i));
        }

        return categories;
    }

    private Author getRandomAuthor() {

        int randomId = this.random.nextInt(this.authorService.getAllAuthorsCount()) + 1;
        return this.authorService.findAuthorById(randomId);

    }
}
