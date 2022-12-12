package application.gui.controller;

import application.domain.User;
import application.domain.exceptions.ValidationException;
import application.gui.SocialNetworkApplication;
import application.gui.controller.windows.ApplicationWindow;
import application.service.exceptions.NotFoundException;
import application.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;
import java.time.LocalDate;

public class RegisterController extends ApplicationWindow {
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField lastNameField;
    @FXML
    public TextField mailAddressField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confirmPasswordField;
    @FXML
    public DatePicker birthDatePicker;
    @FXML
    public Text errorText;

    @FXML
    public void initialize() {
        errorText.setText("");
        birthDatePicker.setConverter(new StringConverter<>() {
            public String toString(LocalDate date) {
                if (date != null) {
                    return Constants.DATE_FORMATTER.format(date);
                } else {
                    return "";
                }
            }
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, Constants.DATE_FORMATTER);
                } else {
                    return null;
                }
            }
        });
    }

    public void handleRegisterButton() throws IOException {
        try {
            if (passwordField.getText() != null && !passwordField.getText().equals(confirmPasswordField.getText())) {
                throw new ValidationException("The passwords don't match");
            }
            User user = networkService.userService.save(
                    mailAddressField.getText(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    passwordField.getText(),
                    birthDatePicker.getValue(),
                    null
            );
            networkService.login(user.getMailAddress().toString(), user.getPassword());
            FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/interface.fxml"));
            changeScene(loader.load());
        } catch (ValidationException | NotFoundException exception) {
            errorText.setText(exception.getMessage());
        }
    }

    public void handleLoginHyperlink() throws IOException {
        FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/login.fxml"));
        changeScene(loader.load());
    }
}
