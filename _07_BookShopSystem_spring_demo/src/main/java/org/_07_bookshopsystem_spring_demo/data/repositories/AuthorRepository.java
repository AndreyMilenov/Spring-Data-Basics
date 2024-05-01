package org._07_bookshopsystem_spring_demo.data.repositories;

import org._07_bookshopsystem_spring_demo.data.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Set<Author> findAllByBooksReleaseDateBefore(LocalDate releaseDate);
@Query("FROM Author a ORDER BY SIZE(a.books) DESC")
    Set<Author> findAllByOrderByBooksDesc();
}
