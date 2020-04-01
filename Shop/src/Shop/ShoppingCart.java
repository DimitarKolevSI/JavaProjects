package Shop;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    List<Product> products = new LinkedList<>();

    public void add(Product newProduct){
        if(newProduct == null){
            System.out.println("This product is null");
        }
        else if(newProduct.getAmount()==0){
            System.out.println("This product  is out of stock! Sorry!");
        }
        else products.add(newProduct);
    }

    public Object get(int index){
        if(index < 0 || index >= products.size()) return null;
        return products.get(index);
    }

    public void remove(Product product){
        if(product == null){
            System.out.println("This product is null!");
        }
        else products.remove(product);
    }

    public double payAndEmptyTheCart(){
        double bill = 0;
        if(products.isEmpty()){
            System.out.println("You have to add products first");
        }
        else {
            bill = calculateTheBill();
            products.clear();
        }
        return bill;
    }

    public double calculateTheBill(){
        double bill = 0;
        for(Product product:products){
            bill += product.getPrice();
        }
        return bill;
    }

    public void print(){
        for(Product product: products){
            System.out.println(product);
        }
    }
}
