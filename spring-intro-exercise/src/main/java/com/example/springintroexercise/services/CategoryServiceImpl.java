package com.example.springintroexercise.services;


import com.example.springintroexercise.GlobalConstants;
import com.example.springintroexercise.entities.Author;
import com.example.springintroexercise.entities.Category;
import com.example.springintroexercise.repositoies.CategoryRepository;
import com.example.springintroexercise.utils.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static com.example.springintroexercise.GlobalConstants.CATEGORIES_FILE_PATH;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    Random random;

    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.random = new Random();
    }


    @Override
    public void seedCategories() throws IOException {





        String [] fileContent = this.fileUtil.readFileContent(CATEGORIES_FILE_PATH);

        Arrays.stream(fileContent).forEach(e -> {
            Category category = new Category(e);

            this.categoryRepository.saveAndFlush(category);

        });

    }



    @Override
    public int getAllCategoriesCount() {
        return (int) this.categoryRepository.count();
    }

    @Override
    public Category findCategoryById(long id) {
        return this.categoryRepository.getOne(id);
    }


}
