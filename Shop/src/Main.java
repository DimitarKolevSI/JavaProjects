import Shop.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Product p = new Product(10,30,5,4);
        System.out.println(p);
        for(int i = 0;i<10;i++){
            Product p1 = new Product();
            System.out.println(p1);
        }
        LaptopAndPcCategory lp = new LaptopAndPcCategory(p,null,"Intel",1000,20,"Board","Card");
        System.out.println(lp);
        Object laptop = "LaptopAndPcCategory";
        if(lp instanceof LaptopAndPcCategory){
            System.out.println("This is Laptop or PC.");
        }
        Laptop l = new Laptop(lp,15.6,false);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.add(p);
        shoppingCart.add(lp);
        shoppingCart.add(l);
        shoppingCart.add(null);
        for(int i = 0;i<3;i++){
            if(shoppingCart.get(i) instanceof Product){
                System.out.println("This is product!");
            }
            if(shoppingCart.get(i) instanceof LaptopAndPcCategory){
                System.out.println("This is LaptopAndPcCategory");
            }
            if(shoppingCart.get(i) instanceof Laptop){
                System.out.println("This is Laptop");
            }
        }
        System.out.println(shoppingCart.get(1));
    }
}
