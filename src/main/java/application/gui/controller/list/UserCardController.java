package application.gui.controller.list;

import application.model.Friend;
import application.utils.Constants;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class UserCardController extends AbstractCardController<Friend> {
    private Friend friend;
    @FXML
    public ImageView userPhoto;
    @FXML
    public Text userNameText;
    @FXML
    public Text friendsFromText;
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
        if (friend.getFriendsFrom() != null) {
            friendsFromText.setText("Friends from: " + friend.getFriendsFrom().format(Constants.DATE_FORMATTER));
        }
        else {
            friendsFromText.setText("");
        }
        if (friend.getCommonFriends() == 0) {
            inCommonText.setText("");
        }
        else {
            inCommonText.setText("Common friends: " + friend.getCommonFriends());
        }
    }

    public void handleElementClicked() throws IOException {
        interfaceController.showUserProfile(friend);
    }

    @Override
    public void refresh() throws IOException {
        build();
    }
}
