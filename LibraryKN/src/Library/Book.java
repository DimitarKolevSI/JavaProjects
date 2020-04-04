package Library;

import org.w3c.dom.css.Rect;

import java.security.Key;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

public class Book implements Comparable{
    private String Author;
    private String Title;
    private String Genre;
    private String Resume;
    private int Year;
    private Set<String> KeyWords;
    private double Rating;
    private int NumberOfRatings;
    private static int IDCounter = 1;
    private int ID;

    public Book(){
        Author = "";
        Title = "";
        Genre = "";
        Resume = "";
        Year = 0;
        KeyWords = new TreeSet<>(Collections.emptySet());
        Rating = 0;
        NumberOfRatings = 0;
        setID();
    }

    public Book(String author, String title, String genre, String resume, int year, String... keyWords){
        Author = author;
        Title = title;
        Genre = genre;
        Resume = resume;
        Year = year;
        KeyWords = new TreeSet<String>(Arrays.asList(keyWords));
        Rating = 0;
        NumberOfRatings = 0;
        setID();
    }

    public Book(String author, String title, String genre, String resume, int year, double rating, int numberOfRatings,String... keyWords){
        Author = author;
        Title = title;
        Genre = genre;
        Resume = resume;
        Year = year;
        KeyWords = new TreeSet<String>(Arrays.asList(keyWords));
        Rating = rating;
        NumberOfRatings = numberOfRatings;
        setID();
    }

    public String getAuthor() {
        return Author;
    }

    public String getTitle() {
        return Title;
    }

    public String getGenre() {
        return Genre;
    }

    public String getResume() {
        return Resume;
    }

    public int getYear() {
        return Year;
    }

    public Set<String> getKeyWords() {
        return KeyWords;
    }

    public List<String> getKeyWordsAsList() {
        return KeyWords.stream().collect(Collectors.toList());
    }

    public double getRating() {
        return Rating;
    }

    public int getNumberOfRatings() {
        return NumberOfRatings;
    }

    public int getID() {
        return ID;
    }

    private void setID(){
        ID = IDCounter;
        IDCounter++;
    }

    public void rateBook(double rating)throws IllegalArgumentException{
        if(rating < 0 || rating > 10)
            throw new IllegalArgumentException("Rating should be between 0 and 10");
        double newRating = (getNumberOfRatings()*getRating() + rating)/(getNumberOfRatings() + 1);
        this.Rating = rating;
        NumberOfRatings++;
    }

    @Override
    public String toString() {
        return "Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Genre='" + Genre + '\'' +
                ", ID=" + ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return ID == book.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

    @Override
    public int compareTo(Object o) throws IllegalArgumentException{
        if(!(o instanceof Book))
            throw new IllegalArgumentException("You can't compare object Book with another class!");
        return this.ID - ((Book) o).ID;
    }
}
