package RentACar.Vue;

import RentACar.Model.Client;
import RentACar.Vue.Interface.ClientResearchObserver;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RetourVeh extends VBox implements ClientResearchObserver {
    private Client client;
    public RetourVeh() {
        super();
        try {
            setAlignment(Pos.CENTER);
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheVeh.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("RetourVeh.fxml")));
            client = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notify(Client c) {
        this.client = c;
    }
}
