package bg.sofia.uni.fmi.mjt.netflix.platform;

import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;

import java.util.stream.Stream;

public class IntegerStreamableArray {
    private IntegerStreamablePair[] pairs;
    private int elementsCount = 0;
    private int capacity;

    public IntegerStreamableArray() {
        capacity = 1000;
        this.pairs = new IntegerStreamablePair[capacity];
    }

    public IntegerStreamableArray(int capacity) {
        this.capacity = capacity;
        this.pairs = new IntegerStreamablePair[this.capacity];
    }

    public int getSize() {
        return this.elementsCount;
    }

    public void addStreamable(Streamable streamable) {
        if (contains(streamable)) {
            pairs[indexOf(streamable)].incrementFrequency();
        } else {
            pairs[elementsCount++] = new IntegerStreamablePair(streamable);
        }
    }

    public boolean contains(Streamable streamable) {
        if (elementsCount == 0) return false;
        for (IntegerStreamablePair pair : pairs) {
            if(pair == null){
                return false;
            }
            if (pair.getStreamable().getTitle().equals(streamable.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public Streamable getMostWatched(){
        if(pairs.length == 0) return null;
        Streamable streamable = null;
        int max = -1;
        for(int i=0;i<elementsCount;i++){
            if(pairs[i].getFrequency() > max){
                max = pairs[i].getFrequency();
                streamable = pairs[i].getStreamable();
            }
        }
        return streamable;
    }

    public IntegerStreamablePair[] toArray(){
        IntegerStreamablePair[] pairs = new IntegerStreamablePair[elementsCount];
        for(int i=0;i<elementsCount;i++){
            pairs[i] = this.pairs[i];
        }
        return pairs;
    }

    private int indexOf(Streamable streamable) {
        for (int i = 0; i < elementsCount; i++) {
            if (pairs[i].getStreamable().getTitle().equals(streamable.getTitle())) {
                return i;
            }
        }
        return -1;
    }

}
