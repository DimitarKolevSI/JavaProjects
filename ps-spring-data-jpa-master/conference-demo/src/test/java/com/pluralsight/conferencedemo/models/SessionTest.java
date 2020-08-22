package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.SessionJpaRepository;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SessionTest {
    @Autowired
    private SessionRepository repository;

    @Autowired
    private SessionJpaRepository jpaRepository;

    @Test
    public void test() throws Exception {
        List<Session> sessions = repository.getSessionsThatHaveName("Java");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaNot() throws Exception{
        List<Session> sessions = jpaRepository.findBySessionLengthNot(30);
        assertTrue(sessions.size()>0);
    }

    @Test
    public void testJpaNotLike() throws Exception{
        List<Session> sessions = jpaRepository.findBySessionNameNotLike("Java%");
        assertTrue(sessions.size()>0);
    }

    @Test
    public void testJpaLike() throws Exception{
        List<Session> sessions = jpaRepository.findBySessionNameLike("Java%");
        assertTrue(sessions.size()==0);
    }

    @Test
    public void testJpaStartsWith() throws Exception{
        List<Session> sessions = jpaRepository.findBySessionNameStartsWith("I");
        System.out.println(sessions.size());
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaEndingWith() throws Exception{
        List<Session> sessions = jpaRepository.findBySessionNameEndsWith("e");
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaLessThan() throws Exception{
        List<Session> sessions = jpaRepository.findBySessionLengthLessThan(45);
        assertTrue(sessions.size() > 0);
    }

    @Test
    public void testJpaGreaterThan() throws Exception{
        List<Session> sessions = jpaRepository.findBySessionLengthGreaterThan(30);
        assertTrue(sessions.size() > 0);
    }

}
