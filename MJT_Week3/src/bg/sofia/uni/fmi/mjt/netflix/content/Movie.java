package bg.sofia.uni.fmi.mjt.netflix.content;

import bg.sofia.uni.fmi.mjt.netflix.content.enums.Genre;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;

public class Movie implements Streamable {
    private final String name;
    private final Genre genre;
    private final PgRating pgRating;
    private final int duration;

    public Movie(String name, Genre genre, PgRating pgRating, int duration) {
        this.name = name;
        this.genre = genre;
        this.pgRating = pgRating;
        this.duration = duration;
    }

    @Override
    public String getTitle() {
        return this.name;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public PgRating getRating() {
        return this.pgRating;
    }
}
