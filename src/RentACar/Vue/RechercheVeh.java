package RentACar.Vue;

import RentACar.Controller.Session;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class RechercheVeh extends HBox {
    public RechercheVeh() {
        super();
        try {
            Session.getInstance().getVehiculeEntry().clear();
            Parent root = FXMLLoader.load(getClass().getResource("RechercheVeh.fxml"));
            getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
