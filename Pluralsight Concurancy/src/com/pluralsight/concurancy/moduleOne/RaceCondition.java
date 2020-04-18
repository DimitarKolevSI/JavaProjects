package com.pluralsight.concurancy.moduleOne;

import javax.print.attribute.standard.RequestingUserName;

public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        //Example 1
        /*
        LongWrapper longWrapper = new LongWrapper(0L);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1_000;i++){
                    longWrapper.incrementValue();
                }
            }
        };

        Thread t = new Thread(r);
        t.start();

        t.join();

        System.out.println("Value = " + longWrapper.getValue());
        */

        //Example 2

        LongWrapper longWrapper = new LongWrapper(0L);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1_000;i++){
                    longWrapper.incrementValue();
                }
            }
        };

        Thread[]threads = new Thread[1_000];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(r);
            threads[i].start();
        }

        for(int i=0;i<threads.length;i++){
            threads[i].join();
        }

        System.out.println("Value = " + longWrapper.getValue());

    }
}
