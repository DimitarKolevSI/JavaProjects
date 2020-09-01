package com.library.services;

import com.library.models.Book;
import com.library.models.Reader;
import com.library.repositories.BookJpaRepository;
import com.library.repositories.ReaderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
public class ReaderJpaServiceImpl implements ReaderService {

    private ReaderJpaRepository readerJpaRepository;
    private BookJpaRepository bookJpaRepository;


    @Autowired
    public ReaderJpaServiceImpl(ReaderJpaRepository repository,
                                BookJpaRepository bookRepository) {
        this.readerJpaRepository = repository;
        this.bookJpaRepository = bookRepository;
    }

    @Override
    public Reader findByUsername(String username) {
        return readerJpaRepository.findByUsername(username);
    }

    @Override
    public void readBook(String username, Long id) {
        readerJpaRepository.readBook(username, id);
    }

    @Override
    public void addBook(Book book) {
        bookJpaRepository.saveAndFlush(book);
    }

    @Override
    public Set<Book> getAllReadBooksByUser(String username) {
        Reader reader = readerJpaRepository.findByUsername(username);
        if (reader == null) return Collections.emptySet();
        return reader.getReadBooks();
    }

    @Override
    public void addReader(Reader newReader) {
        readerJpaRepository.addReader(newReader.getUsername(), newReader.getPassword(),
                newReader.getFirstName(), newReader.getLastName(),
                newReader.getGender(), newReader.getEmail());
    }
}
