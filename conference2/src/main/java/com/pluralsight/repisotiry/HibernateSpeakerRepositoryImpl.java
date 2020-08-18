package com.pluralsight.repisotiry;

import com.pluralsight.model.Speaker;

import java.util.ArrayList;
import java.util.List;

public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {

    public List<Speaker> findAll()
    {
        List<Speaker> speakers = new ArrayList<Speaker>();

        Speaker speaker = new Speaker();
        speaker.setFirstName("Dimitar");
        speaker.setLastName("Kolev");

        speakers.add(speaker);

        return speakers;
    }
}
