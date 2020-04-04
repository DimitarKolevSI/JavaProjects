package Library;

import javax.security.auth.login.LoginException;

public class User {
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
}
