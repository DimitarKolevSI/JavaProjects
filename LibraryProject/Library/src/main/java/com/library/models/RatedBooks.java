package com.library.models;

import javax.persistence.*;

@Entity
@Table(name = "rated_book")
public class RatedBooks {

    @EmbeddedId
    RatedBooksKey id;

    @ManyToOne
    @MapsId("username")
    @JoinColumn(name = "username")
    Reader reader;

    @ManyToOne
    @MapsId("booksId")
    @JoinColumn(name = "books_id")
    Book book;

    @Column(name = "rating")
    Double rating;

    public RatedBooks() {
    }

    public RatedBooksKey getId() {
        return id;
    }

    public void setId(RatedBooksKey id) {
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
