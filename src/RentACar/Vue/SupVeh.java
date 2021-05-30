package RentACar.Vue;

import RentACar.Controller.Session;
import RentACar.Model.Vehicule;
import RentACar.Vue.Interface.VehiculeResearchObserver;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SupVeh extends VBox implements VehiculeResearchObserver {
    public SupVeh() {
        super();
        try {
            setAlignment(Pos.CENTER);
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheVeh.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("supVeh.fxml")));
            Session.getInstance().setVehiculeResearchObserver(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notify(Vehicule v) {

    }
}