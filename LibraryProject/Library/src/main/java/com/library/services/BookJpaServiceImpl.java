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
    public List<String> getAllGenres() {
        return repository.getAllGenres();
    }

    @Override
    public List<Book> getAllBooksOrderedByYearDesc(String title) {
        return repository.findByTitleContainingOrderByYearPublishedDesc(title);
    }

    @Override
    public void rateBook(Long id, String username, Double rating) {
        repository.addBookRating(id, username, rating);
        repository.updateNumberOfRatings(id);
        repository.updateRatings(id);
    }
}
