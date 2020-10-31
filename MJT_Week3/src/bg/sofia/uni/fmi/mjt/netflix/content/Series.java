package bg.sofia.uni.fmi.mjt.netflix.content;

import bg.sofia.uni.fmi.mjt.netflix.content.enums.Genre;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;

public class Series implements Streamable {
    private final String name;
    private final Genre genre;
    private final PgRating pgRating;
    private Episode[] episodes;

    public Series(String name, Genre genre, PgRating pgRating, Episode[] episodes) {
        this.name = name;
        this.genre = genre;
        this.pgRating = pgRating;
        this.episodes = episodes;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public int getDuration() {
        int duration = 0;
        for(Episode e: episodes){
            duration += e.getDuration();
        }
        return duration;
    }

    @Override
    public PgRating getRating() {
        return this.pgRating;
    }
}
