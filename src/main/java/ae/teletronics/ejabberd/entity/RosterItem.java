package ae.teletronics.ejabberd.entity;

/**
 * Created by kristian on 4/7/16.
 */
public class RosterItem {
    String jid;
    String nick;
    String subscription;
    String ask;
    String group;

    public RosterItem() {
    }

    public RosterItem(String jid, String nick, String subscription, String ask, String group) {
        this.jid = jid;
        this.nick = nick;
        this.subscription = subscription;
        this.ask = ask;
        this.group = group;
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSubscription() {
        return subscription;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
