package com.library.controllers;

import com.library.models.Book;
import com.library.repositories.BookJpaRepository;
import com.library.services.BookJpaServiceImpl;
import jdk.jshell.EvalException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookJpaController {
    private BookJpaServiceImpl service;

    @Autowired
    public BookJpaController(BookJpaServiceImpl service) {
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

    @PostMapping(value = "rate/{book_id}/{username}/{rating}")
    public ResponseEntity rateBook(@PathVariable("book_id")Long book_id,@PathVariable("username")String username,
                                   @PathVariable("rating")Double rating){
        try{
            service.rateBook(book_id, username, rating);
        }
        catch (ConstraintViolationException | DataIntegrityViolationException dive){
            System.out.println(dive.getMessage());
            System.out.println("You have already rated this book!");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);

    }
}
