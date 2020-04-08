package Library;
import javax.security.auth.login.LoginException;

public class User implements Comparable {
    protected String username;
    protected String password;
    protected Role role;
    protected boolean loggedIn;

    public User(){
        username = "";
        password = "";
        role = Role.User;
        loggedIn = false;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        role = Role.User;
        loggedIn = false;
    }

    public User(String username, String password, Role access){
        this.username = username;
        this.password = password;
        role = access;
        loggedIn = false;
    }

    public void changeUsername(String newUsername) throws LoginException {
        if(loggedIn == false)
            throw new LoginException();
        username = newUsername;
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public Role getRole(){
        return role;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public int compareTo(Object o) throws IllegalArgumentException {
        if(! (o instanceof User))
            throw new IllegalArgumentException("You cant compare User to other class!");
        return username.compareTo(((User) o).username);
    }
}
