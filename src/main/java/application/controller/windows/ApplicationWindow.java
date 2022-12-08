package application.controller.windows;

import application.controller.InterfaceController;
import application.service.NetworkService;
import application.utils.Constants;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class ApplicationWindow implements Initializable {
    protected static NetworkService networkService;
    protected static InterfaceController interfaceController;
    protected static Stage interfaceMainStage;

    public static void setNetworkService(NetworkService networkService) {
        ApplicationWindow.networkService = networkService;
    }

    public static void setInterfaceController(InterfaceController interfaceController) {
        ApplicationWindow.interfaceController = interfaceController;
    }

    public static void setInterfaceMainStage(Stage stage) {
        interfaceMainStage = stage;
        interfaceMainStage.getIcons().setAll(Constants.APP_ICON);
        interfaceMainStage.setTitle("Toy Social Network");
        interfaceMainStage.setMinWidth(800);
        interfaceMainStage.setMinHeight(600);
    }

    public void changeScene(Parent root) {
        double oldWidth = interfaceMainStage.getWidth();
        double oldHeight = interfaceMainStage.getHeight();
        Scene scene = new Scene(root);
        interfaceMainStage.setScene(scene);
        interfaceMainStage.setWidth(oldWidth);
        interfaceMainStage.setHeight(oldHeight);
        interfaceMainStage.setMinWidth(800);
        interfaceMainStage.setMinHeight(600);
    }
}
