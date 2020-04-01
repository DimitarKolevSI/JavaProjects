package Vehicles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CargoVehicleTest {
    CargoVehicle cv;

    @Before
    public void setUp() {
        cv = new CargoVehicle(500,600);
    }

    @Test
    public void TestOne(){
        assertFalse(cv.loadCargo(1000));
    }

    @Test
    public void TestTwo(){
        assertTrue(cv.loadCargo(100));
    }

    @Test
    public void TestThree(){
        cv.loadCargo(200);

        assertFalse(cv.unloadCargo(300));
    }

    @Test
    public void TestFour(){
        cv.loadCargo(300);

        assertTrue(cv.unloadCargo(100));
    }

    @Test
    public void TestFive(){
        cv.loadCargo(100);

        assertEquals(100,cv.getCargo());
    }

    @Test
    public void TestSix(){
        cv.loadCargo(100);
        cv.travel(100);

        assertEquals(60000,cv.getAmortisation());
        assertEquals(100,cv.getDistance());
    }
}