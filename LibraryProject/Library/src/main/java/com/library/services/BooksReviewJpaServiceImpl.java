package com.library.services;

import com.library.models.BooksReview;
import com.library.repositories.BookReviewsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BooksReviewJpaServiceImpl implements BooksReviewService {

    private BookReviewsJpaRepository repository;

    @Autowired
    public BooksReviewJpaServiceImpl(BookReviewsJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<BooksReview> getAllReviewsByBookOrderedByTimeDesc(Long id) {
        return repository.getAllReviewsByBookIdOrderedByTimeDesc(id);
    }

    @Override
    public void addReview(String username, Long books_id, String review) {
        repository.addReview(username, books_id, review);
    }
}
