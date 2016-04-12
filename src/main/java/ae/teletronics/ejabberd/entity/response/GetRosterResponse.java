package ae.teletronics.ejabberd.entity.response;

import ae.teletronics.ejabberd.entity.RosterItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kristian on 4/7/16.
 */
public class GetRosterResponse extends ErrorResponse{

    List<RosterItem> rosterItemList;

    public GetRosterResponse() {
        this.rosterItemList = new ArrayList<>();
    }

    public GetRosterResponse(List<RosterItem> rosterItemList) {
        this.rosterItemList = rosterItemList;
    }

    public GetRosterResponse(String error) {
        super(error);
        this.rosterItemList = new ArrayList<>();
    }

    public List<RosterItem> getRosterItemList() {
        return rosterItemList;
    }

    public void setRosterItemList(List<RosterItem> rosterItemList) {
        this.rosterItemList = rosterItemList;
    }
}
