package com.library.repositories;

import com.library.models.Book;
import com.library.models.Reader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    /**
     *
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
     */

    @Test
    public void testIfManyToManyAnnotationIsDoneCorrectly(){
        String username = "dimitar_kolev";
        Reader reader = repository.findByUsername(username);
        Set<Book> readBooks = reader.getReadBooks();
        for(Book book:readBooks){
            System.out.println(book.getTitle());
        }
        assertTrue(reader.getReadBooks().size() > 0);
    }

    //Works if the books is not already read
    @Test
    public void testIfReadBookWorksProperly(){
        String username="dimitar_kolev";
        Reader reader = repository.findByUsername(username);
        int size = reader.getReadBooks().size();
        System.out.println("Initial size is: " + size);
        repository.readBook(username,2L);
        reader = repository.findByUsername(username);
        assertTrue(size + 1 == reader.getReadBooks().size());
    }

    @Test
    public void testIfReadBookWorksProperlyWhenBookIsAlreadyRead(){
        String username="dimitar_kolev";
        Reader reader = repository.findByUsername(username);
        /**
         * Kind of replacement for the @Test(expected = ...)
         * since it wasn't working and could find the solution using
         * the @Rule annotation and ExpectedException
         */
        try {
            repository.readBook(username, 12L);
        }
        catch (DataIntegrityViolationException dive){
            assertTrue(true);
            return;
        }
        assertTrue(false);
    }
}
