package Main;

import Library.Library;
import Library.User;
import Library.Book;

import javax.naming.AuthenticationNotSupportedException;
import java.io.IOException;
import java.util.Scanner;

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
        command = command.toLowerCase();
        while (!command.toLowerCase().equals("exit")) {
            String[] commands = command.split(" ");
            if (commands[0].toLowerCase().equals("login")) {
                System.out.print("Username: ");
                String username = scanner.nextLine();
                System.out.print("Password: ");
                String password = scanner.nextLine();
                currentUser = l.logIn(username, password);
            }
            else if (commands[0].equals("logout"))
                l.logOut(currentUser);
            else if (commands[0].equals("books")) {
                if (commands[1].equals("all"))
                    l.booksAll();
                else if (commands[1].equals("info"))
                    l.booksInfo(Integer.parseInt(commands[2]));
                else if (commands[1].equals("find")){
                    System.out.print("Option: ");
                    String Option = scanner.nextLine();
                    if(Option.equals("title")){
                        System.out.print("Title: ");
                        String Title = scanner.nextLine();
                        l.findBooksByTitle(Title);
                    }
                    else if(Option.equals("author"))
                    {
                        System.out.print("Author: ");
                        String Author = scanner.nextLine();
                        l.findBooksByAuthor(Author);
                    }

                }
                else if(commands[1].equals("sort")){
                    System.out.print("Option: ");
                    String Option = scanner.nextLine();
                    System.out.print("Order: ");
                    String Order = scanner.nextLine();
                    if(Option.equals("title") && Order.equals("asc")) l.sortByTitleAscending();
                    else if(Option.equals("title") && Order.equals("desc")) l.sortByTitleDescending();
                    else if(Option.equals("author") && Order.equals("asc")) l.sortByAuthorAscending();
                    else if(Option.equals("author") && Order.equals("desc")) l.sortByAuthorDescending();
                    else if(Option.equals("year") && Order.equals("asc")) l.sortByYearDescending();
                    else if(Option.equals("year") && Order.equals("desc")) l.sortByYearDescending();
                    else if(Option.equals("rating") && Order.equals("asc")) l.sortByRatingAscending();
                    else if(Option.equals("rating") && Order.equals("desc")) l.sortByRatingDescending();
                }
                else if(commands[1].equals("add")){
                    System.out.print("Author: ");
                    String Author = scanner.nextLine();
                    System.out.print("Title: ");
                    String Title = scanner.nextLine();
                    System.out.print("Genre: ");
                    String Genre = scanner.nextLine();
                    System.out.print("Resume: ");
                    String Resume = scanner.nextLine();
                    System.out.print("Year: ");
                    int Year = scanner.nextInt();
                    l.addBook(new Book(Author,Title, Genre, Resume, Year));
                }
            }
            else if(commands[0].equals("users")){
                if(commands[1].equals("add")){
                    l.addUser(currentUser,commands[2],commands[3]);
                }
                /*else if(commands[1].equals("remove")){
                }*/
            }

            System.out.print("Type a command: ");
            command = scanner.nextLine();
        }
        System.out.print("Bye, see you soon!");
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
