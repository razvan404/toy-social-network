package application.controller;

import application.controller.exceptions.NoResultsException;
import application.controller.windows.ApplicationWindow;
import application.domain.Friend;
import application.domain.User;
import application.gui.SocialNetworkApplication;
import application.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.UUID;

public class ProfileController extends ApplicationWindow {
    private Friend friend;
    private User user;
    @FXML
    public ImageView profileImage;
    @FXML
    public Text userNameText;
    @FXML
    public Text biographyText;
    @FXML
    public Text birthDateText;
    @FXML
    public Label registerLabel;
    @FXML
    public Label friendsFromLabel;
    @FXML
    public Label commonFriendsLabel;
    @FXML
    public Button profileButton;
    @FXML
    public AnchorPane friendListPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setUser(UUID userID) {
        this.friend = networkService.findFriend(userID);
        this.user = networkService.userService.find(userID);
    }

    public void build() throws IOException {
        userNameText.setText(user.getName());
        if (user.getBiography() != null) {
            biographyText.setText(user.getBiography());
        }
        else {
            biographyText.setText("");
        }
        birthDateText.setText("Born in: " + user.getBirthDate().format(Constants.DATE_FORMATTER) + " (" +
                ChronoUnit.YEARS.between(user.getBirthDate(), LocalDate.now()) + " years old)");
        registerLabel.setText("Member from: " + user.getRegisterDate().format(Constants.DATE_FORMATTER));

        if (friend.getCommonFriends() == 0) {
            commonFriendsLabel.setText("");
        }
        else {
            commonFriendsLabel.setText("Common friends (" + friend.getCommonFriends() + ")");
        }
        if (friend.getFriendsFrom() != null) {
            friendsFromLabel.setText("Friends from: " + friend.getFriendsFrom().format(Constants.DATE_TIME_FORMATTER));
        }
        else {
            friendsFromLabel.setText("");
        }
        if (networkService.getCurrentUser().getID().equals(user.getID())) {
            profileButton.setText("Account settings");
        }
        else if (networkService.findFriendsOf(networkService.getCurrentUser().getID())
                .stream().map(Friend::getID).toList().contains(user.getID())) {
            profileButton.setText("Remove friend");
        }
        else {
            profileButton.setText("Send friend request");
        }

        friendsListClicked();
    }

    public void friendsInCommonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("user-list-view.fxml"));
        AnchorPane friendsPane = loader.load();
        loader.<UserListController>getController().setFriendList(networkService.findCommonFriends(user.getID()));
        loader.<UserListController>getController().build();

        friendListPane.getChildren().setAll(friendsPane);
        AnchorPane.setBottomAnchor(friendsPane, 0d);
        AnchorPane.setLeftAnchor(friendsPane, 0d);
        AnchorPane.setRightAnchor(friendsPane, 0d);
        AnchorPane.setTopAnchor(friendsPane, 0d);
    }

    public void friendsListClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("user-list-view.fxml"));
        AnchorPane friendsPane = loader.load();
        loader.<UserListController>getController().setFriendList(networkService.findFriendsOf(user.getID()));
        loader.<UserListController>getController().build();

        friendListPane.getChildren().setAll(friendsPane);
        AnchorPane.setBottomAnchor(friendsPane, 0d);
        AnchorPane.setLeftAnchor(friendsPane, 0d);
        AnchorPane.setRightAnchor(friendsPane, 0d);
        AnchorPane.setTopAnchor(friendsPane, 0d);
    }
}
