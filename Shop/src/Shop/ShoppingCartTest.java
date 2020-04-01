package Shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShoppingCartTest {
    ShoppingCart sc;

    @Before
    public void setUp(){
        sc = new ShoppingCart();
    }

    @Test
    public void testForShoppingCartForBill(){
        //Given
        Product p = new Product(10,25);
        Product p1 = new Product(10, 10);

        //When
        double bill = sc.payAndEmptyTheCart();

        //Then
        assertEquals(0,bill,0.01);
        assertEquals(10,p.getAmount());
    }

    @Test
    public void testForShoppingCartAddWithBill(){
        //Given
        sc.add(null);
        sc.add(null);

        //When
        double bill = sc.payAndEmptyTheCart();

        //Then
        assertEquals(0,bill,0.01);
    }


}