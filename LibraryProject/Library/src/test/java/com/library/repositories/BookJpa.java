package com.library.repositories;

import com.library.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookJpa {

    @Autowired
    BookJpaRepository repository;

    @Test
    public void testIfFindingByAuthorWorksWithValidAuthor(){
        String author = "J.K.Rowling";
        List<Book> books = repository.findByAuthor(author);
        System.out.println(books.get(0).getTitle());
        assertTrue(books.size() > 0);
    }

    @Test
    public void testIfFindingByAuthorWorksWithInvalidAuthor(){
        String author = "J.K.Rowlings";
        List<Book> books = repository.findByAuthor(author);
        assertTrue(books.size() == 0);
    }

    @Test
    public void testIfFindByTitleWorksWithValidTitle(){
        String title="It";
        Book it = repository.findByTitle(title);
        assertNotNull(it);
    }

    @Test
    public void testIfFindByTitleWorksWithInvalidTitle(){
        String title="Its";
        Book it = repository.findByTitle(title);
        assertNull(it);
    }



}
