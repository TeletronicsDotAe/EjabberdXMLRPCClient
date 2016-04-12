package ae.teletronics.ejabberd.entity.response;

import ae.teletronics.ejabberd.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristian on 4/7/16.
 */
public class GetUsersResponse extends ErrorResponse{
    List<User> userList = new ArrayList<>();

    public GetUsersResponse() {
    }

    public GetUsersResponse(String error) {
        super(error);
    }

    public GetUsersResponse(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
