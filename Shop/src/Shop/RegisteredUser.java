package Shop;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RegisteredUser extends User implements Comparable {
    private String Username = new String();
    private String Email = new String();
    private String Password = new String();
    private double Balance;
    private boolean logIn = false;
    protected boolean isItInSystem = false;

    public RegisteredUser() {
        super();
        Username = "";
        Email = "";
        Password = "";
        Balance = 0;
    }

    public RegisteredUser(String username, String email, String password, double balance) {
        super();
        Username = username;
        Email = email;
        Password = password;
        Balance = balance;
    }

    public RegisteredUser(@NotNull User oldUser, String username, String email, String password, double balance) {
        Username = username;
        Email = email;
        Password = password;
        Balance = balance;
        this.ID = oldUser.ID;
        this.shoppingCart = oldUser.shoppingCart;
    }

    public String getUsername() {
        return Username;
    }

    private void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    private void setEmail(String email) {
        Email = email;
    }

    private String getPassword() {
        return Password;
    }

    private void setPassword(String password) {
        Password = password;
    }

    public void logIn(@NotNull String password) {
        if (password.equals(Password) && !logIn)
            logIn = true;
        else
            System.out.println("Wrong password or you are already logged in!");
    }

    public void logOut() {
        logIn = false;
    }

    public boolean isLogIn() {
        return logIn;
    }

    public void changePassword(String oldPassword, String newPassword) {
        if (isLogIn()) {
            if (oldPassword.equals(Password))
                setPassword(newPassword);
            else
                System.out.println("Wrong password!");
        } else System.out.println("You must log in first!");
    }

    public void changeEmail(String newEmail) {
        if (isLogIn())
            setEmail(newEmail);
        else
            System.out.println("You must log in first");
    }

    public void changeUsername(String newUsername){
        if (isLogIn())
            setUsername(newUsername);
        else
            System.out.println("You must log in first");
    }

    public void increaseBalance(double newAmount) throws IllegalArgumentException {
        if(logIn && isItInSystem)
            System.out.println("You have to log in the system first in order to purchase");
        else {
            try {
                if (newAmount < 0) {
                    throw new IllegalArgumentException("The new amount can't be negative number!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                newAmount = 0;
            } finally {
                this.Balance += newAmount;
            }
        }
    }

    protected void decreaseBalance(double bill) throws IllegalArgumentException {
        if(logIn && isItInSystem)
            System.out.println("You have to log in the system first in order to purchase");
        else {
            if (bill < 0 || bill > Balance)
                throw new IllegalArgumentException("The bill can't be negative number nor a number larger than our balance");
            else Balance -= bill;
        }
    }

    public void purchase() {
        if(logIn && isItInSystem)
            System.out.println("You have to log in the system first in order to purchase");
        else {
            double bill = shoppingCart.calculateTheBill();
            if (bill > Balance)
                System.out.println("You don't have enough money in your balance. Please make a transaction.");
            else
                decreaseBalance(bill);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisteredUser that = (RegisteredUser) o;
        return Username.equals(that.Username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Username);
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "Username='" + Username + '\'' +
                ", Email='" + Email + '\'' +
                ", ID=" + ID +
                '}';
    }

    @Override
    public int compareTo(@NotNull Object o) throws IllegalArgumentException {
        if (!(o instanceof RegisteredUser)) {
            throw new IllegalArgumentException("This object is not a registered user!");
        }
        return (int)(this.ID - ((RegisteredUser) o).ID);
    }
}
