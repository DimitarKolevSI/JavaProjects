package com.library.services;

import com.library.models.BooksReview;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface BooksReviewService {

    Set<BooksReview> getAllReviewsByBookOrderedByTimeDesc(Long id);

    void addReview(String username, Long books_id, String review);
}
