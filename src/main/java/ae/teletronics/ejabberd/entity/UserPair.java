package ae.teletronics.ejabberd.entity;

/**
 * Created by jensrjorgensen on 22/02/2017.
 */
public class UserPair {
    String user;
    String contact;

    public UserPair() {

    }
    public UserPair(String user, String contact) {
        this.user = user;
        this.contact = contact;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
