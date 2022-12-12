package application.gui.controller;

import application.domain.Friend;
import application.gui.SocialNetworkApplication;
import application.gui.controller.list.UserListController;
import application.utils.Animations;
import application.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ProfileController extends InterfaceController {
    private Friend friend;
    @FXML
    public Text userNameText;
    @FXML
    public Text friendsFromText;
    @FXML
    public Text memberSinceText;
    @FXML
    public Text birthDateText;
    @FXML
    public Text biographyTitle;
    @FXML
    public Text biographyText;
    @FXML
    public Text friendsText;
    @FXML
    public Text inCommonText;
    @FXML
    public Button profileButton;
    @FXML
    public AnchorPane friendsPane;

    public void setUser(Friend friend) {
        this.friend = friend;
    }

    public void build() throws IOException {
        userNameText.setText(friend.getName());
        if (friend.getFriendsFrom() != null) {
            friendsFromText.setText("Friends from: " + friend.getFriendsFrom().format(Constants.DATE_TIME_FORMATTER));
        }
        else {
            friendsFromText.setText("");
        }
        memberSinceText.setText(friend.getRegisterDate().format(Constants.DATE_FORMATTER) + " (" +
                ChronoUnit.DAYS.between(friend.getRegisterDate(), LocalDate.now()) + " days)");
        birthDateText.setText(friend.getBirthDate().format(Constants.DATE_FORMATTER) + " (" +
                ChronoUnit.YEARS.between(friend.getBirthDate(), LocalDate.now()) + " years)");

        if (friend.getBiography() != null) {
            biographyText.setText(friend.getBiography());
        }
        else {
            biographyText.setText("");
            biographyTitle.setText("");
        }


        if (friend.getCommonFriends() == 0) {
            inCommonText.setText("");
        }
        else {
            inCommonText.setText("Common friends (" + friend.getCommonFriends() + ")");
        }

        if (networkService.getCurrentUser().getID().equals(friend.getID())) {
            profileButton.getStyleClass().setAll("account-settings-button");
        }
        else if (networkService.findFriendsOf(networkService.getCurrentUser().getID())
                .stream().map(Friend::getID).toList().contains(friend.getID())) {
            profileButton.getStyleClass().setAll("remove-friend-button");
        }
        else {
            profileButton.getStyleClass().setAll("add-friend-button");
        }

        handleFriends();
    }

    public void handleFriends() throws IOException {
        List<Friend> resultList = networkService.findFriendsOf(friend.getID());

        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/list/user-list.fxml"));
        AnchorPane friendsPane = loader.load();

        loader.<UserListController>getController().setEntities(resultList);
        loader.<UserListController>getController().build();

        this.friendsPane.getChildren().setAll(friendsPane);
        AnchorPane.setLeftAnchor(friendsPane, 0d);
        AnchorPane.setRightAnchor(friendsPane, 0d);
        AnchorPane.setTopAnchor(friendsPane, 0d);
    }
}
