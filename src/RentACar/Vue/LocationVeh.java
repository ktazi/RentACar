package RentACar.Vue;

import RentACar.Controller.Session;
import RentACar.Model.Client;
import RentACar.Model.Vehicule;
import RentACar.Vue.Interface.ClientResearchObserver;
import RentACar.Vue.Interface.VehiculeResearchObserver;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class LocationVeh extends VBox implements ClientResearchObserver, VehiculeResearchObserver {
    public LocationVeh() {
        super();
        try {
            Session.getInstance().getVehiculeEntry().clear();
            Session.getInstance().getClientEntry().clear();
            setAlignment(Pos.CENTER);
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheVeh.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheClient.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("Location.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notify(Client c) {

    }

    @Override
    public void notify(Vehicule v) {

    }
}