package application.controller.list;

import application.controller.exceptions.NoResultsException;
import application.controller.windows.InterfaceWindow;
import application.gui.SocialNetworkApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public abstract class ListController<E, C extends ListElementController<E>> extends InterfaceWindow {
    private String fxmlLocation;
    private List<E> entities;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public VBox vBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setEntities(List<E> entities) {
        this.entities = entities;
    }

    public abstract URL getFXML();

    public void build() throws NoResultsException {
        vBox.getChildren().clear();
        if (entities.isEmpty()) {
            throw new NoResultsException();
        }
        entities.forEach(entity -> {
            FXMLLoader fxmlLoader = new FXMLLoader(getFXML());
            try {
                AnchorPane entityPane = fxmlLoader.load();
                fxmlLoader.<C>getController().setEntity(entity);
                entityPane.setPadding(new Insets(5));
                vBox.getChildren().add(entityPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
