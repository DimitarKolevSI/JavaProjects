package com.library.controllers;

import com.library.models.Book;
import com.library.repositories.BookJpaRepository;
import com.library.services.BookJpaServiceImpl;
import jdk.jshell.EvalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(value = "get_all_ordered")
    public List<Book> findAllOrderByTitle() {
        return service.findAllOrderByTitle("");
    }

    @GetMapping(value = "find_all_by_title_orderedAsc/{title}")
    public List<Book> findAllByTitleOrderedAsc(@PathVariable("title") String title){
        return service.findAllOrderByTitle(title);
    }

    @GetMapping(value = "search_by_author/{author}")
    public List<Book> findByAuthor(@PathVariable String author){
        return service.findByAuthor(author);
    }

    @GetMapping(value = "find_by_title/{title}")
    public Book findByTitle(@PathVariable("title")String title){
        return service.findByTitle(title);
    }

    @GetMapping(value = "find_by_title_cic/{title}")
    public List<Book> findByTitleContainsIC(@PathVariable(value = "title") String title){
        return service.findByTitleContainingIgnoreCase(title);
    }

    @GetMapping(value = "get_all_genres")
    public List<String> getAllGenres(){
        return service.getAllGenres();
    }

    @GetMapping(value = "get_all_by_year_desc")
    public List<Book> getAllBooksOrderedByYearDesc(){
        return service.getAllBooksOrderedByYearDesc("");
    }

    @GetMapping(value = "find_by_genre/{genre}")
    public List<Book> findByGenre(@PathVariable("genre")String genre){
        return service.findByGenre(genre);
    }
}
