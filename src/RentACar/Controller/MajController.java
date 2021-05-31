package RentACar.Controller;

import RentACar.Model.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MajController {
    public TextField surname;
    public TextField name;
    public TextField email;
    public TextField phone;
    public TextField street;
    public TextField city;
    public TextField post;
    public Button buttonMAJClient;

    public void majClient(ActionEvent actionEvent) {

        Client c = Session.getInstance().getCli();
        if (c == null)
            PopUp.popup("Veuillez Selectionner un client", (Stage)(email.getScene().getWindow()), true);
        else {
            c.setNom(surname.getText());
            c.setPrenom(name.getText());
            c.setCodePostal(Integer.parseInt(post.getText()));
            c.setEmail(email.getText());
            c.setNumTel(phone.getText());
            c.setRue(street.getText());
            c.setVille(city.getText());

            String strSql = "UPDATE client SET nom_client = '" + c.getNom() +  "', prenom_client ='" + c.getPrenom() + "', email_client = '" + c.getEmail() + "', rue_client = '" +
                    c.getRue() + "', ville_client = '" + c.getVille() + "', codePostal_client = '" + c.getCodePostal() + "', numTel_client = '" + c.getNumTel() + "' WHERE idClient ='" + c.getId() + "';";

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
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, login, password);
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(strSql);
            } catch (SQLException throwables) {
                PopUp.popup("Probleme lors de la requete", (Stage)(email.getScene().getWindow()), true);
                throwables.printStackTrace();
            }

        }


    }

    public void setClient(Client c){
        surname.setText(c.getNom());
        name.setText(c.getPrenom());
        phone.setText(c.getNumTel());
        email.setText(c.getEmail());
        street.setText(c.getRue());
        city.setText(c.getVille());
        post.setText(Integer.toString(c.getCodePostal()));

    }

}
