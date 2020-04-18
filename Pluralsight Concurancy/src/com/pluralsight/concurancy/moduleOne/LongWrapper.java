package com.pluralsight.concurancy.moduleOne;

public class LongWrapper {
    /*
    This code causes race conditions
    private long l;

    public LongWrapper(long l) {
        this.l = l;
    }

    public long getValue() {
        return l;
    }

    public void incrementValue() {
        l = l + 1;
    }
    */

    private long l;
    //Create new object called key which we are going to use as a lock
    private Object key = new Object();

    public LongWrapper(long l) {
        this.l = l;
    }

    public long getValue() {
        return l;
    }

    public void incrementValue() {
        //synchronizing by this object to make sure that there will be no race conditions
        synchronized (key) {
            l = l + 1;
        }
    }
}
