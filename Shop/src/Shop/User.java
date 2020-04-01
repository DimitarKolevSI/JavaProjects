package Shop;

public class User {
    private static long IDHelper = 30000;
    protected long ID;
    private ShoppingCart shoppingCart;

    protected void setID() {
        ID = IDHelper;
        IDHelper++;
        shoppingCart = new ShoppingCart();
    }

    public long getID() {
        return ID;
    }

    public User() {
        setID();
    }

    public void addProductToTheCart(Product newProduct) {
        shoppingCart.add(newProduct);
    }

    public void removeProductFromTheCart(Product product) {
        if (product == null){
            System.out.println("The product is null!");
        }
        else shoppingCart.remove(product);
    }


}
