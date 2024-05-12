package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.repository.BookInfo;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<String> findAllByEditionAndCopies(EditionType editionType, int i);

    List<String> findAllByPriceLowerThanAndPriceHigherThan(BigDecimal priceLower, BigDecimal priceHigher);

    List<String> findAllNotReleasedBooksByGivenYear(LocalDate today);

    List<String> findAllByReleaseDateBeforeGivenDate(LocalDate today);

    List<String> findAllBookTitlesByStringContaining(String string);

    List<String> getAllBooksByGivenAuthorFirstNameStartingWith(String firstName);

    int findTitleCountLongerThen(int minLength);

    BookInfo findInfoByTitle(String title);
}
