package ae.teletronics.ejabberd;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;

/**
 * Created by pcoltau on 3/13/17.
 */
public interface IEjabberdXMLRPCClientBuilder {
    IEjabberdXMLRPCClientBuilder setExecutorService(ExecutorService executorService);

    IEjabberdXMLRPCClientBuilder setEjabberdHostname(String ejabberdHostname);

    IEjabberdXMLRPCClientBuilder setEjabberdPort(String ejabberdPort);

    IEjabberdXMLRPCClientBuilder setEjabberdProtocol(String ejabberdProtocol);

    IEjabberdXMLRPCClientBuilder setEjabberdPath(String ejabberdPath);

    IEjabberdXMLRPCClient build() throws MalformedURLException;
}
