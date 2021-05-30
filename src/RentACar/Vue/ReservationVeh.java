package RentACar.Vue;

import RentACar.Model.Client;
import RentACar.Vue.Interface.ClientResearchObserver;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ReservationVeh extends VBox implements ClientResearchObserver {
    public ReservationVeh() {
        super();
        try {
            setAlignment(Pos.CENTER);
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheVeh.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheClient.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("ReservationVeh.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notify(Client c) {

    }
}