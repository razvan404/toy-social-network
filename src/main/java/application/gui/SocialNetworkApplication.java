package application.gui;

import application.gui.controller.windows.ApplicationWindow;

import application.repository.database.FriendshipRepositoryDB;
import application.repository.database.UserRepositoryDB;
import application.service.FriendshipService;
import application.service.NetworkService;
import application.service.UserService;
import application.utils.database.NetworkDataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SocialNetworkApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        ApplicationWindow.setApplicationStage(stage);
        ApplicationWindow.setNetworkService(new NetworkService(
                new UserService(new UserRepositoryDB(NetworkDataBase.getInstance())),
                new FriendshipService(new FriendshipRepositoryDB(NetworkDataBase.getInstance()))
        ));

        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/login.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.show();
    }
}
