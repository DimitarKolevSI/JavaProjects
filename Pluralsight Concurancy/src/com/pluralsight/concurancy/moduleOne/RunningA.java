package com.pluralsight.concurancy.moduleOne;

public class RunningA {

    public static void main(String[] args) throws InterruptedException {

        //Trying to create deadlock
        A a = new A();

        //Standard declaring
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                a.a();
            }
        };

        //Declaring runnable object with lambda function
        Runnable r2 = ()->a.b();

        Thread t1 = new Thread(r1);
        t1.start();

        Thread t2 = new Thread(r2);
        t2.start();

        t1.join();
        t2.join();


    }
}
