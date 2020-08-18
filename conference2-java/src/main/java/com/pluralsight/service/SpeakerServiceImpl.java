package com.pluralsight.service;

import com.pluralsight.model.Speaker;
import com.pluralsight.repisotiry.HibernateSpeakerRepositoryImpl;
import com.pluralsight.repisotiry.SpeakerRepository;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository;

    public List<Speaker> findAll() {
        return repository.findAll();
    }

    public void setRepository(SpeakerRepository repository) {
        this.repository = repository;
    }
}
