package ae.teletronics.ejabberd;

import ae.teletronics.ejabberd.entity.response.*;
import org.junit.Assert;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kristian on 4/7/16.
 */
public class EjabberdXMLRPCClientErrorResponseTest {

    public static final String ERROR_MESSAGE = "This is an error";
    XmlRpcClient xmlRpcClient = Mockito.mock(XmlRpcClient.class);
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    final EjabberdXMLRPCClient ejabberdXmlrpcClient = new EjabberdXMLRPCClientImpl(executorService, xmlRpcClient);

    @Before
    public void setup() throws Exception {
        Mockito.doThrow(new XmlRpcException(ERROR_MESSAGE)).when(xmlRpcClient).execute(Matchers.anyString(), Matchers.anyList());
    }

    @Test
    public void testCreateUser() throws Exception {
        final BooleanXmppResponse createUserResponse = ejabberdXmlrpcClient.createUser("kristian", "test.local", "just").get();
        assertError(createUserResponse);
    }

    @Test
    public void testDeleteUser() throws Exception {
        final BooleanXmppResponse deleteUserResponse = ejabberdXmlrpcClient.deleteUser("kristian", "test.local").get();
        assertError(deleteUserResponse);
    }

    @Test
    public void testGetUsers() throws Exception {
        final GetUsersResponse getUsersResponse = ejabberdXmlrpcClient.getUsers("test.local").get();
        assertError(getUsersResponse);
    }

    @Test
    public void testAddRosterItem() throws Exception {
        final BooleanXmppResponse addRosterItemResponse = ejabberdXmlrpcClient.addRosterItem("kristian", "test.local", "just", "test.local", "what", "group", "subs").get();
        assertError(addRosterItemResponse);
    }

    @Test
    public void testDeleteRosterItem() throws Exception {
        final BooleanXmppResponse deleteRosterItemResponse = ejabberdXmlrpcClient.deleteRosterItem("kristian", "test.local", "just", "test.local").get();
        assertError(deleteRosterItemResponse);
    }

    @Test
    public void testGetRoster() throws Exception {
        final GetRosterResponse getRosterResponse = ejabberdXmlrpcClient.getRoster("kristian", "test.local").get();
        assertError(getRosterResponse);
    }

    private void assertError(ErrorResponse response) {
        Assert.assertTrue(response.hasError());
        Assert.assertEquals(ERROR_MESSAGE, response.getError());
    }
}