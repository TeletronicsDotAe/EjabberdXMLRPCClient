package ae.teletronics.ejabberd;

import ae.teletronics.ejabberd.entity.IEjabberdXMLRPCClientBuilder;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kristian on 4/7/16.
 */
public class EjabberdXMLRPCClientBuilder implements IEjabberdXMLRPCClientBuilder {

    ExecutorService executorService = Executors.newCachedThreadPool();
    String ejabberdHostname = "localhost";
    String ejabberdPort = "4560";
    String ejabberdProtocol = "http";
    String ejabberdPath = "/RPC2";

    @Override
    public EjabberdXMLRPCClientBuilder setExecutorService(ExecutorService executorService){
        this.executorService = executorService;
        return this;
    }

    @Override
    public EjabberdXMLRPCClientBuilder setEjabberdHostname(String ejabberdHostname){
        this.ejabberdHostname = ejabberdHostname;
        return this;
    }

    @Override
    public EjabberdXMLRPCClientBuilder setEjabberdPort(String ejabberdPort){
        this.ejabberdPort = ejabberdPort;
        return this;
    }

    @Override
    public EjabberdXMLRPCClientBuilder setEjabberdProtocol(String ejabberdProtocol){
        this.ejabberdProtocol = ejabberdProtocol;
        return this;
    }

    @Override
    public EjabberdXMLRPCClientBuilder setEjabberdPath(String ejabberdPath){
        this.ejabberdPath = ejabberdPath;
        return this;
    }

    @Override
    public IEjabberdXMLRPCClient build() throws MalformedURLException {
        final URL ejabberdUrl = buildUrl();

        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(ejabberdUrl);

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);

        return new EjabberdXMLRPCClient(this.executorService, client);
    }

    URL buildUrl() throws MalformedURLException {
        return new URL(this.ejabberdProtocol, this.ejabberdHostname, Integer.parseInt(this.ejabberdPort), this.ejabberdPath);
    }

}