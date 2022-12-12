package application.gui.controller;

import application.domain.exceptions.ValidationException;
import application.gui.SocialNetworkApplication;
import application.gui.controller.windows.ApplicationWindow;
import application.service.exceptions.NotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class LoginController extends ApplicationWindow {
    @FXML
    public TextField mailField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Text errorText;

    @FXML
    public void initialize() {
        errorText.setText("");
    }

    public void handleLoginButton() throws IOException {
        try {
            networkService.login(mailField.getText(), passwordField.getText());
            FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/interface.fxml"));
            changeScene(loader.load());
        } catch (NotFoundException e) {
            errorText.setText(e.getMessage());
        }
    }
    public void handleRegisterHyperlink() throws IOException {
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/register.fxml"));
        changeScene(loader.load());
    }
}
