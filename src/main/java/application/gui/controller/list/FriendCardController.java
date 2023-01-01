package application.gui.controller.list;

import application.gui.controller.windows.ApplicationWindow;
import application.model.Friend;
import application.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class FriendCardController extends AbstractCardController<Friend> {
    private Friend friend;
    @FXML
    public ImageView userPhoto;
    @FXML
    public Text userNameText;
    @FXML
    public Text inCommonText;

    @Override
    public void setEntity(Friend friend) {
        this.friend = friend;
    }

    @Override
    public void build() {
        userNameText.setText(friend.getName());
        userPhoto.setImage(friend.getAvatar().getPhoto());
        if (friend.getCommonFriends() == 0) {
            inCommonText.setText("");
        }
        else {
            inCommonText.setText(friend.getCommonFriends() + " friends in common");
        }
    }

    public void handleElementClicked() throws IOException {
        interfaceController.handleFriendsButton(friend);
    }

    @Override
    public void refresh() throws IOException {
        build();
    }
}
