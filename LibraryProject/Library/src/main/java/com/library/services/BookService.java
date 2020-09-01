package com.library.services;

import com.library.models.Book;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> findAllOrderByTitle(String title);

    List<Book> findByAuthor(String author);

    Book findByTitle(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByGenre(String genre);

    List<Book> findByPagesLessThan(Integer pages);

    List<Book> findByPagesGreaterThan(Integer pages);

    void insertBookCustom(String title, String author,
                          Integer pages, Integer yearPublished,
                          String review, String genre);

    void incrementNumberOfRatings(Long id);

    //void rateABook(Long id, Double rating);

    List<String> getAllGenres();

    List<Book> getAllBooksOrderedByYearDesc(String title);
}
