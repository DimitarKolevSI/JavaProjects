package bg.sofia.uni.fmi.mjt.netflix.content;

public class Episode {
    private final String name;
    private final int duration;

    public Episode(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}
