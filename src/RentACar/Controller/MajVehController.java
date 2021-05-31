package RentACar.Controller;

import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MajVehController {

    public Button buttonMajVeh;
    public TextField mat;
    public TextField kilo;
    public TextField carb;
    public TextField modele;
    public ChoiceBox<String> cat;
    public CheckBox vit;
    public CheckBox clim;
    public TextField marque;

    public void majVeh(ActionEvent actionEvent) {
        Vehicule v = Session.getInstance().getVeh();
        if (v == null)
            PopUp.popup("Veuillez Selectionner un Vehicule", (Stage)(mat.getScene().getWindow()), true);
        else {
            v.setMatricule(mat.getText());
            v.setKilometrage(Integer.parseInt(kilo.getText()));
            v.setTypeCarburant(carb.getText());
            v.setModele(modele.getText());
            v.setClim(clim.isSelected());
            v.setBoiteMan(vit.isSelected());
            v.setMarque(marque.getText());
            v.setCategorie(cat.getValue());
            String strSql = "UPDATE vehicule SET kilometrage ='" + v.getKilometrage() + "', boiteManuelle = " +
                    v.isBoiteMan() + ", climatise = " + v.isClim() + ", typeCarburant = '" + v.getTypeCarburant() +
                       "' WHERE matricule ='" + v.getMatricule() + "';";

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
                stmt.executeUpdate(strSql);
            } catch (SQLException throwables) {
                PopUp.popup("Probleme lors de la requete", (Stage)(carb.getScene().getWindow()), true);
                throwables.printStackTrace();
            }


        }

    }
}
