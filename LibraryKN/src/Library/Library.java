package Library;

import javax.naming.AuthenticationNotSupportedException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Library {
    private static final File BOOKS_RECORD = new File("src/Library/Books.txt");
    private static final File USERS_RECORD = new File("src/Library/Users.txt");
    private static final File ID_COUNTER = new File("src/Library/IDCounter.txt");
    private Set<Book> books = new HashSet<Book>();
    private Set<User> users = new HashSet<User>();
    private static int IDHelper;


    public Library() throws IOException {
        loadUsersFromFile();
        loadBooksFromFile();
        loadIDCounterFromFile();
    }

    public User logIn(String username, String password){
        User currentUser = null;
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                currentUser = user;
                if (!user.loggedIn) {
                    user.loggedIn = true;
                    System.out.println("Welcome " + user.username);
                } else
                    System.out.println("You are already logged in!");
            }
        }
        return currentUser;
    }

    public void logOut(User user) {
        try{
            if(user==null)
                throw new NullPointerException();
        }
        catch (NullPointerException npe){
            System.out.println("You need to log in first!");
            return;
        }
        if (user.loggedIn) {
            user.loggedIn = false;
            System.out.printf("Come back soon %s!%n",user.username);
        } else
            System.out.println("You aren't logged in!");
    }

    protected void loadIDCounterFromFile(){
        try{
            Scanner scanner = new Scanner(ID_COUNTER);
            String line = scanner.nextLine();
            IDHelper = Integer.parseInt(line);
        }
        catch(FileNotFoundException fnfe){
            System.out.println("Something went wrong with ID file!");
        }
    }

    protected void loadUsersFromFile() {
        try {
            Scanner scanner = new Scanner(USERS_RECORD);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] info = line.split(" ");
                Role authority;
                if (info[2].equals("Administrator"))
                    authority = Role.Administrator;
                else
                    authority = Role.User;
                users.add(new User(info[0], info[1], authority));
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("Something went wrong with users file!");
        }
    }

    protected void loadBooksFromFile() {
        try {
            Scanner scanner = new Scanner(BOOKS_RECORD);
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
                String[] keyWords = scanner.nextLine().split(" ");
                books.add(Book.Builder.newInstance().withAuthor(author).withTitle(title).withGenre(genre).withResume(resume).withYear(year).withRating(rating).withID(ID).withKeyWords(keyWords).build());
            }
        }
        catch(FileNotFoundException fnfe){
            System.out.println("Something went wrong with books file!");
        }
    }

    protected void addNewUserToTheFile(User user) throws IOException {
        FileWriter fileWriter = new FileWriter(USERS_RECORD, true);
        fileWriter.write(String.format("%s %s %s%n", user.username, user.password, user.role));
        fileWriter.close();
    }

    protected void addNewBookToTheFile(Book book) throws IOException {
        FileWriter fileWriter = new FileWriter(BOOKS_RECORD, true);
        fileWriter.write(String.format(Locale.US, "%s;%s;%s;%s;%d;%.2f;%d;%n", book.getAuthor(), book.getTitle(), book.getGenre(), book.getResume(), book.getYear(), book.getRating(),book.getID()));
        for(String word:book.getKeyWords()){
            fileWriter.write(String.format("%s ",word));
        }
        fileWriter.write(String.format("%n"));
        fileWriter.close();
    }

    public void addBook(Book newBook){
        try {
            if (newBook == null)
                throw new NullPointerException();
        }
        catch (NullPointerException npe){
            System.out.println("Can't add null value to the set");
            return;
        }
        newBook.ID = IDHelper;
        IDHelper++;
        books.add(newBook);
    }

    public void booksAll() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void booksInfo(int ID) {
        for (Book book : books) {
            if (book.getID() == ID) {
                book.printDetailInfo();
            }
        }
    }

    public void findBooksByTitle(String title) {
        if(title==null)
            return;
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                System.out.println(book);
            }
        }
    }

    public void findBooksByAuthor(String author) {
        if(author == null)
            return;
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                System.out.println(book);
            }
        }
    }

    public void findBooksByTag(String word){
        if(word==null)
            return;
        for(Book book: books){
            if(book.getKeyWords().contains(word))
                book.printDetailInfo();
        }
    }

    protected void printBooks(Set<Book> books){
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void sortByTitleDescending() {
        Set<Book> titleDescendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getTitle().compareTo(o1.getTitle());
            }
        });
        titleDescendingSet.addAll(books);
        printBooks(titleDescendingSet);
    }

    public void sortByTitleAscending() {
        Set<Book> titleAscendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        titleAscendingSet.addAll(books);
        printBooks(titleAscendingSet);
    }

    public void sortByAuthorDescending() {

        Set<Book> authorDescendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o2.getAuthor().compareTo(o1.getAuthor());
            }
        });
        authorDescendingSet.addAll(books);
        printBooks(authorDescendingSet);
    }

    public void sortByAuthorAscending() {
        Set<Book> authorAscendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getAuthor().compareTo(o2.getAuthor());
            }
        });
        authorAscendingSet.addAll(books);
        printBooks(authorAscendingSet);
    }

    public void sortByYearAscending() {
        Comparator<Book> bookComparatorByYear = Comparator.comparing(
                Book::getYear, (s1, s2) -> {
                    return s1.compareTo(s2);
                }
        );
        List<Book> list = new ArrayList<Book>();
        list.addAll(books);
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
        list.addAll(books);
        Collections.sort(list, bookComparatorByYear);
        for(Book book: list){
            System.out.println(book);
        }
    }

    public void sortByRatingAscending() {
        Set<Book> ratingAscendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return Double.compare(o1.getRating(),o2.getRating());
            }
        });
        ratingAscendingSet.addAll(books);
        printBooks(ratingAscendingSet);
    }

    public void sortByRatingDescending() {
        Set<Book> ratingAscendingSet = new TreeSet<Book>(new Comparator<Book>(){
            @Override
            public int compare(Book o1, Book o2) {
                return -Double.compare(o1.getRating(),o2.getRating());
            }
        });
        ratingAscendingSet.addAll(books);
        printBooks(ratingAscendingSet);
    }

    public void addUser(User user, String username, String password){
        try{
            if(user == null)
                throw new NullPointerException();
        }
        catch(NullPointerException npe){
            System.out.println("You need to log in first!");
        }
        try{
            if (user.role == Role.Administrator) {
                User newUser = new User(username, password, Role.User);
                if(users.contains(newUser)) {
                    System.out.println("This username is taken! Please try with another!");
                    return;
                }
                users.add(newUser);
            } else
                throw new AuthenticationNotSupportedException("You should be administrator to add users!");
        }
        catch(AuthenticationNotSupportedException anse){
            System.out.println("You have to be administrator to add new user!");
        }
    }

    public void removeBook(int ID) {
        Iterator<Book> it = books.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            if (book.getID() == ID) {
                it.remove();
            }
        }
    }

    public void removeUser(String username) {
        Iterator<User> it = users.iterator();
        while (it.hasNext()) {
            User user = it.next();
            if (user.username.equals(username)) {
                it.remove();
            }
        }
    }

    protected void rewriteBookFile() throws IOException {
        FileWriter fileWriter = new FileWriter(BOOKS_RECORD);
        for (Book book : books) {
            addNewBookToTheFile(book);
        }
    }

    protected void rewriteUsersFile() throws IOException {
        FileWriter fileWriter = new FileWriter(USERS_RECORD);
        for (User user : users) {
            addNewUserToTheFile(user);
        }
    }

    protected void refreshIDCounter() throws IOException {
            FileWriter fw = new FileWriter(ID_COUNTER);
            fw.write(String.format("%d", IDHelper));
            fw.close();
    }

    public void saveChanges(){
        try {
            rewriteBookFile();
            rewriteUsersFile();
            refreshIDCounter();
        }
        catch(IOException e){
            System.out.println("Something went wrong with the files! Sorry!");
        }
    }

    public void closeLibrary(){
        saveChanges();
        System.exit(0);
    }
}
