package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    @Query("FROM Book b WHERE b.price < :priceLower OR b.price > :priceUpper")
    List<Book> findAllByPriceLowerThanOrPriceUpperThan(BigDecimal priceLower, BigDecimal priceUpper);

    @Query("FROM Book b WHERE YEAR(b.releaseDate) != YEAR(:Year)")
    List<Book> findAllByReleaseDateIsNotIn(LocalDate Year);
    @Query("FROM Book b WHERE b.releaseDate < :localDate")
    List<Book> findAllByReleaseDateBeforeDate(LocalDate localDate);
    @Query("FROM Book b WHERE LOWER(b.title)  LIKE LOWER(concat( '%',:title ,'%'))")
    List<Book> findAllByTitleContains(@Param("title") String title);

    List<Book> findAllByAuthorLastNameStartingWith(String firstName);
    @Query("SELECT COUNT(b) FROM Book b WHERE LENGTH(b.title) > :number" )
    int countByTitleContains(int number);


    BookInfo findByTitle(String title);
}
