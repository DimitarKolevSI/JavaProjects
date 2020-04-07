package Library;

import javax.security.auth.login.LoginException;

public class User implements Comparable {
    protected String Username;
    protected String Password;
    protected LevelOfAccess levelOfAccess;
    protected boolean LoggedIn;

    public User(){
        Username = "";
        Password = "";
        levelOfAccess = LevelOfAccess.User;
        LoggedIn = false;
    }

    public User(String username, String password){
        Username = username;
        Password = password;
        levelOfAccess = LevelOfAccess.User;
        LoggedIn = false;
    }

    public User(String username, String password, LevelOfAccess access){
        Username = username;
        Password = password;
        levelOfAccess = access;
        LoggedIn = false;
    }

    public void changeUsername(String newUsername) throws LoginException {
        if(LoggedIn == false)
            throw new LoginException();
        Username = newUsername;
    }

    public boolean isLoggedIn(){
        return LoggedIn;
    }

    public LevelOfAccess getLevelOfAccess(){
        return levelOfAccess;
    }

    public String getUsername(){
        return Username;
    }

    @Override
    public int compareTo(Object o) throws IllegalArgumentException {
        if(! (o instanceof User))
            throw new IllegalArgumentException("You cant compare User to other class!");
        return Username.compareTo(((User) o).Username);
    }
}
