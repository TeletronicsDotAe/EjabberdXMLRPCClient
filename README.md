# EjabberdXMLRPCClient
Java client library for ejabberd XML RPC

## Usage

``` java
package ae.teletronics.ejabberd;

import ae.teletronics.ejabberd.entity.RosterItem;
import ae.teletronics.ejabberd.entity.response.BooleanXmppResponse;
import ae.teletronics.ejabberd.entity.response.GetRosterResponse;

import java.net.MalformedURLException;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsageExample {
    final static Logger logger = Logger.getLogger(UsageExample.class.getName());

    public static void run(String[] args) throws MalformedURLException {
        EjabberdXMLRPCClient ejabberdXMLRPCClient = new EjabberdXMLRPCClientBuilder()
                .setEjabberdHostname("localhost")
                .setExecutorService(Executors.newCachedThreadPool())
                .build();

        final BooleanXmppResponse createFirstUser = ejabberdXMLRPCClient.createUser("kristian", "test.local", "kristian").join();
        final BooleanXmppResponse createSecondUser = ejabberdXMLRPCClient.createUser("testuser", "test.local", "kristian").join();
        final BooleanXmppResponse addRosterItem = ejabberdXMLRPCClient.addRosterItem("kristian", "test.local", "testuser", "ello.local", "testuser", "Contacts", "none").join();

        final GetRosterResponse getRosterResponse = ejabberdXMLRPCClient.getRoster("kristian", "ello.local").join();

        for (RosterItem rosterItem : getRosterResponse.getRosterItemList()) {
            logger.log(Level.INFO, "Found user {0} in roster for user {1}", new Object[]{rosterItem.getJid(), "kristian"});
        }
    }
}

```
