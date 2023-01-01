package application.gui.controller;

import application.model.Avatar;
import application.gui.controller.windows.InterfaceWindow;
import application.model.User;
import application.model.exceptions.ValidationException;
import application.repository.file.AvatarRepositoryFile;
import application.service.exceptions.AlreadyExistsException;
import application.utils.Animations;
import application.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class SettingsController extends InterfaceWindow {
    private Avatar avatar;
    public ImageView avatarImage;
    @FXML
    public FlowPane avatarsPane;
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
    public TextArea biographyField;
    @FXML
    public Text errorText;
    @FXML
    public void initialize() {
        setCurrentInterfaceWindow(this);

        firstNameField.setText(null);
        lastNameField.setText(null);
        mailAddressField.setText(null);
        passwordField.setText(null);
        confirmPasswordField.setText(null);
        birthDatePicker.setValue(null);
        errorText.setText(null);

        User user =  networkService.getCurrentUser();
        avatarImage.setImage(user.getAvatar().getPhoto());
        firstNameField.setPromptText(user.getFirstName());
        lastNameField.setPromptText(user.getLastName());
        mailAddressField.setPromptText(user.getMailAddress().toString());
        birthDatePicker.setPromptText(user.getBirthDate().format(Constants.DATE_FORMATTER));
        biographyField.setPromptText(user.getBiography());

        avatarsPane.getChildren().clear();

        double imageLength = 63;
        AvatarRepositoryFile.getInstance().findAll().forEach(avatar -> {
            ImageView imageView = new ImageView(avatar.getPhoto());
            imageView.setFitHeight(imageLength);
            imageView.setFitWidth(imageLength);
            imageView.setCursor(Cursor.HAND);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    event -> {
                        this.avatar = avatar;
                        this.avatarImage.setImage(avatar.getPhoto());
                        System.out.println(avatar);
                    });
            avatarsPane.getChildren().add(imageView);
        });

        Animations.bounceTransition(avatarImage).play();
    }

    @FXML
    public void handleUpdateButton() {
        try {
            if (passwordField.getText() != null && !passwordField.getText().equals(confirmPasswordField.getText())) {
                throw new ValidationException("The passwords don't match");
            }

            User currentUser = networkService.getCurrentUser();
            networkService.userService.update(currentUser,
                    mailAddressField.getText(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    passwordField.getText(),
                    birthDatePicker.getValue(),
                    biographyField.getText(),
                    avatar);
        } catch (ValidationException | AlreadyExistsException e) {
            errorText.setText(e.getMessage());
        }
    }

    @Override
    public void refresh() throws IOException {
        initialize();
    }
}
