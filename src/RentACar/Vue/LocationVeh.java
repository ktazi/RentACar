package RentACar.Vue;

import RentACar.Controller.PopUp;
import RentACar.Controller.Session;
import RentACar.Model.Client;
import RentACar.Model.Vehicule;
import RentACar.Vue.Interface.ClientResearchObserver;
import RentACar.Vue.Interface.VehiculeResearchObserver;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

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

            ChoiceBox<String> assurances = (ChoiceBox<String>)((GridPane)((VBox)(getChildren().get(2))).getChildren().get(1)).getChildren().get(5);

            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream("Config/conf.properties")) {
                props.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Class.forName(props.getProperty("jdbc.driver.class"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String url = props.getProperty("jdbc.url");
            String login = props.getProperty("jdbc.login");
            String password = props.getProperty("jdbc.password");
            try {
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery("select type_assurance from assurance;");
                ArrayList<String> s = new ArrayList<>();
                while (resultSet.next()){
                    s.add(resultSet.getString("type_assurance"));
                }
                assurances.getItems().clear();
                assurances.getItems().setAll(s);
                assurances.setValue(assurances.getItems().get(0));
            }
            catch (SQLException e){
                e.printStackTrace();
            }


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