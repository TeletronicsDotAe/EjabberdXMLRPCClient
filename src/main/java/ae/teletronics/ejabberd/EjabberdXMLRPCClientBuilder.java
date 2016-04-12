package ae.teletronics.ejabberd;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kristian on 4/7/16.
 */
public class EjabberdXMLRPCClientBuilder {

    ExecutorService executorService = Executors.newCachedThreadPool();
    String ejabberdHostname = "localhost";
    String ejabberdPort = "4560";
    String ejabberdProtocol = "http";
    String ejabberdPath = "RPC2";

    public EjabberdXMLRPCClientBuilder setExecutorService(ExecutorService executorService){
        this.executorService = executorService;
        return this;
    }

    public EjabberdXMLRPCClientBuilder setEjabberdHostname(String ejabberdHostname){
        this.ejabberdHostname = ejabberdHostname;
        return this;
    }

    public EjabberdXMLRPCClientBuilder setEjabberdPort(String ejabberdPort){
        this.ejabberdPort = ejabberdPort;
        return this;
    }

    public EjabberdXMLRPCClientBuilder setEjabberdProtocol(String ejabberdProtocol){
        this.ejabberdProtocol = ejabberdProtocol;
        return this;
    }

    public EjabberdXMLRPCClientBuilder setEjabberdPath(String ejabberdPath){
        this.ejabberdPath = ejabberdPath;
        return this;
    }

    public EjabberdXMLRPCClient build() throws MalformedURLException {
        final URL ejabberdUrl = new URL(this.ejabberdProtocol, this.ejabberdHostname, this.ejabberdPort);

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(ejabberdUrl);

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        return new EjabberdXMLRPCClient(this.executorService, client);
    }
}