package ae.teletronics.ejabberd;

import ae.teletronics.ejabberd.entity.response.BooleanXmppResponse;
import ae.teletronics.ejabberd.entity.response.GetRosterResponse;
import ae.teletronics.ejabberd.entity.response.GetUserPairListResponse;
import ae.teletronics.ejabberd.entity.response.GetUsersResponse;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;

/**
 * Created by kristian on 4/7/16.
 */
public class EjabberdXMLRPCClient implements IEjabberdXMLRPCClient {

    ExecutorService executorService;
    XmlRpcClient client = new XmlRpcClient();
    ResponseParser responseParser = new ResponseParser();

    public EjabberdXMLRPCClient(ExecutorService executorService, XmlRpcClient client) {
        this.executorService = executorService;
        this.client = client;
    }

    @Override
    public CompletableFuture<BooleanXmppResponse> createUser(String username, String host, String password){
        Map params = new HashMap();
        params.put("user", username);
        params.put("host", host);
        params.put("password", password);

        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap createUserXMLRPCResponse = executeXmlRpc("register", Arrays.asList(params));
                return responseParser.parseBooleanResponse(createUserXMLRPCResponse);
            } catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);

    }

    @Override
    public CompletableFuture<BooleanXmppResponse> deleteUser(String username, String host){
        Map params = new HashMap();
        params.put("user", username);
        params.put("host", host);
        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap deleteUserXMLRPCResponse = executeXmlRpc("unregister", Arrays.asList(params));
                return responseParser.parseBooleanResponse(deleteUserXMLRPCResponse);
            } catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<GetUsersResponse> getUsers(String host){
        Map params = new HashMap();
        params.put("host", host);
        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap response = executeXmlRpc("registered_users", Arrays.asList(params));
                final GetUsersResponse getUsersResponse = responseParser.parseGetUserResponse(response);
                return getUsersResponse;
            } catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<BooleanXmppResponse> addRosterItem(String localuser, String localserver, String user, String server, String nick, String group, String subs){
        Map params = new HashMap();
        params.put("localuser", localuser);
        params.put("localserver", localserver);
        params.put("user", user);
        params.put("server", server);
        params.put("nick", nick);
        params.put("group", group);
        params.put("subs", subs);
        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap response = executeXmlRpc("add_rosteritem", Arrays.asList(params));
                final BooleanXmppResponse booleanXmppResponse = responseParser.parseBooleanResponse(response);
                return booleanXmppResponse;
            } catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<BooleanXmppResponse> deleteRosterItem(String localuser, String localserver, String user, String server){
        Map params = new HashMap();
        params.put("localuser", localuser);
        params.put("localserver", localserver);
        params.put("server", server);
        params.put("user", user);
        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap response = executeXmlRpc("delete_rosteritem", Arrays.asList(params));
                return responseParser.parseBooleanResponse(response);
            } catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<GetRosterResponse> getRoster(String user, String server){
        Map struct = new HashMap();
        struct.put("user", user);
        struct.put("server", server);
        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap response = executeXmlRpc("get_roster", Arrays.asList(struct));
                return responseParser.parseGetRosterResponse(response);
            } catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<BooleanXmppResponse> sendChatMessage(String to, String from, String subject, String body) {
        Map struct = new HashMap();
        struct.put("type", "chat");
        struct.put("to", to);
        struct.put("from", from);
        struct.put("subject", subject);
        struct.put("body", body);
        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap response = executeXmlRpc("send_message", Arrays.asList(struct));
                return responseParser.parseBooleanResponse(response);
            } catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<BooleanXmppResponse> sendStanza(String to, String from, String stanza) {
        Map struct = new HashMap();
        struct.put("to", to);
        struct.put("from", from);
        struct.put("stanza", stanza);
        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap response = executeXmlRpc("send_stanza", Arrays.asList(struct));
                return responseParser.parseBooleanResponse(response);
            } catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<GetUserPairListResponse> processRosterItems(String action, String subs, String asks, String users, String contacts){
        Map struct = new HashMap();
        struct.put("action", action);
        struct.put("subs", subs);
        struct.put("asks", asks);
        struct.put("users", users);
        struct.put("contacts", contacts);

        return CompletableFuture.supplyAsync(() -> {
            try {
                final HashMap response = executeXmlRpc("process_rosteritems", Arrays.asList(struct));
                return responseParser.parseUserPairListResponse(response);
            }catch (XmlRpcException e) {
                throw new CompletionException(e);
            }
        }, executorService);
    }

    HashMap executeXmlRpc(String command, List params) throws XmlRpcException {
        return (HashMap) client.execute(command, params);
    }

}
