package bg.sofia.uni.fmi.mjt.netflix.account;

import java.time.LocalDateTime;

public class Account {
    private final String username;
    private final LocalDateTime birthdate;

    public Account(String username, LocalDateTime birthdate) {
        this.username = username;
        this.birthdate = birthdate;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getBirthdate() {
        return birthdate;
    }
}
