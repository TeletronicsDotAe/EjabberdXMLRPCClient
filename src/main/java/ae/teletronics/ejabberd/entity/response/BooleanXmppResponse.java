package ae.teletronics.ejabberd.entity.response;

/**
 * Created by kristian on 4/7/16.
 */
public class BooleanXmppResponse extends ErrorResponse{

    public static int XMLRPC_SUCCES = 0;
    boolean successFull = false;

    public BooleanXmppResponse() {
    }

    public BooleanXmppResponse(boolean successFull) {
        this.successFull = successFull;
    }

    public BooleanXmppResponse(int xmppRes) {
        this.successFull = xmppRes == XMLRPC_SUCCES;
    }

    public BooleanXmppResponse(String error) {
        super(error);
    }

    public boolean isSuccessFull() {
        return successFull;
    }

    public void setSuccessFull(boolean successFull) {
        this.successFull = successFull;
    }
}
