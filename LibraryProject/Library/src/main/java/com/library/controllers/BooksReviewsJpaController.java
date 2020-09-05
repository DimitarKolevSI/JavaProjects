package com.library.controllers;

import com.library.models.BooksReview;
import com.library.services.BooksReviewService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/review")
public class BooksReviewsJpaController {

    private BooksReviewService service;

    @Autowired
    public BooksReviewsJpaController(BooksReviewService service) {
        this.service = service;
    }

    @GetMapping(value = "get_all_sorted/{id}")
    public Set<BooksReview> getAllReviewsByBookIdOrderedByTimeDesc(@PathVariable("id")Long id){
        return service.getAllReviewsByBookOrderedByTimeDesc(id);
    }

    @PostMapping(value = "add_review/{username}/{books_id}/{review}")
    public ResponseEntity addReview(@PathVariable(name = "username") String username,
                             @PathVariable(name = "books_id") Long id,
                             @PathVariable(name = "review") String review){
        try{
            service.addReview(username, id, review);
        }
        catch (ConstraintViolationException | DataIntegrityViolationException dive){
            System.out.println("You have already made review to this book");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
