package ae.teletronics.ejabberd;

import ae.teletronics.ejabberd.entity.response.BooleanXmppResponse;
import ae.teletronics.ejabberd.entity.response.GetRosterResponse;
import ae.teletronics.ejabberd.entity.response.GetUserPairListResponse;
import ae.teletronics.ejabberd.entity.response.GetUsersResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Created by carlos on 9/3/17.
 */
public interface EjabberdXMLRPCClient {
    public CompletableFuture<BooleanXmppResponse> createUser(String username, String host, String password);

    public CompletableFuture<BooleanXmppResponse> deleteUser(String username, String host);

    public CompletableFuture<GetUsersResponse> getUsers(String host);

    public CompletableFuture<BooleanXmppResponse> addRosterItem(String localuser, String localserver, String user, String server, String nick, String group, String subs);

    public CompletableFuture<BooleanXmppResponse> deleteRosterItem(String localuser, String localserver, String user, String server);

    public CompletableFuture<GetRosterResponse> getRoster(String user, String server);

    public CompletableFuture<BooleanXmppResponse> sendChatMessage(String to, String from, String subject, String body);

    public CompletableFuture<BooleanXmppResponse> sendStanza(String to, String from, String stanza);

    public CompletableFuture<GetUserPairListResponse> processRosterItems(String action, String subs, String asks, String users, String contacts);
}
