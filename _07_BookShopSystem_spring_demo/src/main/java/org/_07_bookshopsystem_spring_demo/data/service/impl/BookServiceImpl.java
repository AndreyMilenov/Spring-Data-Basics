package org._07_bookshopsystem_spring_demo.data.service.impl;

import org._07_bookshopsystem_spring_demo.data.entities.Author;
import org._07_bookshopsystem_spring_demo.data.entities.Book;
import org._07_bookshopsystem_spring_demo.data.entities.Category;
import org._07_bookshopsystem_spring_demo.data.entities.enums.AgeRestriction;
import org._07_bookshopsystem_spring_demo.data.entities.enums.EditionType;
import org._07_bookshopsystem_spring_demo.data.repositories.BookRepository;
import org._07_bookshopsystem_spring_demo.data.service.AuthorService;
import org._07_bookshopsystem_spring_demo.data.service.BookService;
import org._07_bookshopsystem_spring_demo.data.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public List<String> findAllByBooksByGeorgePowellOrdered() {
        return  this.bookRepository
                .findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle("George", "Powell")
                .stream()
                .map(b -> String.format("%s %s %d",b.getTitle(),b.getReleaseDate(),b.getCopies()))
                .collect(Collectors.toList());

    }

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private static final String FILE_PATH = "src/main/resources/books.txt";

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() == 0 ) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] data = row.split("\\s+");

                        Author author = this.authorService.getRandomAuthor();

                        EditionType editionType =

                                EditionType.values()[Integer.parseInt(data[0])];
                        LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                        int copies = Integer.parseInt(data[2]);
                        BigDecimal price = new BigDecimal(data[3]);
                        AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
                        String title = Arrays.stream(data).skip(5).collect(Collectors.joining(" "));
                        Set<Category> categories = this.categoryService.getRandomCategories();
                        Book book = new Book(title, editionType, price, copies, releaseDate, ageRestriction, author, categories);
                        this.bookRepository.saveAndFlush(book);
                    });
        }
    }
    @Override
    public List<String> findAllBooksAfterYear2000() throws IOException {
        return this.bookRepository.findAllByReleaseDateIsAfter(LocalDate.of(2000,12,31))
                .stream()
                .map(book -> String.format("%s %s",book.getTitle(),book.getReleaseDate()))
                .collect(Collectors.toList());
    }
}
