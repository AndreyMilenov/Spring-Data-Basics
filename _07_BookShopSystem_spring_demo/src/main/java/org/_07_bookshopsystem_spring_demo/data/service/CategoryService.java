package org._07_bookshopsystem_spring_demo.data.service;

import org._07_bookshopsystem_spring_demo.data.entities.Category;

import java.io.IOException;
import java.util.Set;

public interface CategoryService {
    void seedCategories() throws IOException;

    Set<Category> getRandomCategories();
}
