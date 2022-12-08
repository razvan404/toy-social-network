package application.controller.list;

import application.domain.Friend;
import application.gui.SocialNetworkApplication;

import java.net.URL;

public class UserListController extends ListController<Friend, UserSummaryController>{
    @Override
    public URL getFXML() {
        return SocialNetworkApplication.class.getResource("user-card-view.fxml");
    }
}
