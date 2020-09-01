package com.library.services;

import com.library.models.Book;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService {
    List<Book> findAllOrderByTitle(String title);

    List<Book> findByAuthor(String author);

    Book findByTitle(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByGenre(String genre);

    //void rateABook(Long id, Double rating);

    List<String> getAllGenres();

    List<Book> getAllBooksOrderedByYearDesc(String title);
}
