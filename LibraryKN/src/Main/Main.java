package Main;

import Library.Book;
import Library.Library;
import Library.LevelOfAccess;
import Library.User;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
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
        Library l = new Library();
        l.logIn("admin", "admin");
        l.logOut("admin");
        l.logIn("admin", "admin");
    }

}
