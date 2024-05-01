package org._07_bookshopsystem_spring_demo.data.service;

import org._07_bookshopsystem_spring_demo.data.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthor() throws IOException;

    Author getRandomAuthor();

    List<String> getAllAuthorsFirstAndLastNameForBooksBeforeYear1990() throws IOException;

    List<String> getAllAuthorsDescBooks();
}
