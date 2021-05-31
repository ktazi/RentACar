package RentACar.Controller;

import RentACar.Model.Client;
import RentACar.Model.Vehicule;
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

public class RechercheVehController {
    public ChoiceBox<String> classe;
    public Button classeSearch;
    public TextField marqueField;
    public Button marqueButton;
    public CheckBox loue;
    public ListView<String> list;
    public Text mat;
    public Text kilo;
    public Text carb;
    public Text modele;
    public Text marque;
    public CheckBox clim;
    public CheckBox vit;
    public Text cat;
    public Button buttonSelVeh;
    private Vehicule vehicule;

    public void classSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        try {
            findVeh(findVehCat(classe.getValue()));
        } catch (SQLException throwables) {
            PopUp.popup("Recherche ratee",(Stage)(mat.getScene().getWindow()), true);
            throwables.printStackTrace();
        }

        refreshList();

    }

    public void brandSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        try {
            findVeh(findVehMarque(marqueField.getText()));
        } catch (SQLException throwables) {
            PopUp.popup("Recherche ratee",(Stage)(mat.getScene().getWindow()), true);
            throwables.printStackTrace();
        }
        refreshList();
    }

    public void locSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        try {
            findVeh(findVehLocation());
        } catch (SQLException throwables) {
            PopUp.popup("Recherche ratee",(Stage)(mat.getScene().getWindow()), true);
            throwables.printStackTrace();
        }
        refreshList();
    }

    public void selecVeh(ActionEvent actionEvent) {
        if (vehicule == null)
            PopUp.popup("Veuillez selectionner un vehicule",(Stage)(marque.getScene().getWindow()) ,true);
        else
            Session.getInstance().notifyResearchVehiculeObserver(vehicule);
    }

    private void refreshList(){
        ArrayList<String> vehicules = new ArrayList<>();
        this.vehicule = null;
        for (Vehicule v : Session.getInstance().getVehiculeEntry()){
            vehicules.add(v.toString());
        }
        ObservableList<String> ob = FXCollections.observableArrayList(vehicules);
        list.setItems(ob);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (vehicules.indexOf(observable.getValue()) >=0){
                putVehicule(Session.getInstance().getVehiculeEntry().get(vehicules.indexOf(observable.getValue())));
                Session.getInstance().selVeh(vehicules.indexOf(observable.getValue()));
            }
        });
    }

    public String findVehCat(String cat){
        String strSql = "SELECT * FROM vehicule NATURAL JOIN modele WHERE typeCategorie = '" + cat + "';";
        return strSql;
    }

    public String findVehMarque(String marque){
        String strSql = "SELECT * FROM vehicule NATURAL JOIN modele WHERE marque = '" + marque + "';";
        return strSql;
    }

    public String findVehLocation(){
        String strSql = "select * from Vehicule natural join (select matricule from loue where location_en_cours) as lou natural join modele;";
        return strSql;
    }

    public static void findVeh(String strSql) throws SQLException {
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
        Session.getInstance().getVehiculeEntry().clear();
        while (resultSet.next()) {
            Session.getInstance().getVehiculeEntry().add(new Vehicule(
                    resultSet.getString("matricule"),
                    resultSet.getString("typeCarburant"),
                    resultSet.getInt("kilometrage"),
                    resultSet.getBoolean("location"),
                    resultSet.getBoolean("boiteManuelle"),
                    resultSet.getBoolean("climatise"),
                    resultSet.getString("modele"),
                    resultSet.getString("marque"),
                    resultSet.getString("typeCategorie")));
        }

    }
    private void putVehicule(Vehicule c){
        mat.setText(c.getMatricule());
        kilo.setText(Integer.toString(c.getKilometrage()));
        carb.setText(c.getTypeCarburant());
        modele.setText(c.getModele());
        marque.setText(c.getMarque());
        clim.setSelected(c.isClim());
        vit.setSelected(c.isBoiteMan());
        cat.setText(c.getCategorie());
        this.vehicule = c;
    }



}
