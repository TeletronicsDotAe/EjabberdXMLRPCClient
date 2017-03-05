package ae.teletronics.ejabberd.entity.response;


import ae.teletronics.ejabberd.entity.UserPair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jensrjorgensen on 22/02/2017.
 */
public class GetUserPairListResponse extends ErrorResponse {
    List<UserPair> userPairList;

    public GetUserPairListResponse() {
        this.userPairList = new ArrayList<>();
    }

    public GetUserPairListResponse(List<UserPair> userPairList) {
        this.userPairList = userPairList;
    }

    public GetUserPairListResponse(String error) {
        super(error);
        this.userPairList = new ArrayList<>();
    }

    public List<UserPair> getUserPairList() {
        return this.userPairList;
    }

    public void gstUserPairList(List<UserPair> userPairList) {
        this.userPairList = userPairList;
    }
}
