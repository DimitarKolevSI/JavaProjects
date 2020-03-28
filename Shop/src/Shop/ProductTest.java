package Shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    Product p;

    @Before
    public void setUp(){
        p = new Product(10,30);
    }

    @Test
    public void TestForIncreasingAmountWithNegativeNumber(){
        //Given
        int newAmount = -10;

        //When
        p.increaseAmount(newAmount);
        int answer = p.getAmount();

        //Then
        assertEquals(10,answer);
    }

    @Test
    public void TestForIncreasingAmountWithValidNumber(){
        //Given
        int newAmount = 10;

        //When
        p.increaseAmount(newAmount);

        //Then
        assertEquals(20, p.getAmount());
    }

    @Test
    public void TestForDecreasingAmountWithNegativeNumber(){
        //Given
        int negativeNumber = -10;

        //When
        p.decreaseAmount(negativeNumber);

        //Then
        assertEquals(10,p.getAmount());
    }

    @Test
    public void TestForDecreasingAmountWithLargerNumber(){
        //Given
        int largeAmount = 50;

        //When
        p.decreaseAmount(largeAmount);

        //Then
        assertEquals(10,p.getAmount());
    }

    @Test
    public void TestForDecreasingAmountWithDecentNumber(){
        //Given
        int amount = 5;

        //When
        p.decreaseAmount(amount);

        //Then
        assertEquals(5,p.getAmount());

    }
}