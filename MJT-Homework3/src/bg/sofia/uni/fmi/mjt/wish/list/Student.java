package bg.sofia.uni.fmi.mjt.wish.list;

import java.util.Objects;

public record Student(String username, String password) {

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return username.equals(student.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
