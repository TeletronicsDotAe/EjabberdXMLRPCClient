package ae.teletronics.ejabberd;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.hamcrest.core.Is.isA;

/**
 * Created by kristian on 4/7/16.
 */
public class EjabberdXMLRPCClientErrorResponseTest {

    public static final String ERROR_MESSAGE = "This is an error";
    XmlRpcClient xmlRpcClient = Mockito.mock(XmlRpcClient.class);
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    final IEjabberdXMLRPCClient ejabberdXmlrpcClient = new EjabberdXMLRPCClient(executorService, xmlRpcClient);

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() throws Exception {
        Mockito.doThrow(new XmlRpcException(ERROR_MESSAGE)).when(xmlRpcClient).execute(Matchers.anyString(), Matchers.anyList());
    }

    @Test
    public void testCreateUser() throws Exception {
        expectedException.expectCause(isA(XmlRpcException.class));
        ejabberdXmlrpcClient.createUser("kristian", "test.local", "just").get();
    }

    @Test
    public void testDeleteUser() throws Exception {
        expectedException.expectCause(isA(XmlRpcException.class));
        ejabberdXmlrpcClient.deleteUser("kristian", "test.local").get();
    }

    @Test
    public void testGetUsers() throws Exception {
        expectedException.expectCause(isA(XmlRpcException.class));
        ejabberdXmlrpcClient.getUsers("test.local").get();
    }

    @Test
    public void testAddRosterItem() throws Exception {
        expectedException.expectCause(isA(XmlRpcException.class));
        ejabberdXmlrpcClient.addRosterItem("kristian", "test.local", "just", "test.local", "what", "group", "subs").get();
    }

    @Test
    public void testDeleteRosterItem() throws Exception {
        expectedException.expectCause(isA(XmlRpcException.class));
        ejabberdXmlrpcClient.deleteRosterItem("kristian", "test.local", "just", "test.local").get();
    }

    @Test
    public void testGetRoster() throws Exception {
        expectedException.expectCause(isA(XmlRpcException.class));
        ejabberdXmlrpcClient.getRoster("kristian", "test.local").get();
    }
}