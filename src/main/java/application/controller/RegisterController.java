package application.controller;

import application.controller.windows.ApplicationWindow;
import application.domain.User;
import application.gui.SocialNetworkApplication;
import application.utils.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RegisterController extends ApplicationWindow {
    @FXML
    public VBox mainVBox;
    @FXML
    public TextField firstNameField;
    @FXML
    public TextField lastNameField;
    @FXML
    public TextField mailField;
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
    public Button registerButton;
    @FXML
    public Hyperlink loginHyperlink;

    public void initialize(URL location, ResourceBundle resourceBundle) {
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
        biographyField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            final String FOCUS_EVENT_TEXT = "TAB_TO_FOCUS_EVENT";
            if (!KeyCode.TAB.equals(event.getCode()))
            {
                return;
            }

            // handle events where the TAB key or TAB + CTRL key is pressed
            // so don't handle the event if the ALT, SHIFT or any other modifier key is pressed
            if (event.isAltDown() || event.isMetaDown() || event.isShiftDown())
            {
                return;
            }

            if (!(event.getSource() instanceof final TextArea textArea))
            {
                return;
            }

            if (event.isControlDown())
            {
                // if the event text contains the special focus event text
                // => do not consume the event, and let the default behaviour (= move focus to the next control) happen.
                //
                // if the focus event text is not present, then the user has pressed CTRL + TAB key,
                // then consume the event and insert or replace selection with tab character
                if (!FOCUS_EVENT_TEXT.equalsIgnoreCase(event.getText()))
                {
                    event.consume();
                    textArea.replaceSelection("\t");
                }
            }
            else
            {
                // The default behaviour of the TextArea for the CTRL+TAB key is a move of focus to the next control.
                // So we consume the TAB key event, and fire a new event with the CTRL + TAB key.
                event.consume();
                textArea.fireEvent(new KeyEvent(event.getSource(), event.getTarget(), event.getEventType(), event.getCharacter(),
                        FOCUS_EVENT_TEXT, event.getCode(), event.isShiftDown(), true, event.isAltDown(), event.isMetaDown()));
            }
        });
    }

    public void registerButtonClicked() {
        try {
            if (passwordField.getText() != null && !passwordField.getText().equals(confirmPasswordField.getText())) {
                throw new IllegalArgumentException("The passwords don't match");
            }
            User user = networkService.userService.save(
                    mailField.getText(),
                    firstNameField.getText(),
                    lastNameField.getText(),
                    passwordField.getText(),
                    birthDatePicker.getValue(),
                    biographyField.getText()
            );
            networkService.login(user.getMailAddress().toString(), user.getPassword());
            FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("interface-view.fxml"));
            changeScene(loader.load());
        } catch (Exception e) {
            errorText.setText(e.getMessage());
        }
    }

    public void loginHyperlinkClicked() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SocialNetworkApplication.class.getResource("login-view.fxml"));
        AnchorPane root = fxmlLoader.load();
        changeScene(root);
    }
}
