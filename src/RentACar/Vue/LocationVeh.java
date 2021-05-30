package RentACar.Vue;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LocationVeh extends VBox {
    public LocationVeh() {
        super();
        try {
            setAlignment(Pos.CENTER);
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheVeh.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheClient.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("Location.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}