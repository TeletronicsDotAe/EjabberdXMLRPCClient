package ae.teletronics.ejabberd;

import org.junit.Assert;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by kristian on 4/12/16.
 */
public class EjabberdXMLRPCClientBuilderTest {
    @Test
    public void build() throws Exception {
        final URL url = new EjabberdXMLRPCClientBuilder()
                .setEjabberdHostname("test.local")
                .setEjabberdPort("4324")
                .buildUrl();

        Assert.assertEquals("http://test.local:4324/RPC2", url.toString());
    }

}