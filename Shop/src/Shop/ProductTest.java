package Shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    Product p;
    Product p1;

    @Before
    public void setUp(){
        p = new Product(10,30);
        p1 = new Product(10,30);
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

    @Test
    public void TestForRatingProductWithNegativeNumber(){
        //Given
        int negativeRating = -10;

        //When
        p.rateTheProduct(negativeRating);

        //Then
        assertEquals(0,p.getRating(),0.01);
    }

    @Test
    public void TestForRatingProductWithLargerNumber(){
        //Given
        int largeRating = 50;

        //When
        p.rateTheProduct(largeRating);

        //Then
        assertEquals(0,p.getRating(),0.01);
    }

    @Test
    public void TestForRatingProductWithDecentNumber(){
        //Given
        double rating = 5;

        //When
        p.rateTheProduct(rating);

        //Then
        assertEquals(5,p.getRating(),0.01);
    }

    @Test
    public void TestForRatingTheProductWithMoreThanOneRating(){
        //Given
        double ratingOne = 5;
        double ratingTwo = 10;
        double ratingThree = 0;

        //When
        p.rateTheProduct(ratingOne);
        p.rateTheProduct(ratingTwo);
        p.rateTheProduct(ratingThree);

        //Then
        assertEquals(5, p.getRating(),0.01);
    }

    public void TestForIncreasingPriceWithNegativeNumber(){
        //Given
        int newPrice = -10;

        //When
        p.increasePrice(newPrice);
        double answer = p.getPrice();

        //Then
        assertEquals(30,answer,0.01);
    }

    @Test
    public void TestForIncreasingPriceWithValidNumber(){
        //Given
        int newPrice = 10;

        //When
        p.increasePrice(newPrice);

        //Then
        assertEquals(40, p.getPrice(),0.01);
    }

    @Test
    public void TestForDecreasingPriceWithNegativeNumber(){
        //Given
        int negativeNumber = -10;

        //When
        p.decreasePrice(negativeNumber);

        //Then
        assertEquals(30,p.getPrice(),0.01);
    }

    @Test
    public void TestForDecreasingPriceWithLargerNumber(){
        //Given
        int largeAmount = 50;

        //When
        p.decreasePrice(largeAmount);

        //Then
        assertEquals(30,p.getPrice(),0.01);
    }

    @Test
    public void TestForDecreasingPriceWithDecentNumber(){
        //Given
        int amount = 5;

        //When
        p.decreasePrice(amount);

        //Then
        assertEquals(25,p.getPrice(),0.01);
    }

    @Test
    public void TestForEqualsMethodWithAnotherProduct(){

        //Then
        assertNotEquals(p,p1);
    }

    @Test
    public void TestForEqualsMethodWithAnotherClass(){
        //Given
        String answer = "Answer";

        //Then
        assertNotEquals(p,answer);
    }

    @Test
    public void TestForPercentDiscount(){
        //Given
        double percent = 50;

        //When
        p.decreasePriceWithPercent(percent);

        //Then
        assertEquals(15,p.getPrice(),0.01);
    }

    @Test
    public void TestForPercentDiscountWithNegative(){
        //Given
        double percent = -50;

        //When
        p.decreasePriceWithPercent(percent);

        //Then
        assertEquals(30,p.getPrice(),0.01);
    }

    @Test
    public void TestForPercentDiscountWith25p(){
        //Given
        double percent = 25;

        //When
        p.decreasePriceWithPercent(percent);

        //Then
        assertEquals(22.5,p.getPrice(),0.01);
    }

}