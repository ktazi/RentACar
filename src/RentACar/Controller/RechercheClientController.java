package RentACar.Controller;

import RentACar.Model.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class RechercheClientController {

    public Text email;
    public ListView<String> list;
    public Text surname;
    public Text name;
    public Text phone;
    public Text street;
    public Text city;
    public Text post;
    public Button nameButton;
    public TextField surnameField;
    public CheckBox alpha;
    public TextField matriculeField;
    private Client client;

    public void nameSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        try {
            findClient(findClientNameReq(surnameField.getText(), alpha.isSelected()));
            PopUp.popup("Recherche reussie",(Stage)(city.getScene().getWindow()), false);
        } catch (SQLException throwables) {
            PopUp.popup("Recherche ratee",(Stage)(city.getScene().getWindow()), true);
        }
        refreshList();
    }

    public void carSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        try {
            findClient(findClientMatriculeReq(matriculeField.getText(), alpha.isSelected()));
            PopUp.popup("Recherche reussie",(Stage)(city.getScene().getWindow()), false);
        } catch (SQLException throwables) {
            PopUp.popup("Recherche ratee",(Stage)(city.getScene().getWindow()), true);
            throwables.printStackTrace();
        }
        refreshList();
    }

    public void locSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        try {
            findClient(findClientLocationReq(alpha.isSelected()));
            PopUp.popup("Recherche reussie",(Stage)(city.getScene().getWindow()), false);
        } catch (SQLException throwables) {
            PopUp.popup("Recherche ratee",(Stage)(city.getScene().getWindow()), true);
        }
        refreshList();
    }

    public void val(ActionEvent actionEvent) {
        if (client == null)
            PopUp.popup("Veuillez selectionner un client",(Stage)(email.getScene().getWindow()) ,true);
        else
            Session.getInstance().notifyResearchClientObserver(client);
    }

    public void allSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        try {
            findClient(findAllClientReq(alpha.isSelected()));
            PopUp.popup("Recherche reussie",(Stage)(city.getScene().getWindow()), false);
        } catch (SQLException throwables) {
            PopUp.popup("Recherche ratee",(Stage)(city.getScene().getWindow()), true);
        }
        refreshList();
    }

    private void refreshList(){
        ArrayList<String> clients = new ArrayList<>();
        this.client = null;
        for (Client cli : Session.getInstance().getClientEntry()){
            clients.add(cli.toString());
        }
        ObservableList<String> ob = FXCollections.observableArrayList(clients);
        list.setItems(ob);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (clients.indexOf(observable.getValue()) >= 0){
                putClient(Session.getInstance().getClientEntry().get(clients.indexOf(observable.getValue())));
                Session.getInstance().selCli(clients.indexOf(observable.getValue()));
            }
        });
    }

    private void putClient(Client c){
        surname.setText(c.getNom());
        name.setText(c.getPrenom());
        phone.setText(c.getNumTel());
        email.setText(c.getEmail());
        street.setText(c.getRue());
        city.setText(c.getVille());
        post.setText(Integer.toString(c.getCodePostal()));
        this.client = c;
    }


    public String findClientNameReq(String nom_client, boolean sorted){
        String  strSql = "SELECT * FROM client WHERE nom_client like '" + nom_client + "%' ";
        if (sorted)
            strSql += "Order by nom_client;";
        else
            strSql += ";";
        return strSql;
    }

    public String findClientMatriculeReq(String matricule, boolean sorted){
        String strSql = "SELECT * FROM Client NATURAL JOIN (SELECT idClient FROM RentACar.Loue WHERE matricule = '"+ matricule+"') AS ids ";
        if (sorted)
            strSql += "Order by nom_client;";
        else
            strSql += ";";
        return strSql;
    }

    public String findClientLocationReq(boolean sorted){
        String strSql = "SELECT * FROM Client NATURAL JOIN (SELECT idClient FROM RentACar.Loue WHERE location_en_cours) AS loc ";
        if (sorted)
            strSql += "Order by nom_client;";
        else
            strSql += ";";
        return strSql;
    }

    public String findAllClientReq(boolean sorted){
        String strSql = "SELECT * FROM Client ";
        if (sorted)
            strSql += "Order by nom_client;";
        else
            strSql += ";";
        return strSql;
    }


    public static void findClient(String strSql ) throws SQLException {
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
        Connection connection = DriverManager.getConnection(url, login, password);
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(strSql);
        Session.getInstance().getClientEntry().clear();
        while (resultSet.next()) {
            Session.getInstance().getClientEntry().add( new Client(
                    resultSet.getString("nom_client"),
                    resultSet.getString("prenom_client"),
                    resultSet.getString("email_client"),
                    resultSet.getString("rue_client"),
                    resultSet.getString("ville_client"),
                    resultSet.getInt("codePostal_client"),
                    resultSet.getString("numTel_client"),
                    resultSet.getInt("idClient")));
        }

    }


}
