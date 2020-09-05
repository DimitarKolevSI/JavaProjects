package com.library.models;

import javax.persistence.*;

@Entity
@Table(name = "readers_review")
public class BooksReview {

    @EmbeddedId
    BooksReviewKey id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    Reader reader;

    @ManyToOne
    @MapsId("booksId")
    @JoinColumn(name = "books_id")
    Book book;

    @Column(name = "review")
    String review;

    @Column(name = "posted_date")
    String postedDate;

    @Column(name = "posted_time")
    String postedTime;

    public BooksReview() {
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public BooksReviewKey getId() {
        return id;
    }

    public void setId(BooksReviewKey id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
