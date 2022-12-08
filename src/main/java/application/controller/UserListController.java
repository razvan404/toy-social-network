package application.controller;

import application.controller.windows.ApplicationWindow;
import application.domain.Friend;
import application.gui.SocialNetworkApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserListController extends ApplicationWindow {
    private Parent parent;
    private List<Friend> friendList;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public VBox friendsVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
    }

    public void setFriendList(List<Friend> friendList) {
        this.friendList = friendList;
    }

    public void build() throws IOException {
        friendsVBox.getChildren().clear();
        if (friendList.isEmpty()) {
            friendsVBox.setAlignment(Pos.CENTER);
            FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("not-found-view.fxml"));
            AnchorPane notFound = loader.load();
            friendsVBox.getChildren().add(notFound);
        }
        friendsVBox.setAlignment(Pos.TOP_LEFT);
        friendList.forEach(friend -> {
            FXMLLoader fxmlLoader = new FXMLLoader(SocialNetworkApplication.class.getResource("user-card-view.fxml"));
            try {
                AnchorPane userPane = fxmlLoader.load();
                fxmlLoader.<UserCardController>getController().setFriend(friend);
                userPane.setPadding(new Insets(5));
                friendsVBox.getChildren().add(userPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
