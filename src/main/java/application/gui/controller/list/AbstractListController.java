package application.gui.controller.list;

import application.gui.SocialNetworkApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public abstract class AbstractListController<E, C extends AbstractCardController<E>> {
    private List<E> entities;
    private String location;
    public VBox container;

    public void setGUIElements(String location, VBox container) {
        this.location = location;
        this.container = container;

    }
    public void setEntities(List<E> entities) {
        this.entities = entities;
    }

    public void build() throws IOException {
        container.getChildren().clear();

        if (entities.isEmpty()) {
            FXMLLoader loader = new FXMLLoader(SocialNetworkApplication.class.getResource("fxml/not-found.fxml"));
            container.getChildren().add(loader.load());
        }

        for (E entity : entities) {
            FXMLLoader fxmlLoader = new FXMLLoader(SocialNetworkApplication.class.getResource(location));
            AnchorPane entityPane = fxmlLoader.load();
            fxmlLoader.<C>getController().setEntity(entity);
            fxmlLoader.<C>getController().build();
            container.getChildren().add(entityPane);
        }
    }
}
