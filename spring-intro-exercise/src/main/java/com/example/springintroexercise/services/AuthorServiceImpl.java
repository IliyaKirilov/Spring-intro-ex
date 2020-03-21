package com.example.springintroexercise.services;


import com.example.springintroexercise.GlobalConstants;
import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.repositoies.AuthorRepository;
import com.example.springintroexercise.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import static com.example.springintroexercise.GlobalConstants.*;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }


    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() != 0){
            return;
        }


        Arrays.stream(this.fileUtil.readFileContent(AUTHOR_FILE_PATH)).forEach(
                e-> {
                    String[] split = e.split("\\s+");
                    Author author = new Author(split[0], split[1]);
                    this.authorRepository.saveAndFlush(author);

                    }
        );


    }

    @Override
    public int getAllAuthorsCount() {
        return (int) this.authorRepository.count();
    }

    @Override
    public Author findAuthorById(long id) {
        return this.authorRepository.getOne(id);
    }
}
