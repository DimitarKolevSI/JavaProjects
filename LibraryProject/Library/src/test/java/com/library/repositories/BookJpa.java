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

    @Test
    public void testIfFindByTitleContainsWorksWithValidTitle(){
        String title="PET";
        List<Book> books = repository.findByTitleContainingIgnoreCase(title);
        assertTrue(books.size() > 0);
    }

    @Test
    public void testIfFindByTitleContainsWorksWithInvalidTitle(){
        String title="PETS";
        List<Book> books = repository.findByTitleContainingIgnoreCase(title);
        assertTrue(books.size() == 0);
    }

    @Test
    public void testIfFindByGenreContainsWorksWithValidGenre(){
        String genre="Fantasy";
        List<Book> books = repository.findByGenre(genre);
        assertTrue(books.size() > 0);
    }

    @Test
    public void testIfFindByGenreContainsWorksWithInvalidGenre(){
        String genre="Action";
        List<Book> books = repository.findByGenre(genre);
        assertTrue(books.size() == 0);
    }

    @Test
    public void testIfFindByPagesLessThanWorksWithValidPages(){
        Integer pages = 500;
        List<Book> books = repository.findByPagesLessThan(pages);
        assertTrue(books.size() > 0);
    }

    @Test
    public void testIfFindByPagesLessThanWorksWithInvalidPages(){
        Integer pages = 20;
        List<Book> books = repository.findByPagesLessThan(pages);
        assertTrue(books.size() == 0);
    }

    @Test
    public void testIfFindByPagesGreaterThanWorksWithValidPages(){
        Integer pages = 500;
        List<Book> books = repository.findByPagesGreaterThan(pages);
        assertTrue(books.size() > 0);
    }

    @Test
    public void testIfFindByPagesGreaterThanWorksWithInvalidPages(){
        Integer pages = 1138;
        List<Book> books = repository.findByPagesGreaterThan(pages);
        assertTrue(books.size() == 0);
    }




}
