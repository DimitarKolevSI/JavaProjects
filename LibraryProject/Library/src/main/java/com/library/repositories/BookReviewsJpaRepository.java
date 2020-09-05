package com.library.repositories;

import com.library.models.BooksReview;
import com.library.models.BooksReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface BookReviewsJpaRepository extends JpaRepository<BooksReview, BooksReviewKey> {

    @Query(value = "SELECT * FROM readers_review WHERE books_id = :id ORDER BY posted_date,posted_time DESC",
            nativeQuery = true)
    Set<BooksReview> getAllReviewsByBookIdOrderedByTimeDesc(@Param("id")Long id);

    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO readers_review(username, book_id, review) " +
                    "VALUES(:username,:books_id,:review)",
            nativeQuery = true
    )
    void addReview(@Param("username")String username, @Param("books_id")Long books_id, @Param("review")String review);

}
