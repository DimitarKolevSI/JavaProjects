package com.library.repositories;

import com.library.models.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReaderJpaRepository extends JpaRepository<Reader,String> {
    Reader findByUsername(String username);
    /*
    @Query(
            value = "SELECT password FROM readers WHERE username LIKE :username",
            nativeQuery = true;
    )*/
    String getPasswordByUsername(String username);
}
