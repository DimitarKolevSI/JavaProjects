package Shop;

import java.util.*;

public class Shop {
    private Set<Product> products = new TreeSet<Product>();

    public void addProduct(Product newProduct){
        if(newProduct == null){
            System.out.println("This product is null");
        }
        else products.add(newProduct);
    }

    public void removeProduct(Product product){
        if(product == null){
            System.out.println("This product is null");
        }
        else{
            if(!products.contains(product)){
                System.out.println("This product is not part of the store!");
            }
            else products.remove(product);
        }
    }

    public List<Product> getProductsAsList(){
        List<Product> productList = new ArrayList<>(products);
        return productList;
    }

    public void delivery(Map<Product,Integer> newProducts){
        for(Product product : newProducts.keySet()){
            if(product!=null && newProducts.get(product)!=null){
                product.increaseAmount(newProducts.get(product));
            }
        }
    }

    public void revision(){
        LinkedList<Product> products = new LinkedList<Product>(getProductsAsList());
        for(Product i: products){
            System.out.println(i);
        }
    }
}
