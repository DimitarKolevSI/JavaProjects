package bg.sofia.uni.fmi.mjt.wish.list;

import java.util.Objects;

public class Student {
    private final String username;
    private final String password;
    private boolean isLoggedIn;

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void logIn() {
        this.isLoggedIn = true;
    }

    public void logOut() {
        this.isLoggedIn = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return username.equals(student.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
