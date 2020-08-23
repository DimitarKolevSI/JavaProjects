package com.pluralsight.conferencedemo.models;

import com.pluralsight.conferencedemo.repositories.TicketTypeJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TicketTypeTest {
    @Autowired
    private TicketTypeJpaRepository jpaRepository;

    @Test
    public void testJpaTrue() throws Exception{
        List<TicketType> types = jpaRepository.findByIncludesWorkshopTrue();
        assertTrue(types.size() > 0);
    }

    @Test
    public void testJpaFalse() throws Exception{
        List<TicketType> types = jpaRepository.findByIncludesWorkshopFalse();
        assertTrue(types.size() > 0);
    }
}
