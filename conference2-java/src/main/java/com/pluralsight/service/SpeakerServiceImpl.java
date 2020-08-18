package com.pluralsight.service;

import com.pluralsight.model.Speaker;
import com.pluralsight.repisotiry.HibernateSpeakerRepositoryImpl;
import com.pluralsight.repisotiry.SpeakerRepository;

import java.util.List;

public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository = new HibernateSpeakerRepositoryImpl();

    public List<Speaker> findAll()
    {
        return repository.findAll();
    }


}
