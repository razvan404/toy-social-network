package application.controller;

import application.controller.exceptions.NoResultsException;
import application.controller.windows.ApplicationWindow;
import application.domain.User;
import application.gui.SocialNetworkApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class InterfaceController extends ApplicationWindow {
    @FXML
    public AnchorPane searchListPane;
    @FXML
    public Button homeButton;
    @FXML
    public TextField searchTextField;
    @FXML
    public Button friendsButton;
    @FXML
    public Button yourAccountButton;
    @FXML
    public Button logOutButton;
    @FXML
    public AnchorPane mainAnchorPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ApplicationWindow.setInterfaceController(this);
    }

    public void setMainAnchorContent(Node content) {
        mainAnchorPane.getChildren().setAll(content);
        AnchorPane.setBottomAnchor(content, 0d);
        AnchorPane.setLeftAnchor(content, 0d);
        AnchorPane.setRightAnchor(content, 0d);
        AnchorPane.setTopAnchor(content, 0d);
    }

    public void homeButtonClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("home-view.fxml"));
        AnchorPane homePane = loader.load();

        setMainAnchorContent(homePane);
    }

    public void searchFieldChanged() throws IOException {
        searchListPane.setVisible(true);
        String searchEntry = searchTextField.getText();
        if (searchEntry != null && !searchEntry.equals("")) {
            FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("user-list-view.fxml"));
            AnchorPane searchedPane = loader.load();
            loader.<UserListController>getController().setFriendList(networkService.findByName(searchEntry));
            loader.<UserListController>getController().build();

            searchListPane.getChildren().setAll(searchedPane);
            AnchorPane.setBottomAnchor(searchedPane, 0d);
            AnchorPane.setLeftAnchor(searchedPane, 0d);
            AnchorPane.setRightAnchor(searchedPane, 0d);
            AnchorPane.setTopAnchor(searchedPane, 0d);
        }
        else {
            optionChanged();
        }
    }

    public void friendsButtonClicked() throws IOException {
        optionChanged();
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("user-list-view.fxml"));
        AnchorPane friendsPane = loader.load();
        loader.<UserListController>getController().setFriendList(networkService.findFriendsOf(networkService.getCurrentUser().getID()));
        loader.<UserListController>getController().build();

        setMainAnchorContent(friendsPane);
    }

    public void yourAccountButtonClicked() throws IOException {
        optionChanged();
        showUserProfile(networkService.getCurrentUser().getID());
    }

    public void logOutButtonClicked() throws IOException {
        optionChanged();
        FXMLLoader fxmlLoader = new FXMLLoader(SocialNetworkApplication.class.getResource("login-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        changeScene(root);
    }

    public void showFriendsInCommon(UUID userID) throws IOException {
        optionChanged();
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("user-list-view.fxml"));
        AnchorPane commonFriendsPane = loader.load();
        loader.<UserListController>getController().setFriendList(networkService.findCommonFriends(userID));
        loader.<UserListController>getController().build();

        setMainAnchorContent(commonFriendsPane);
    }

    public void showUserProfile(UUID userID) throws IOException {
        optionChanged();
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("profile-view.fxml"));
        AnchorPane commonFriendsPane = loader.load();
        loader.<ProfileController>getController().setUser(userID);
        loader.<ProfileController>getController().build();

        setMainAnchorContent(commonFriendsPane);
    }

    public void showFriendListOf(UUID user) throws IOException {
        optionChanged();
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("user-list-view.fxml"));
        AnchorPane friendsPane = loader.load();
        loader.<UserListController>getController().setFriendList(networkService.findFriendsOf(user));
        loader.<UserListController>getController().build();

        setMainAnchorContent(friendsPane);
    }

    public void showNotFound(AnchorPane parent) throws IOException {
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("not-found-view.fxml"));
        AnchorPane notFoundPane = loader.load();
        parent.getChildren().setAll(notFoundPane);
        AnchorPane.setBottomAnchor(notFoundPane, 0d);
        AnchorPane.setLeftAnchor(notFoundPane, 0d);
        AnchorPane.setRightAnchor(notFoundPane, 0d);
        AnchorPane.setTopAnchor(notFoundPane, 0d);
    }

    void optionChanged() {
        searchTextField.clear();
        searchListPane.setVisible(false);
    }
}
