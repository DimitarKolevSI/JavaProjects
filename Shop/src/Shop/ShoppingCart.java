package Shop;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    List<Product> products = new LinkedList<>();

    public void add(Product newProduct){
        products.add(newProduct);
    }

    public Object get(int index){
        return products.get(index);
    }

    public void remove(Product product){
        products.remove(product);
    }

    public double payAndEmptyTheCart(){
        double bill = 0;
        for(int i = 0;i<products.size();i++){
            bill += products.get(i).getPrice();
        }
        products.clear();
        return bill;
    }
}
