package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionJpaRepository extends JpaRepository<Session, Long> {
    /*
        The name of the method tells the DSL what kind of query we want using the Spring JPA.
        In this case with findBy we tell the parser that we want to search. After that with Session name
        we indicate that we want to search in the session_name column which sessionName in our model. And with Contains
        we choose the criteria on which we want to search
        Or in other word this method name is transformed in to SQL query which looks like this:
        SELECT * FROM SESSION S WHERE S.SESSION_NAME LIKE ?
    */
    List<Session> findBySessionNameContains(String name);

    /*
        Return types:
        If the query returns more than one row we use List<?>
        If we are sure that the query will return one object we use Session (for example)
        If we are counting something we use Long
     */

    List<Session> findBySessionLengthNot(Integer sessionLength);
    List<Session> findBySessionNameNotLike(String name);
    List<Session> findBySessionNameLike(String name);
    List<Session> findBySessionNameStartsWith(String name);
    List<Session> findBySessionNameEndsWith(String name);
    List<Session> findAll();
}
