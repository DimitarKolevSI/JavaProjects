package com.library.services;

import com.library.models.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> findAllOrderByTitle(String title);
}
