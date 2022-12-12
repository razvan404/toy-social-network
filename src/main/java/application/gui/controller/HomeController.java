package application.gui.controller;

import application.gui.controller.windows.InterfaceWindow;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class HomeController extends InterfaceWindow {
    @FXML
    public Text usersCount;
    @FXML
    public Text friendshipsCount;

    @FXML
    public void initialize() {
        usersCount.setText(String.valueOf(networkService.userService.size()));
        friendshipsCount.setText(String.valueOf(networkService.friendshipService.size()));
    }
}
