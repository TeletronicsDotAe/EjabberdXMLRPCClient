package ae.teletronics.ejabberd.entity;

/**
 * Created by kristian on 4/7/16.
 */
public class User {
    String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
