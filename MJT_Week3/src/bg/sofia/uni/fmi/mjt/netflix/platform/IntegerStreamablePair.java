package bg.sofia.uni.fmi.mjt.netflix.platform;

import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;

public class IntegerStreamablePair {
    private int frequency;
    private Streamable streamable;

    IntegerStreamablePair (Streamable streamable){
        this.streamable = streamable;
        this.frequency = 1;
    }

    public int getFrequency() {
        return frequency;
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public void incrementFrequency(){
        this.frequency++;
    }
}
