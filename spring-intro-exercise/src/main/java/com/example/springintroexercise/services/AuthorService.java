package com.example.springintroexercise.services;


import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.repositoies.AuthorRepository;
import com.example.springintroexercise.repositoies.CategoryRepository;
import com.example.springintroexercise.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

public interface AuthorService {

    void seedAuthors() throws IOException;
    int getAllAuthorsCount();
    Author findAuthorById(long id);
}
