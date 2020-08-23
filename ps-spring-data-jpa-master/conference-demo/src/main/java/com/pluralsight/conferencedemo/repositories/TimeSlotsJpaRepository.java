package com.pluralsight.conferencedemo.repositories;

import com.pluralsight.conferencedemo.models.TimeSlots;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public interface TimeSlotsJpaRepository extends JpaRepository<TimeSlots,Long> {
    List<TimeSlots> findByStartTimeAfter(Time time);
    List<TimeSlots> findByTimeSlotDateAfter(Date date);
    List<TimeSlots> findByTimeSlotDateBefore(Date date);
}
