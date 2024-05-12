package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //printBooksByAgeRestriction(reader);
        //printBooksWhitGoldenEditionTypWhitLessThen5000Copies();
        //printAllBooksWhitPriceLowerThenAndPriceHigherThan();
        //notReleasedBooksInGivenYear(reader);
        //printAllByReleaseDateBefore(reader);
        //printAllAuthorsWhitFirstNameEndsWhit(reader);
        //printBooksTitlesByGivenString(reader);
        //printBookTitlesByAuthorFirstNameStartingWhit(reader);
        //findStatsForTitleLength(reader);
        //printTotalBookCopiesFromAuthor(reader);
        printBookProjection(reader);
    }

    private void printBookProjection(BufferedReader reader) throws IOException {
        String title = reader.readLine();
        BookInfo info = this.bookService.findInfoByTitle(title);
        System.out.println(info);
    }

    private void printTotalBookCopiesFromAuthor(BufferedReader reader) throws IOException {
        String[] authorNames = reader.readLine().split(" ");
        int count = authorService.getAuthorsAndAllBookCopies(authorNames[0],authorNames[1]);

        System.out.printf("%s %s %d%n",authorNames[0],authorNames[1],count);
    }

    private void findStatsForTitleLength(BufferedReader reader) throws IOException {
        int minLength = Integer.parseInt(reader.readLine());
        int count = bookService.findTitleCountLongerThen(minLength);

        System.out.printf("There are %d books with longer titles than %d symbols.\n", count, minLength);

    }

    private void printBookTitlesByAuthorFirstNameStartingWhit(BufferedReader reader) throws IOException {
        String string = reader.readLine();
        this.bookService.getAllBooksByGivenAuthorFirstNameStartingWith(string)
                .forEach(System.out::println);
    }

    private void printBooksTitlesByGivenString(BufferedReader reader) throws IOException {
        String string = reader.readLine();
        this.bookService.findAllBookTitlesByStringContaining(string)
                .forEach(System.out::println);
    }

    private void printAllAuthorsWhitFirstNameEndsWhit(BufferedReader reader) throws IOException {
        String string = reader.readLine();
        this.authorService.getAllAuthorsWhereFirstNameEndsWith(string)
                .forEach(System.out::println);
    }

    private void printAllByReleaseDateBefore(BufferedReader reader) throws IOException {
        String[] date = reader.readLine().split("-");
        int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int year = Integer.parseInt(date[2]);
        this.bookService.findAllByReleaseDateBeforeGivenDate(LocalDate.of(year,month,day))
                .forEach(System.out::println);


    }

    private void notReleasedBooksInGivenYear(BufferedReader reader) throws IOException {
        LocalDate today = LocalDate.of(Integer.parseInt(reader.readLine()), 1,1);
        List<String> allNotReleasedBooksByGivenYear = this.bookService.findAllNotReleasedBooksByGivenYear(today);
        allNotReleasedBooksByGivenYear.forEach(System.out::println);
    }

    private void printAllBooksWhitPriceLowerThenAndPriceHigherThan() {
        this.bookService.findAllByPriceLowerThanAndPriceHigherThan(BigDecimal.valueOf(5),BigDecimal.valueOf(40))
                .forEach(System.out::println);
    }

    private void printBooksWhitGoldenEditionTypWhitLessThen5000Copies() {
        List<String> copies = this.bookService.findAllByEditionAndCopies(EditionType.GOLD, 5000);
        copies.forEach(System.out::println);
    }

    private void printBooksByAgeRestriction(BufferedReader reader) throws IOException {
        String restriction = reader.readLine();

        try {
            AgeRestriction ageRestriction =
                    AgeRestriction.valueOf(restriction.toUpperCase());

            List<String> allByAgeRestriction = bookService.findAllByAgeRestriction(ageRestriction);
            allByAgeRestriction.forEach(System.out::println);

        }catch (IllegalArgumentException ex){
            System.out.println("Wrong age category");
            return;
        }


    }

    private void printAllBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
