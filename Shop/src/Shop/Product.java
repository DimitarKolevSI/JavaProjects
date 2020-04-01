package Shop;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

enum Category{
    LAPTOP_AND_PC,
    COMPUTER_ACCESSORIES,
    BOOKS
}

public class Product implements Comparable {
    private static int IDHelper = 1000;
    private int ID;
    private int Amount;
    private double Price;
    private double Rating;
    private int NumberOfRatings;

    //Constructors
    public Product() {
        setID();
        setAmount(0);
        setPrice(0);
        setRating(0);
        setNumberOfRatings(0);
    }

    public Product(int Amount, double Price) {
        setID();
        setAmount(Amount);
        setPrice(Price);
        setRating(0);
        setNumberOfRatings(0);
    }

    public Product(int Amount, double Price, double Rating, int NumberOfRatings) {
        setID();
        setAmount(Amount);
        setPrice(Price);
        setRating(Rating);
        setNumberOfRatings(NumberOfRatings);
    }

    public Product(Product another){
        this.ID = another.ID;
        setAmount(another.Amount);
        setPrice(another.Price);
        setRating(another.Rating);
        setNumberOfRatings(another.NumberOfRatings);
    }

    //Setters

    private void setID() {
        this.ID = IDHelper;
        IDHelper++;
    }

    protected void setAmount(int amount) {
        Amount = amount;
    }

    protected void setPrice(double price) {
        Price = price;
    }

    protected void setRating(double rating) {
        Rating = rating;
    }

    protected void setNumberOfRatings(int numberOfRatings) {
        NumberOfRatings = numberOfRatings;
    }

    //Getters

    protected int getID() {
        return ID;
    }

    public int getAmount() {
        return Amount;
    }

    public double getPrice() {
        return Price;
    }

    public double getRating() {
        return Rating;
    }

    public int getNumberOfRatings() {
        return NumberOfRatings;
    }

    public void decreaseAmount(int Amount) throws IllegalArgumentException {
        try {
            if (Amount > this.getAmount() || Amount < 0) {
                throw new IllegalArgumentException("Invalid amount entered. Should be between 0 and the current amount.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            Amount = 0;
        } finally {
            setAmount(getAmount() - Amount);
        }
    }

    public void increaseAmount(int Amount) throws IllegalArgumentException {
        try {
            if (Amount < 0) {
                throw new IllegalArgumentException("Invalid amount entered! Amount should be positive number!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            Amount = 0;
        } finally {
            setAmount(getAmount() + Amount);
        }

    }

    public void rateTheProduct(double Rating) throws IllegalArgumentException {
        try {
            if (Rating < 0 || Rating > 10) {
                throw new IllegalArgumentException("Invalid rating entered. Should be between 0-10");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        double newRating = (getNumberOfRatings() * getRating() + Rating) / (getNumberOfRatings() + 1);
        setRating(newRating);
        NumberOfRatings++;
    }

    public void decreasePrice(double price) throws IllegalArgumentException {
        try {
            if (price < 0 || price > getPrice()) {
                throw new IllegalArgumentException("Invalid price entered! Should be between 0 and the current price!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            price = 0;
        }
        finally {
            setPrice(getPrice() - price);
        }

    }

    public void increasePrice(double price) throws IllegalArgumentException {
        try {
            if (price < 0) {
                throw new IllegalArgumentException("Invalid price entered! Should be positive number!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            price = 0;
        }
        finally {
            setPrice(getPrice() + price);
        }
    }

    public void decreasePriceWithPercent(double percent) throws IllegalArgumentException{
        try{
            if(percent < 0 || percent > 100){
                throw new IllegalArgumentException("Invalid percent entered! Should be between 0 and 100");
            }
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            percent = 0;
        }
        finally {
            double newPrice = this.getPrice() - (percent*this.getPrice())/100;
            setPrice(newPrice);
        }
    }

    @Override
    public String toString() {
        return "ID=" + ID +
                ", Amount=" + Amount +
                ", Price=" + Price +
                ", Rating=" + Rating +
                ", NumberOfRatings=" + NumberOfRatings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return ID == product.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public int compareTo(@NotNull Object o) throws IllegalArgumentException{
        if(!(o instanceof Product)){
            throw new IllegalArgumentException("This object is not an instance of Product!");
        }
        return this.ID - ((Product) o).ID;
    }
}
