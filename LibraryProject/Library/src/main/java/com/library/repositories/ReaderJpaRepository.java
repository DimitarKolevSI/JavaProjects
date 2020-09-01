package com.library.repositories;

import com.library.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ReaderJpaRepository extends JpaRepository<Reader, String> {

    Reader findByUsername(String username);

    /**
     * @Query( value = "SELECT password FROM readers WHERE username LIKE :username",
     * nativeQuery = true
     * )
     * String getPasswordByUsername(String username);
     **/

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO read_books VALUES (:username,:book_id)",
            nativeQuery = true
    )
    void readBook(@Param("username") String username, @Param("book_id") Long book_id);

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO readers(username,password,first_name,last_name,gender,email)" +
                    "VALUES(:username,:password,:firstName," +
                    ":lastName,:gender,:email)",
            nativeQuery = true
    )
    void addReader(@Param("username") String username, @Param("password") String password,
                   @Param("firstName") String firstName, @Param("lastName") String lastName,
                   @Param("gender") char gender, @Param("email") String email);

}
