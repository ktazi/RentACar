package RentACar.Vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class RechercheClient extends HBox {
    public RechercheClient() {
        super();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("RechercheClient.fxml"));
            getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
