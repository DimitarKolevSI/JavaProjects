package com.library.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BooksReviewKey implements Serializable {
    @Column(name = "username")
    String username;

    @Column(name = "books_id")
    Long booksId;

    public BooksReviewKey() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBooksId() {
        return booksId;
    }

    public void setBooksId(Long booksId) {
        this.booksId = booksId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksReviewKey that = (BooksReviewKey) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(booksId, that.booksId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, booksId);
    }
}
