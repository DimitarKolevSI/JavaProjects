package com.library.repositories;

import com.library.models.Book;
import com.library.models.BooksReview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookJpa {

    @Autowired
    BookJpaRepository repository;

    @Autowired
    BookReviewsJpaRepository reviewsJpaRepository;

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
    /*
    The tests works. Commenting them out for the sake of not adding rows to the database
    that we don't need.
    @Test
    public void testInsertingBook(){
        long oldCount = repository.count();
        Book book = new Book();
        book.setAuthor("Dan Brown");
        book.setTitle("Angels & Demon");
        book.setPages(616);
        book.setYearPublished(2000);
        book.setReview("Very impressive and interesting story.");
        book.setGenre("Thriller");
        book.setRating(0.0);
        book.setNumberOfRatings(0);
        book.setCoverUrl("http://4.bp.blogspot.com/_g3gjaai4a_0/TAhFLcAU7FI/AAAAAAAAAKY/4oHhAR-t-iQ/s1600/book%2Bclip%2Bart.jpg");
        repository.saveAndFlush(book);
        long newCount = repository.count();
        assertTrue(oldCount == newCount - 1);
    }

    @Test
    public void testCustomInsertingBook(){
        long oldCount = repository.count();
        Book book = new Book();
        book.setAuthor("Dan Brown");
        book.setTitle("Inferno");
        book.setPages(624);
        book.setYearPublished(2014);
        book.setReview("Very impressive and interesting story.");
        book.setGenre("Thriller");
        repository.insertBookCustom(book.getTitle(),book.getAuthor(),
                                    book.getPages(),book.getYearPublished(),
                                    book.getReview(),book.getGenre());
        long newCount = repository.count();
        assertTrue(oldCount == newCount - 1);
    }


    @Test
    public void testIfIncrementNumberOfRatingsWorks(){
        Book book = repository.findByTitle("It");
        int oldNumberOfRatings = book.getNumberOfRatings();
        repository.incrementNumberOfRatings(book.getId());
        assertTrue(repository.getNumberOfRatings(book.getId()) == oldNumberOfRatings + 1);
        repository.setNumberOfRatings(book.getId(),oldNumberOfRatings);
    }

    @Test
    public void testIfRateABookWorks(){
        Book book = repository.findByTitle("It");
        Long id = book.getId();
        repository.rateABook(id,1D);
        repository.incrementNumberOfRatings(id);
        book = repository.findByTitle("Origin");
        assertTrue(book.getRating().equals(8.2));
    }

    @Test
    public void randomTestForNow(){
        String title = "It";
        Book it = repository.findByTitle(title);
        assertTrue(it.getTitle().equals(title));
    }
    */

    @Test
    public void testIfFindAllGenresWorks(){
        List<String> genres = repository.getAllGenres();
        for(String genre:genres){
            System.out.println(genre);
        }
        assertTrue(genres.size()>0);
    }

    @Test
    public void testIfGetAllReviewsWorkProperlyWhenCalledFromBookRepository(){
        String title = "It";
        Set<BooksReview> reviews = repository.findByTitle(title).getReviews();
        assertTrue(reviews.size() > 0);
    }

    @Test
    public void testIfGetAllReviewsWorksWhenCalledFromReviewRepository(){
        Long id = 4L;
        List<BooksReview> reviews = reviewsJpaRepository.getAllReviewsByBookIdOrderedByTimeDesc(id);
        assertTrue(reviews.size() > 0);
    }
}
