package application.gui.controller;

import application.domain.Friend;
import application.gui.SocialNetworkApplication;
import application.gui.controller.list.UserListController;
import application.gui.controller.windows.InterfaceWindow;
import application.utils.Animations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class InterfaceController extends InterfaceWindow {
    private boolean isToolBarExtended = true;
    @FXML
    public AnchorPane mainSection;
    @FXML
    public TextField searchField;
    @FXML
    public AnchorPane searchSection;
    @FXML
    public AnchorPane toolBar;

    @FXML
    public void initialize() throws IOException {
        if (mainSection != null) {
            setInterfaceController(this);
            searchSection.setPrefHeight(0);
            handleMenuButton();
            handleHomeButton();
        }
    }

    public void handleChangeWindow() throws IOException {
        searchSection.getChildren().clear();
        if (isToolBarExtended) {
            handleMenuButton();
        }
    }

    public void handleSearchField() throws IOException {
        String searchEntry = searchField.getText();

        if (searchEntry != null && !searchEntry.equals("")) {
            List<Friend> resultList = networkService.findByName(searchEntry);

            FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/list/user-list.fxml"));
            AnchorPane searchedPane = loader.load();

            loader.<UserListController>getController().setEntities(resultList);
            loader.<UserListController>getController().build();

            Animations.changeHeightTransition(searchSection, searchSection.getHeight(), searchedPane.getHeight()).play();

            searchSection.getChildren().setAll(searchedPane);
            AnchorPane.setBottomAnchor(searchedPane, 0d);
            AnchorPane.setLeftAnchor(searchedPane, 0d);
            AnchorPane.setRightAnchor(searchedPane, 0d);
            AnchorPane.setTopAnchor(searchedPane, 0d);
        }
        else {
            Animations.changeHeightTransition(searchSection, searchSection.getHeight(), 0).play();
            searchSection.getChildren().clear();
        }
    }

    public void handleProfileButton() throws IOException {
        showUserProfile(networkService.getCurrentUser());
    }

    public void handleSignOutButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/login.fxml"));
        changeScene(loader.load());
    }

    public void handleMenuButton() throws IOException {
        if (!isToolBarExtended) {
            Animations.changeWidthTransition(toolBar, 50, 200).play();
            Animations.changeBlurTransition(mainPane, 0, 8).play();
            mainPane.setDisable(true);
            isToolBarExtended = true;
        }
        else {
            Animations.changeWidthTransition(toolBar, 200, 50).play();
            Animations.changeBlurTransition(mainPane, 8, 0).play();
            mainPane.setDisable(false);
            isToolBarExtended = false;
        }
    }

    public void handleHomeButton() throws IOException {
        handleChangeWindow();
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/home.fxml"));
        setMainPaneContent(loader.load());
    }

    public void showUserProfile(Friend friend) throws IOException {
        handleChangeWindow();
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/profile.fxml"));
        AnchorPane userProfile = loader.load();
        loader.<ProfileController>getController().setUser(friend);
        loader.<ProfileController>getController().build();

        setMainPaneContent(userProfile);
    }
}
