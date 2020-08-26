package com.library.repositories;

import com.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthor(String author);
    Book findByTitle(String title);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByGenre(String genre);
    List<Book> findByPagesLessThan(Integer pages);
    List<Book> findByPagesGreaterThan(Integer pages);
}
