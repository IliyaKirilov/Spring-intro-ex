package com.example.springintroexercise.controllers;

import com.example.springintroexercise.entities.Book;
import com.example.springintroexercise.services.AuthorService;
import com.example.springintroexercise.services.BookService;
import com.example.springintroexercise.services.CategoryService;
import com.example.springintroexercise.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import java.util.List;


@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final FileUtil fileUtil;
    private final BookService bookService;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, FileUtil fileUtil, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.fileUtil = fileUtil;
        this.bookService = bookService;
    }


    @Override
    public void run(String... args) throws Exception {


        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();


        //task1
        List<Book> allBooksAfter2000 = this.bookService.getAllBooksAfter2000();

        for (Book book : allBooksAfter2000) {
            System.out.println(String.format("%s %d",book.getTitle(),book.getCopies()));

        }

        List<Book> booksFromGeorgePowell = this.bookService.booksFromGeorgePowell();

        for (Book book : booksFromGeorgePowell) {

            System.out.println(String.format("%s %s %d",book.getTitle(),
                    book.getReleaseDate().toString(),book.getCopies()));
        }
    }
}
