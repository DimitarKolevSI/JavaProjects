package com.library.services;

import java.util.Set;
import com.library.models.Book;
import com.library.models.Reader;

public interface ReaderService {
    Reader findByUsername(String username);
    void readBook(String username,Long id);
    void addBook(Book book);
    Set<Book> getAllReadBooksByUser(String username);
    void addReader(Reader newReader);
}