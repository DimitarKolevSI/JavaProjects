package com.library.services;

import com.library.models.Book;
import com.library.repositories.BookJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookJpaServiceImpl implements BookService {

    private BookJpaRepository repository;

    @Autowired
    public BookJpaServiceImpl(BookJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    @Override
    public List<Book> findAllOrderByTitle(String title) {
        return repository.findAllByTitleContainingIgnoreCaseOrderByTitleAsc(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    @Override
    public Book findByTitle(String title) {
        return repository.findByTitle(title);
    }

    @Override
    public List<Book> findByTitleContainingIgnoreCase(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> findByGenre(String genre) {
        return repository.findByGenre(genre);
    }

    @Override
    public List<Book> findByPagesLessThan(Integer pages) {
        return repository.findByPagesLessThan(pages);
    }

    @Override
    public List<Book> findByPagesGreaterThan(Integer pages) {
        return repository.findByPagesGreaterThan(pages);
    }

    @Override
    public void insertBookCustom(String title, String author, Integer pages,
                                 Integer yearPublished, String review, String genre) {
        repository.insertBookCustom(title, author, pages, yearPublished, review, genre);
    }

    @Override
    public void incrementNumberOfRatings(Long id) {
        repository.incrementNumberOfRatings(id);
    }

    @Override
    public List<String> getAllGenres() {
        return repository.getAllGenres();
    }

    @Override
    public List<Book> getAllBooksOrderedByYearDesc(String title) {
        return repository.findByTitleContainingOrderByYearPublishedDesc(title);
    }
}
