import Shop.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Product p = new Product(10, 30, 5, 4);
        Product product = new Product(10, 12.50, 10, 1);
        Product product1 = new Product(20, 10);
        Laptop acer = new Laptop(p, "Acer", "Intel i-7", 1000, 20, "S.th", "Nvidia MX150", 15.6, false);
        acer.increasePrice(1500);
        Shop shop = new Shop();
        shop.addProduct(acer);
        shop.addProduct(p);
        shop.revision();
    }
}
