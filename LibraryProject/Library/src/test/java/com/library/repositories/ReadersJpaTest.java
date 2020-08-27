package com.library.repositories;

import com.library.models.Book;
import com.library.models.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReadersJpaTest {
    @Autowired
    private ReaderJpaRepository repository;

    @Test
    public void testIfFindByUsernameWorks() {
        String username = "dimitar_kolev";
        String expectedFirstName = "Dimitar";
        Reader reader = repository.findByUsername(username);
        assertTrue(expectedFirstName.equals(reader.getFirstName()));
    }

    @Test
    public void testIfFindByUsernameWorksWithInvalidValue() {
        String username = "dimitar_kolev0911";
        Reader reader = repository.findByUsername(username);
        assertNull(reader);
    }

    @Test
    public void testIfGetPasswordByUsernameWorks() {
        String username = "dimitar_kolev";
        String expectedPassword = "m123456";
        String password = repository.getPasswordByUsername(username);
        assertTrue(expectedPassword.equals(password));
    }

    @Test
    public void testIfGetPasswordByUsernameWorksWithInvalidUsername(){
        String username = "dimitar_kolev0911";
        String password = repository.getPasswordByUsername(username);
        assertNull(password);
    }

    @Test
    public void testIfManyToManyAnnotationIsDoneCorrectly(){
        String username = "dimitar_kolev";
        Reader reader = repository.findByUsername(username);
        List<Book> readBooks = reader.getReadBooks();
        for(Book book:readBooks){
            System.out.println(book.getTitle());
        }
        assertTrue(reader.getReadBooks().size() > 0);
    }
}
