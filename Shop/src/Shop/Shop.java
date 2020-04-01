package Shop;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Shop {
    private Set<Product> products = new TreeSet<Product>();
    private Set<RegisteredUser> users = new TreeSet<RegisteredUser>();

    public void addProduct(Product newProduct) {
        if (newProduct == null) {
            System.out.println("This product is null");
        } else products.add(newProduct);
    }

    public void removeProduct(Product product) {
        if (product == null) {
            System.out.println("This product is null");
        } else {
            if (!products.contains(product)) {
                System.out.println("This product is not part of the store!");
            } else products.remove(product);
        }
    }

    public List<Product> getProductsAsList() {
        return new ArrayList<Product>(products);
    }

    public void delivery(Map<Product, Integer> newProducts) {
        for (Product product : newProducts.keySet()) {
            if (product != null && newProducts.get(product) != null) {
                if (products.contains(product)) product.increaseAmount(newProducts.get(product));
            }
        }
    }

    public void revision() {
        LinkedList<Product> products = new LinkedList<Product>(getProductsAsList());
        for (Product i : products) {
            System.out.println(i);
        }
    }

    public void registerAnUser(@NotNull User user, String Username, String Email, String Password) throws IllegalArgumentException{
        if(user == null || Username== null||Email== null|| Password== null)
            throw new IllegalArgumentException("One of the parameters was null");
        if (users.contains(user))
            System.out.println("This user is already in the system");
        else {
            RegisteredUser newUser = new RegisteredUser(user, Username, Email, Password, 0);
            newUser.isItInSystem = true;
            users.add(newUser);
        }
    }
}
