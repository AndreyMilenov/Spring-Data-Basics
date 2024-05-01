package org._07_bookshopsystem_spring_demo.data.service.impl;


import org._07_bookshopsystem_spring_demo.data.entities.Author;
import org._07_bookshopsystem_spring_demo.data.repositories.AuthorRepository;
import org._07_bookshopsystem_spring_demo.data.service.AuthorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Override
    public List<String> getAllAuthorsDescBooks() {
        Set<Author> allByOrderByDesc = this.authorRepository.findAllByOrderByBooksDesc();
        return allByOrderByDesc
                .stream()
                .map(a ->String.format("%s %s %d",a.getFirstName(),a.getLastName(),a.getBooks().size()))
                .collect(Collectors.toList());
    }

    private static final String FILE_PATH = "src/main/resources/authors.txt";

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthor() throws IOException {
        if (this.authorRepository.count() == 0 ) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> row != null && !row.isEmpty())
                    .forEach(row -> {
                        String[] tokens = row.split("\\s+");
                        this.authorRepository.saveAndFlush(new Author(tokens[0], tokens[1]));
                    });
        }
    }

    @Override
    public Author getRandomAuthor() {
        int randomId = ThreadLocalRandom
                .current()
                .nextInt(1,(int)this.authorRepository.count() + 1);
        return this.authorRepository.findById(randomId).get();

    }

    @Override
    public List<String> getAllAuthorsFirstAndLastNameForBooksBeforeYear1990() throws IOException {
        return this.authorRepository.findAllByBooksReleaseDateBefore(LocalDate.of(1990,1,1))
                .stream()
                .map(author -> String.format("%s %s",author.getFirstName(),author.getLastName()))
                .collect(Collectors.toList());

    }

}
