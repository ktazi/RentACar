package RentACar.Controller;

import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SupVehController {
    public Button button;

    public void supVehi(ActionEvent actionEvent) {

        Vehicule vehicule = Session.getInstance().getVeh();
        if (vehicule == null){
            PopUp.popup("Veuillez Selectionner un vehicule a supprimer", (Stage)(button.getScene().getWindow()), true);
        }
        else {

            String strSql = "DELETE FROM vehicule WHERE matricule = '" + Session.getInstance().getVeh().getMatricule() + "';";
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

            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(strSql);
                PopUp.popup("Vehicule supprime avec succes", (Stage)(button.getScene().getWindow()), true);

            } catch (SQLException e) {
                PopUp.popup("Erreur lors de la suppression", (Stage)(button.getScene().getWindow()), true);
                e.printStackTrace();
            }

        }
    }
}
