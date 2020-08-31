package com.library.controllers;

import com.library.models.Book;
import com.library.repositories.BookJpaRepository;
import com.library.services.BookJpaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    private BookJpaServiceImpl service;

    @Autowired
    public BookController(BookJpaServiceImpl service) {
        this.service = service;
    }

    @GetMapping(value = "get_all")
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }

    @GetMapping(value = "get_all_ordered")
    public List<Book> findAllOrderByTitle() {
        return service.findAllOrderByTitle("");
    }
}
