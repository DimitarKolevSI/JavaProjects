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
        return repository.findAllByTitleContainingOrderByTitleAsc(title);
    }
}
