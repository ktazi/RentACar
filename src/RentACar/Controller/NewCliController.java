package RentACar.Controller;

import RentACar.Model.Client;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class NewCliController {

    public TextField surname;
    public TextField name;
    public TextField email;
    public TextField phone;
    public TextField street;
    public TextField city;
    public TextField post;

    public void addClient(ActionEvent actionEvent) {
        boolean success = true;
        if (success){
            Client client = new Client.ClientBuilder().setEmail(email.getText()).setCodePostal(Integer.parseInt(post.getText())).setNom(surname.getText()).setPrenom(name.getText()).setNumTel(phone.getText()).setRue(street.getText()).setVille(city.getText()).build();
            //TODO : implementer ajout client
            try {
                addClient(client);
            } catch (SQLException throwables) {
                System.out.println(throwables.getSQLState());
                throwables.printStackTrace();
            }
            PopUp.popup("Client ajoute avec succees", (Stage)email.getScene().getWindow(), false);

        }
        else {
            PopUp.popup("Erreur dans l'ajout du client", (Stage)email.getScene().getWindow(), true);
        }
    }


    public static void addClient(Client cl) throws SQLException {

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
            String strSql = "SELECT MAX(idClient) as idClient FROM client"; // On récupère l'ancien ID max
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSql);
            resultSet.next();

            cl.setId( resultSet.getInt("idClient") + 1);

            String strSql2 = "INSERT INTO client (idClient, nom_client, prenom_client, email_client, rue_client, ville_client, codePostal_client, numTel_client, dureeDuProgramme, idProgrammeFidelite) " +
                    "VALUES ('" + cl.getId() + "','" + cl.getNom() + "','" + cl.getPrenom() + "','" + cl.getEmail() +
                    "','" + cl.getRue() + "','" + cl.getVille() + "','" + Integer.toString(cl.getCodePostal())  + "','" + cl.getNumTel() + "', 0, 0);";
            Statement stmt2 = connection.createStatement();
            stmt2.executeUpdate(strSql2);
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }

}
