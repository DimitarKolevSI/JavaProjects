package com.pluralsight.service;

import com.pluralsight.model.Speaker;
import com.pluralsight.repisotiry.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("speakerService")
public class SpeakerServiceImpl implements SpeakerService {

    private SpeakerRepository repository;

    public SpeakerServiceImpl(){
        System.out.println("In the SpeakerServiceImpl default constructor");
    }

    @Autowired
    public SpeakerServiceImpl (SpeakerRepository speakerRepository){
        System.out.println("In the SpeakerServiceImpl repository constructor");
        repository = speakerRepository;
    }

    public List<Speaker> findAll() {
        return repository.findAll();
    }

    public void setRepository(SpeakerRepository repository) {
        this.repository = repository;
        System.out.println("In the SpeakerServiceImpl setter");
    }
}
