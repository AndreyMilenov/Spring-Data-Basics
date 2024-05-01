package org._07_bookshopsystem_spring_demo.data.service;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> findAllBooksAfterYear2000() throws IOException;

    List<String> findAllByBooksByGeorgePowellOrdered();
}
