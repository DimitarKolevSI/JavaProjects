package com.pluralsight.repisotiry;

import com.pluralsight.model.Speaker;

import java.util.ArrayList;
import java.util.List;

public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {

    private List<Speaker> speakers = new ArrayList<>();

    public List<Speaker> findAll()
    {
        return speakers;
    }

    public void addSpeaker(Speaker speaker){
        speakers.add(speaker);
    }
}
