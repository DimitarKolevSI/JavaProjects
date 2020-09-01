package com.library.repositories;

import com.library.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BookJpaRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    Book findByTitle(String title);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByGenre(String genre);

    List<Book> findByPagesLessThan(Integer pages);

    List<Book> findByPagesGreaterThan(Integer pages);

    List<Book> findAllByTitleContainingIgnoreCaseOrderByTitleAsc(String title);

    List<Book> findByTitleContainingOrderByYearPublishedDesc(String title);

    /**
     * Unnecessary methods left only for idea if it is needed
     *
     * @Transactional
     * @Modifying
     * @Query(value = "INSERT INTO books(title,author,pages,year_published,review,genre)" +
     * "VALUES (:title,:author,:pages,:year,:review,:genre)",nativeQuery = true)
     * void insertBookCustom(@Param("title")String title,@Param("author")String author,
     * @Param("pages")Integer pages,@Param("year")Integer yearPublished,
     * @Param("review")String review,@Param("genre")String genre);
     * @Transactional
     * @Modifying
     * @Query(value = "UPDATE books SET number_of_ratings = number_of_ratings + 1" +
     * " WHERE id = :id",nativeQuery = true)
     * void incrementNumberOfRatings(@Param("id")Long id);
     * @Transactional
     * @Modifying
     * @Query(value = "UPDATE books SET number_of_ratings = :number_of_ratings WHERE id = :id",nativeQuery = true)
     * void setNumberOfRatings(@Param("id")Long id,@Param("number_of_ratings") Integer numberOfRatings);
     * @Query(value = "SELECT number_of_ratings FROM books WHERE id = :id",nativeQuery = true)
     * Integer getNumberOfRatings(@Param("id")Long id);
     **/

    @Transactional
    @Modifying
    @Query(value = "UPDATE books SET rating = " +
            "((SELECT number_of_ratings * rating + :rating FROM books WHERE id = :id))/" +
            "((SELECT number_of_ratings FROM books WHERE id = :id) + 1)" +
            "WHERE id = :id"
            , nativeQuery = true)
    void rateABook(@Param("id") Long id, @Param("rating") Double rating);

    @Query(value = "SELECT DISTINCT genre FROM books", nativeQuery = true)
    List<String> getAllGenres();

}
