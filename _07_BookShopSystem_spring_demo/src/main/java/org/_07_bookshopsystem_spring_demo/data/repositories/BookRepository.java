package org._07_bookshopsystem_spring_demo.data.repositories;

import org._07_bookshopsystem_spring_demo.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    Set<Book> findAllByReleaseDateIsAfter(LocalDate localDate);

    Set<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(String firstName, String lastName);
}
