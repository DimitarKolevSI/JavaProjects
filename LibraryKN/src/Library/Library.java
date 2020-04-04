package Library;

import com.sun.security.jgss.GSSUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Library {
    File booksRecord = new File("src/Library/Books.txt");
    File usersRecord = new File("src/Library/Users.txt");
    List<Book> Books = new LinkedList<Book>();
    List<User> Users = new LinkedList<User>();

    public Library() throws IOException {
        loadUsersFromFile();
    }

    public void logIn(String username, String password) throws FileNotFoundException {
        for(User user:Users){
            if(user.Username.equals(username)&& user.Password.equals(password)){
                if(!user.LoggedIn){
                    user.LoggedIn = true;
                    System.out.println("Welcome " + user.Username);
                }
                else
                    System.out.println("You are already logged in!");
            }
        }
    }

    public void logOut(String username){
        for(User user:Users){
            if(user.Username.equals(username)){
                if(user.LoggedIn){
                    user.LoggedIn = false;
                    System.out.println("Come back soon!");
                }
                else
                    System.out.println("You aren't logged in!");
            }
        }
    }

    protected void loadUsersFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(usersRecord);
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] info = line.split(" ");
            LevelOfAccess authority;
            if(info[2].equals("Administrator"))
                authority = LevelOfAccess.Administrator;
            else
                authority = LevelOfAccess.User;
            Users.add(new User(info[0],info[1],authority));
        }
    }

    protected void addNewUserToTheFile(User user) throws IOException {
        FileWriter fileWriter = new FileWriter(usersRecord,true);
        fileWriter.write(String.format("%s %s %s",user.Username,user.Password,user.levelOfAccess));
        fileWriter.close();
    }

    protected void addNewBookToTheFile(Book book) throws IOException {
        FileWriter fileWriter = new FileWriter(booksRecord,true);
        fileWriter.write(String.format("%s %s %s %s %d %f %d %d",book.getAuthor(),book.getTitle(),book.getGenre(),book.getResume(),book.getYear(),book.getRating(),book.getNumberOfRatings(),book.getID()));
        fileWriter.close();
    }
}
