package Main;

import Library.Book;
import Library.Library;
import Library.LevelOfAccess;
import Library.User;

import javax.naming.AuthenticationNotSupportedException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException, AuthenticationNotSupportedException {
        /*File booksRecord = new File("src/Library/Books.txt");
        if(booksRecord.createNewFile()) System.out.println("New file was created!");
        else System.out.println("The file is already created");
        try{

            Scanner scanner = new Scanner(booksRecord);
            System.out.println("Is the file empty: " + !scanner.hasNextLine());
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
            scanner.close();
            FileWriter writer = new FileWriter(booksRecord,true);
            writer.write("Newer text\n");
            writer.write("Newer line\n");
            writer.close();
            scanner = new Scanner(booksRecord);
            System.out.println("Is the file empty: " + !scanner.hasNextLine());
            /*while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.contains("text")) System.out.println(line);
            }
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }*/
        Scanner scanner = new Scanner(System.in);
        Library l = new Library();
        User currentUser = null;
        String command;
        System.out.print("Type a command: ");
        command = scanner.nextLine();
        while(!command.toLowerCase().equals("exit")) {
            String[] commands = command.split(" ");
            if (commands[0].toLowerCase().equals("login")) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                currentUser = l.logIn(username, password);
            } else if (commands[0].toLowerCase().equals("logout")) {
                l.logOut(currentUser);
            }
            System.out.print("Type a command: ");
            command = scanner.nextLine();
        }
        /*currentUser = l.logIn("admin", "admin");
        l.logOut("admin");
        currentUser = l.logIn("admin", "admin");

        l.addUser(currentUser,"me","123456");
        currentUser = l.logIn("me","123456");
        l.sortByTitleAscending();
        l.booksAll();
        l.sortByRatingAscending();
        l.booksAll();
        System.out.println();
        l.removeBook("aOrigin");
        l.booksAll();*/
    }

}
