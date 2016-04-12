package ae.teletronics.ejabberd.entity.response;

/**
 * Created by kristian on 4/7/16.
 */
public class CreateUserResponse extends BooleanXmppResponse{

    public CreateUserResponse(boolean successFull) {
        super(successFull);
    }

    public CreateUserResponse(int xmppRes) {
        super(xmppRes);
    }
}
