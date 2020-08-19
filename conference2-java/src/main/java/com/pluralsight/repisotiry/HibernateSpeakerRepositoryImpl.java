package com.pluralsight.repisotiry;

import com.pluralsight.model.Speaker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("speakerRepository")
public class HibernateSpeakerRepositoryImpl implements SpeakerRepository {

    private List<Speaker> speakers = new ArrayList<>();

    public List<Speaker> findAll()
    {
        addSpeaker(new Speaker("Dimitar","Kolev"));
        return speakers;
    }

    public void addSpeaker(Speaker speaker){
        speakers.add(speaker);
    }
}
