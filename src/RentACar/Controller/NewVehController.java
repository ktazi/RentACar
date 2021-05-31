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

public class NewVehController {
    public TextField mat;
    public TextField kilo;
    public TextField carb;
    public TextField modele;
    public TextField marque;
    public Button buttonAddVeh;
    public CheckBox clim;
    public CheckBox vit;
    public ChoiceBox<String> cat;

    public void addVeh(ActionEvent actionEvent) {

        Vehicule vehicule = new Vehicule(mat.getText(),carb.getText(),Integer.parseInt(kilo.getText()),false,vit.isSelected(),clim.isSelected(),modele.getText(),marque.getText(),cat.getValue());
        String strSql = "SELECT * FROM modele WHERE modele = '" + vehicule.getModele() + "' and marque = '" + vehicule.getMarque() + "';";
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
            PopUp.popup("Erreur dans l'ajout du vehicule", (Stage)clim.getScene().getWindow(), true);

        }

        String url = props.getProperty("jdbc.url");
        String login = props.getProperty("jdbc.login");
        String password = props.getProperty("jdbc.password");
        try {
        Connection connection = DriverManager.getConnection(url, login, password);
       Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSql);
            if (!resultSet.next()) {
                strSql = "INSERT INTO modele (modele, marque) " +
                        "VALUES ('" + vehicule.getModele() + "','" + vehicule.getMarque() + "');";

                try (Statement stmt2 = connection.createStatement()) {
                    stmt2.executeUpdate(strSql);
                } catch (SQLException e) {
                    e.printStackTrace();
                    PopUp.popup("Erreur dans l'ajout du vehicule", (Stage)clim.getScene().getWindow(), true);

                }
            }
            strSql = "INSERT INTO vehicule (matricule, kilometrage, location, boiteManuelle, climatise, typeCarburant, typeCategorie, idAgence, modele) VALUES ('" +
                    vehicule.getMatricule() + "'," + vehicule.getKilometrage() + "," + false + "," +
                    vehicule.isBoiteMan() + "," + vehicule.isClim() + ",'" + vehicule.getTypeCarburant() + "','" +
                    vehicule.getCategorie() + "'," + 1 + ",'" + vehicule.getModele() + "');";
            Statement stmt3 = connection.createStatement();
                stmt3.executeUpdate(strSql);
            } catch (SQLException e) {
                e.printStackTrace();
            PopUp.popup("Erreur dans l'ajout du vehicule", (Stage)clim.getScene().getWindow(), true);

        }


    }

}
