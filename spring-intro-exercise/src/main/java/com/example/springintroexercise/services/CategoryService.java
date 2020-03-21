package com.example.springintroexercise.services;

import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.entities.Category;

import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws IOException;

    int getAllCategoriesCount();
    Category findCategoryById(long id);

}
