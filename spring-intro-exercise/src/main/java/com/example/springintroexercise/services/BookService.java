package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService  {
    void seedBooks() throws IOException;
    List<Book> getAllBooksAfter2000();
    List<Book> booksFromGeorgePowell();



}
