package org._07_bookshopsystem_spring_demo;

import org._07_bookshopsystem_spring_demo.data.service.AuthorService;
import org._07_bookshopsystem_spring_demo.data.service.BookService;
import org._07_bookshopsystem_spring_demo.data.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final AuthorService authorService;

    private final CategoryService categoryService;

    private final BookService bookService;

    public CommandLineRunnerImpl(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {


        printBooksByGeorgePowell();


        System.out.println("Started");
    }

    private void printBooksByGeorgePowell() {
        this.bookService.findAllByBooksByGeorgePowellOrdered()
                .forEach(System.out::println);
    }

    private void getAuthorsByBooksDesc() {
        List<String> authorsDescBooks = this.authorService.getAllAuthorsDescBooks();
        authorsDescBooks
                .forEach(System.out::println);

    }

    private void getAuthorsFirstAndLastNameBeforeBooks1990() throws IOException {
        this.authorService.getAllAuthorsFirstAndLastNameForBooksBeforeYear1990()
                .forEach(System.out::println);
    }

    private void getAllBooksAfter2000() throws IOException {
        this.bookService.findAllBooksAfterYear2000()
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        this.categoryService.seedCategories();
        this.authorService.seedAuthor();
        this.bookService.seedBooks();
    }
}
