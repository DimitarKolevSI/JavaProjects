import Shop.LaptopAndPcCategory;
import Shop.Product;

public class Main {

    public static void main(String[] args) {
        Product p = new Product(10,30,5,4);
        System.out.println(p);
        for(int i = 0;i<10;i++){
            Product p1 = new Product();
            System.out.println(p1);
        }
        Product l = new LaptopAndPcCategory();

    }
}
