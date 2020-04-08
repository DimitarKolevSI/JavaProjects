package Library;

import java.util.*;

public class Book implements Comparable{

    private final String author;
    private final String title;
    private final String genre;
    private final String resume;
    private final int year;
    private final double rating;
    private final Set<String> keyWords;
    protected int ID;

    public static class Builder{
        private String author;
        private String title;
        private String genre;
        private String resume;
        private int year;
        private double rating;
        private int IDCounter = 1;
        private Set<String> keyWords;
        protected int ID;

        public Builder(){
            ID = IDCounter;
            IDCounter++;
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder withAuthor(String author){
            this.author = author;
            return this;
        }

        public Builder withTitle(String title){
            this.title = title;
            return this;
        }

        public Builder withGenre(String genre){
            this.genre = genre;
            return this;
        }

        public Builder withResume(String resume){
            this.resume = resume;
            return this;
        }

        public Builder withYear(int year){
            this.year = year;
            return this;
        }

        public Builder withRating(double rating){
            this.rating = rating;
            return this;
        }

        public Builder withKeyWords(Set<String> keyWords){
            this.keyWords = keyWords;
            return this;
        }

        public Builder withKeyWords(String... keyWords){
            this.keyWords = new HashSet<String>(Arrays.asList(keyWords));
            return this;
        }

        protected Builder withID(int ID){
            this.ID = ID;
            return this;
        }

        public Book build(){
            return new Book(this);
        }
    }


    private Book(Builder builder){
        this.title = builder.title;
        this.author = builder.author;
        this.genre = builder.genre;
        this.resume = builder.resume;
        this.keyWords = builder.keyWords;
        this.ID = builder.ID;
        this.rating = builder.rating;
        this.year = builder.year;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getResume() {
        return resume;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    public int getID() {
        return ID;
    }

    protected void setID(int newID){
        this.ID = newID;
    }

    public String getKeyWordsAsArray(){
        return keyWords.toString();
    }

    public Set<String> getKeyWords(){
        return keyWords;
    }

    public void addKeyWord(String word){
        if(word == null)
            return;
        keyWords.add(word);
    }

    public void printDetailInfo(){
        System.out.printf("Title: %s, Author: %s, Genre: %s, Year: %d, Rating: %.2f, ID: %d%n",getTitle(),getAuthor(),getGenre(),getYear(),getRating(),getID());
        System.out.print("Key words: ");
        for(String word: keyWords){
            System.out.print(word + " ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", Genre='" + genre + '\'' +
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
