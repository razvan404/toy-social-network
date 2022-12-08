package application.controller;

import application.controller.windows.ApplicationWindow;
import application.gui.SocialNetworkApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends ApplicationWindow {
    @FXML
    public VBox mainVBox;
    @FXML
    public TextField mailField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Text errorText;
    @FXML
    public Button loginButton;
    @FXML
    public Hyperlink registerHyperlink;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void loginButtonClicked() {
        try {
            networkService.login(mailField.getText(), passwordField.getText());
            Stage stage = ((Stage) loginButton.getScene().getWindow());

            FXMLLoader fxmlLoader = new FXMLLoader(SocialNetworkApplication.class.getResource("interface-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            errorText.setText(e.getMessage());
        }
    }

    public void registerHyperlinkClicked() throws IOException {
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("register-view.fxml"));
        AnchorPane root = loader.load();
        changeScene(root);
    }
}
