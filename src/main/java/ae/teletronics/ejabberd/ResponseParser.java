package ae.teletronics.ejabberd;

import ae.teletronics.ejabberd.entity.RosterItem;
import ae.teletronics.ejabberd.entity.User;
import ae.teletronics.ejabberd.entity.UserPair;
import ae.teletronics.ejabberd.entity.response.BooleanXmppResponse;
import ae.teletronics.ejabberd.entity.response.GetRosterResponse;
import ae.teletronics.ejabberd.entity.response.GetUserPairListResponse;
import ae.teletronics.ejabberd.entity.response.GetUsersResponse;
import org.apache.xmlrpc.XmlRpcException;

import java.util.HashMap;

/**
 * Created by kristian on 4/7/16.
 */
public class ResponseParser {

    BooleanXmppResponse parseBooleanResponse(HashMap response) throws XmlRpcException {
        int res = (int) response.get("res");
        final BooleanXmppResponse booleanXmppResponse = new BooleanXmppResponse(res);
        if (!booleanXmppResponse.isSuccessFull()) {
            booleanXmppResponse.setError((String) response.get("text"));
        }
        return booleanXmppResponse;
    }

    public GetUsersResponse parseGetUserResponse(HashMap response) {
        GetUsersResponse getUsersResponse = new GetUsersResponse();
        Object[] users = (Object[]) response.get("users");
        for (int i = 0; i < users.length; i++) {
            HashMap userMap = (HashMap) users[i];
            String username = (String) userMap.get("username");
            getUsersResponse.getUserList().add(new User(username));
        }
        return getUsersResponse;
    }

    public GetRosterResponse parseGetRosterResponse(HashMap response) {
        GetRosterResponse getRosterResponse = new GetRosterResponse();
        final Object[] contacts = (Object[]) response.get("contacts");
        for (Object contactObject : contacts) {
            if (contactObject instanceof HashMap && ((HashMap) contactObject).get("contact") instanceof Object[]) {
                final Object[] contact = (Object[]) ((HashMap) contactObject).get("contact");
                RosterItem rosterItem = parseRosterItem(contact);
                getRosterResponse.getRosterItemList().add(rosterItem);
            }
        }
        return getRosterResponse;
    }

    public GetUserPairListResponse parseUserPairListResponse(HashMap response) {
        GetUserPairListResponse getUserPairListResponse = new GetUserPairListResponse();
        final Object[] responseList = (Object[]) response.get("response");
        for (Object pairObject : responseList) {
            if (pairObject instanceof HashMap && ((HashMap) pairObject).get("pairs") instanceof Object[]) {
                final Object[] userPairMap = (Object[]) ((HashMap) pairObject).get("pairs");
                UserPair userPair = parseUserPair(userPairMap);
                getUserPairListResponse.getUserPairList().add(userPair);
            }
        }

        return getUserPairListResponse;
    }

    UserPair parseUserPair(Object[] userPairList) {
        UserPair userPair = new UserPair();
        if (userPairList[0] instanceof HashMap) {
            HashMap userMap = (HashMap) userPairList[0];
            userPair.setUser((String) userMap.get("user"));
        }
        if (userPairList[1] instanceof HashMap) {
            HashMap contactMap = (HashMap) userPairList[1];
            userPair.setContact((String) contactMap.get("contact"));
        }
        return userPair;
    }

    RosterItem parseRosterItem(Object[] contact) {
        RosterItem rosterItem = new RosterItem();

        if (contact[0] instanceof HashMap) {
            HashMap jidMap = (HashMap) contact[0];
            rosterItem.setJid((String) jidMap.get("jid"));
        }

        if (contact[1] instanceof HashMap) {
            HashMap nickMap = (HashMap) contact[1];
            rosterItem.setNick((String) nickMap.get("nick"));
        }

        if (contact[2] instanceof HashMap) {
            HashMap subscriptionMap = (HashMap) contact[2];
            rosterItem.setSubscription((String) subscriptionMap.get("subscription"));
        }

        if (contact[3] instanceof HashMap) {
            HashMap askMap = (HashMap) contact[3];
            rosterItem.setAsk((String) askMap.get("ask"));
        }

        if (contact[4] instanceof HashMap) {
            HashMap groupMap = (HashMap) contact[4];
            rosterItem.setGroup((String) groupMap.get("group"));
        }
        return rosterItem;
    }
}
