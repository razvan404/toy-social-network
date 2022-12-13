package application.gui.controller.windows;

import application.service.NetworkService;
import application.utils.Constants;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ApplicationWindow {
    protected static NetworkService networkService;
    protected static Stage applicationStage;

    public static void setNetworkService(NetworkService networkService) {
        ApplicationWindow.networkService = networkService;
    }

    public static void setApplicationStage(Stage applicationStage) {
        ApplicationWindow.applicationStage = applicationStage;
        ApplicationWindow.applicationStage.getIcons().setAll(Constants.APP_ICON);
        ApplicationWindow.applicationStage.setTitle("Zoop!");
        ApplicationWindow.applicationStage.setMinWidth(800);
        ApplicationWindow.applicationStage.setMinHeight(600);
    }

    public static void changeScene(Parent root) {
        double oldWidth = applicationStage.getWidth();
        double oldHeight = applicationStage.getHeight();
        Scene scene = new Scene(root);
        applicationStage.setScene(scene);
        applicationStage.setWidth(oldWidth);
        applicationStage.setHeight(oldHeight);
        applicationStage.setMinWidth(800);
        applicationStage.setMinHeight(600);
    }
}
