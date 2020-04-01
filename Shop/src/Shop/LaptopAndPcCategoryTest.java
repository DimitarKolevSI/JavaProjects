package Shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LaptopAndPcCategoryTest {
    LaptopAndPcCategory lp;

    @Before
    public void setUp(){
        lp = new LaptopAndPcCategory(20,50,10,1,"lp","lp",100,2,"lp","lp");
    }

    @Test
    public void TestForCopyConstructor(){
        //Given
        LaptopAndPcCategory l1;

        //When
        l1 = new LaptopAndPcCategory(lp);

        //Then
        assertEquals(l1.getPrice(),lp.getPrice(),0.01);
    }
}