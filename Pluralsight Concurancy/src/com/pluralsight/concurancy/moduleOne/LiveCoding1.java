package com.pluralsight.concurancy.moduleOne;

public class LiveCoding1 {
    public static void main (String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("I am running in " + Thread.currentThread().getName());
            }
        };

        Thread t = new Thread(runnable);
        //Setting the name of the thread to "My thread"
        t.setName("My thread");
        //Calling the start method of thread which calls the run function of the runnable object
        t.start(); //Outputs "I am running in My thread"
        t.run(); // Outputs "I am running in main" Don't use it!!!
    }
}
