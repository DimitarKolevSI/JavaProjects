package Library;

import javax.naming.AuthenticationNotSupportedException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Library {
    private File booksRecord = new File("src/Library/Books.txt");
    private File usersRecord = new File("src/Library/Users.txt");
    private File IDCounter = new File("src/Library/IDCounter.txt");
    private Set<Book> Books = new TreeSet<Book>();
    private List<User> Users = new LinkedList<User>();
    private int IDHelper;


    public Library() throws IOException {
        loadUsersFromFile();
        loadBooksFromFile();
        Scanner scanner = new Scanner(IDCounter);
        String line = scanner.nextLine();
        IDHelper = Integer.parseInt(line);
    }

    public User logIn(String username, String password) throws FileNotFoundException {
        User currentUser = null;
        for (User user : Users) {
            if (user.Username.equals(username) && user.Password.equals(password)) {
                currentUser = user;
                if (!user.LoggedIn) {
                    user.LoggedIn = true;
                    System.out.println("Welcome " + user.Username);
                } else
                    System.out.println("You are already logged in!");
            }
        }
        return currentUser;
    }

    public void logOut(User user) {
        if (user.LoggedIn) {
            user.LoggedIn = false;
            System.out.println("Come back soon!");
        } else
            System.out.println("You aren't logged in!");
    }

    protected void loadUsersFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(usersRecord);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] info = line.split(" ");
            LevelOfAccess authority;
            if (info[2].equals("Administrator"))
                authority = LevelOfAccess.Administrator;
            else
                authority = LevelOfAccess.User;
            Users.add(new User(info[0], info[1], authority));
        }
    }

    protected void loadBooksFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(booksRecord);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] info = line.split(";");
            String author = info[0];
            String title = info[1];
            String genre = info[2];
            String resume = info[3];
            int year = Integer.parseInt(info[4]);
            double rating = Double.parseDouble(info[5]);
            int numberOfRatings = Integer.parseInt(info[6]);
            int ID = Integer.parseInt(info[7]);
            Books.add(new Book(author, title, genre, resume, year, rating, numberOfRatings, ID));
        }
    }

    protected void addNewUserToTheFile(User user) throws IOException {
        FileWriter fileWriter = new FileWriter(usersRecord, true);
        fileWriter.write(String.format("%n%s %s %s", user.Username, user.Password, user.levelOfAccess));
        fileWriter.close();
    }

    protected void addNewBookToTheFile(Book book) throws IOException {
        FileWriter fileWriter = new FileWriter(booksRecord, true);
        fileWriter.write(String.format(Locale.US, "%s;%s;%s;%s;%d;%.2f;%d;%d%n", book.getAuthor(), book.getTitle(), book.getGenre(), book.getResume(), book.getYear(), book.getRating(), book.getNumberOfRatings(), book.getID()));
        fileWriter.close();
    }

    public void addBook(Book newBook) throws IOException {
        newBook.ID = IDHelper;
        IDHelper++;
        Books.add(newBook);
        try {
            addNewBookToTheFile(newBook);
        } catch (IOException e) {
            System.out.println("Record of books not found");
        }
        FileWriter fw = new FileWriter(IDCounter);
        fw.write(String.format("%d", IDHelper));
        fw.close();
    }

    public void booksAll() {
        for (Book book : Books) {
            System.out.println(book);
        }
    }

    public void booksInfo(int ID) {
        for (Book book : Books) {
            if (book.getID() == ID) {
                System.out.print("Author: " + book.getAuthor());
                System.out.print(" Title: " + book.getTitle());
                System.out.print(" Genre: " + book.getGenre());
                System.out.print(" Year: " + book.getYear());
                System.out.print(" Resume: " + book.getResume());
                System.out.print(" Rating: " + book.getRating());
                System.out.println(" ID: " + book.getID());
            }
        }
    }

    public void findBooksByTitle(String title) {
        for (Book book : Books) {
            if (book.getTitle().equals(title)) {
                System.out.println(book);
            }
        }
    }

    public void findBooksByAuthor(String author) {
        for (Book book : Books) {
            if (book.getAuthor().equals(author)) {
                System.out.println(book);
            }
        }
    }


    protected void printBooks(Set<Book> books){
        Iterator<Book> it = books.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    public void sortByTitleDescending() {
        Set<Book> titleDescendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getTitle().compareTo(o1.getTitle());
            }
        });
        titleDescendingSet.addAll(Books);
        printBooks(titleDescendingSet);
    }


    public void sortByTitleAscending() {
        Set<Book> titleAscendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        titleAscendingSet.addAll(Books);
        printBooks(titleAscendingSet);
    }

    public void sortByAuthorDescending() {

        Set<Book> authorDescendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getAuthor().compareTo(o1.getAuthor());
            }
        });
        authorDescendingSet.addAll(Books);
        printBooks(authorDescendingSet);
    }

    public void sortByAuthorAscending() {
        Set<Book> authorAscendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getAuthor().compareTo(o2.getAuthor());
            }
        });
        authorAscendingSet.addAll(Books);
        printBooks(authorAscendingSet);
    }

    public void sortByYearAscending() {
        Comparator<Book> bookComparatorByYear = Comparator.comparing(
                Book::getYear, (s1, s2) -> {
                    return s1.compareTo(s2);
                }
        );
        List<Book> list = new ArrayList<Book>();
        list.addAll(Books);
        Collections.sort(list, bookComparatorByYear);
        for(Book book: list){
            System.out.println(book);
        }
    }

    public void sortByYearDescending() {
        Comparator<Book> bookComparatorByYear = Comparator.comparing(
                Book::getYear, (s1, s2) -> {
                    return s2.compareTo(s1);
                }
        ).reversed();
        List<Book> list = new ArrayList<Book>();
        list.addAll(Books);
        Collections.sort(list, bookComparatorByYear);
        for(Book book: list){
            System.out.println(book);
        }
    }

    public void sortByRatingAscending() {
        Set<Book> ratingAscendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                /*if(o1.getRating() > o2.getRating()) return -1;
                if(o1.getRating() < o1.getRating()) return 1;
                return 0;*/
                return Double.compare(o1.getRating(),o2.getRating());
            }
        });
        ratingAscendingSet.addAll(Books);
        printBooks(ratingAscendingSet);
    }

    public void sortByRatingDescending() {
        Set<Book> ratingAscendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                /*if(o1.getRating() > o2.getRating()) return -1;
                if(o1.getRating() < o1.getRating()) return 1;
                return 0;*/
                return -Double.compare(o1.getRating(),o2.getRating());
            }
        });
        ratingAscendingSet.addAll(Books);
        printBooks(ratingAscendingSet);
    }

    public void addUser(User user, String username, String password) throws AuthenticationNotSupportedException, IOException {
        if (user.levelOfAccess == LevelOfAccess.Administrator) {
            User newUser = new User(username, password, LevelOfAccess.User);
            Users.add(newUser);
            addNewUserToTheFile(newUser);
        } else
            throw new AuthenticationNotSupportedException("You should be administrator to add users!");
    }

    protected void rewriteBookFile() throws IOException {
        FileWriter fileWriter = new FileWriter(booksRecord);
        for (Book book : Books) {
            addNewBookToTheFile(book);
        }
    }

    protected void rewriteUsersFile() throws IOException {
        FileWriter fileWriter = new FileWriter(usersRecord);
        for (User user : Users) {
            addNewUserToTheFile(user);
        }
    }

    public void removeBook(String title) throws IOException {
        Iterator<Book> it = Books.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            if (book.getTitle().equals(title)) {
                it.remove();
            }
        }
        rewriteBookFile();
    }

    public void removeUser(String username) throws IOException {
        Iterator<User> it = Users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.Username.equals(username)) {
                it.remove();
            }
        }
        rewriteUsersFile();
    }


}
