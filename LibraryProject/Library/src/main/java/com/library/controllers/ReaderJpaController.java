package com.library.controllers;

import com.library.models.Book;
import com.library.models.Reader;
import com.library.services.ReaderJpaServiceImpl;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/reader")
public class ReaderJpaController {
    private ReaderJpaServiceImpl readerJpaService;

    @Autowired
    public ReaderJpaController(ReaderJpaServiceImpl readerJpaService) {
        this.readerJpaService = readerJpaService;
    }

    @GetMapping(value = "/user/{username}")
    public Reader findByUsername(@PathVariable String username){
        return readerJpaService.findByUsername(username);
    }

    @GetMapping(value = "read_books/{username}")
    public Set<Book> getAllBooksReadBy(@PathVariable String username){
        return readerJpaService.getAllReadBooksByUser(username);
    }

    @PostMapping(value = "add_book")
    @ResponseBody
    public ResponseEntity addBook(@RequestBody Book book){
        try {
            readerJpaService.addBook(book);
        }
        catch (ConstraintViolationException |DataIntegrityViolationException dive){
            System.out.println(dive.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "read_book/{username}/{id}")
    public void readBook(@PathVariable String username, @PathVariable Long id){
        readerJpaService.readBook(username,id);
    }
}
