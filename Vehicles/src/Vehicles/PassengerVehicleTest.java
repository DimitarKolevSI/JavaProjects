package Vehicles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PassengerVehicleTest {
    PassengerVehicle p;

    @Before
    public void setUp(){
        p = new PassengerVehicle(500,20);
    }

    @Test
    public void testOne(){
        p.pickPassengers(50);

        assertEquals(20,p.getPassengers());
    }

    @Test
    public void testTwo(){
        p.pickPassengers(10);

        System.out.println(p.getPassengers());

        assertEquals(10,p.dropPassengers(20));
    }

    @Test
    public void testThree(){
        p.pickPassengers(10);

        assertEquals(10,p.pickPassengers(20));
    }

    @Test
    public void testFour(){
        p.pickPassengers(30);

        assertEquals(20,p.getPassengers());
    }

    @Test
    public void testFive(){
        p.pickPassengers(20);
        p.dropPassengers(10);

        assertEquals(10,p.getPassengers());
    }

    @Test
    public void testSix(){
        p.pickPassengers(10);
        p.travel(100);

        assertEquals(120000,p.getAmortisation());
        assertEquals(100,p.getDistance());
    }


    @Test
    public void testSeven(){
        assertEquals(-1,p.dropPassengers(-20));
    }
}