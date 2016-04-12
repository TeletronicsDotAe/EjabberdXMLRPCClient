package ae.teletronics.ejabberd;

import ae.teletronics.ejabberd.entity.response.BooleanXmppResponse;
import ae.teletronics.ejabberd.entity.response.GetRosterResponse;
import ae.teletronics.ejabberd.entity.response.GetUsersResponse;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.common.TypeFactoryImpl;
import org.apache.xmlrpc.common.XmlRpcController;
import org.apache.xmlrpc.common.XmlRpcHttpRequestConfigImpl;
import org.apache.xmlrpc.common.XmlRpcStreamRequestConfig;
import org.apache.xmlrpc.parser.XmlRpcResponseParser;
import org.apache.xmlrpc.util.SAXParsers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Created by kristian on 4/7/16.
 */
public class ResponseParserTest {

    final ResponseParser responseParser = new ResponseParser();

    @Test
    public void testGetBooleanResponseParser() throws Exception{
        final HashMap addRosterItemResponse = getResponseObject(getClass().getResourceAsStream("/add_roster_item_response.xml"));
        final BooleanXmppResponse booleanXmppResponse = responseParser.parseBooleanResponse(addRosterItemResponse);
        Assert.assertTrue(booleanXmppResponse.isSuccessFull());
    }

    @Test
    public void testGetUserResponseParser() throws Exception{
        final HashMap getUsersResponseMap = getResponseObject(getClass().getResourceAsStream("/get_users_response.xml"));
        final GetUsersResponse getUsersResponse = responseParser.parseGetUserResponse(getUsersResponseMap);
        Assert.assertEquals(4, getUsersResponse.getUserList().size());
    }

    @Test
    public void testGetRosterResponseParser() throws Exception{
        final HashMap addUserResponse = getResponseObject(getClass().getResourceAsStream("/get_roster_response.xml"));
        final GetRosterResponse getRosterResponse = responseParser.parseGetRosterResponse(addUserResponse);
        Assert.assertEquals(3, getRosterResponse.getRosterItemList().size());
    }

    private HashMap getResponseObject(InputStream file) throws XmlRpcException, IOException, SAXException {
        final XMLReader xr = SAXParsers.newXMLReader();
        XmlRpcResponseParser xp = new XmlRpcResponseParser(new XmlRpcHttpRequestConfigImpl(), new TypeFactoryImpl(new XmlRpcClient()));
        xr.setContentHandler(xp);
        xr.parse(new InputSource(file));
        return (HashMap) xp.getResult();
    }

}