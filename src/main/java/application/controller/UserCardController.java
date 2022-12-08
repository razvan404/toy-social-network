package application.controller;

import application.controller.windows.ApplicationWindow;
import application.domain.Friend;
import application.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserCardController extends ApplicationWindow {
    private Friend friend;
    @FXML
    public ImageView userPhoto;
    @FXML
    public Label userNameLabel;

    @FXML
    public Label friendsFromLabel;

    @FXML
    public Label commonFriendsLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    void setFriend(Friend friend) {
        this.friend = friend;
        userNameLabel.setText(friend.getName());
        if (friend.getFriendsFrom() != null) {
            friendsFromLabel.setText("Friends from: " + friend.getFriendsFrom().format(Constants.DATE_FORMATTER));
        }
        else {
            friendsFromLabel.setText("");
        }
        if (friend.getCommonFriends() == 0) {
            commonFriendsLabel.setText("");
        }
        else {
            commonFriendsLabel.setText("Friends in common: " + friend.getCommonFriends());
        }
    }

    public void userPhotoClicked() throws IOException {
        interfaceController.showUserProfile(friend.getID());
    }

    public void friendsInCommonClicked() throws IOException {
        interfaceController.showFriendsInCommon(friend.getID());
    }


}
