package RentACar.Controller;

import RentACar.Model.Retour;
import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class RetourVehController {
    public TextArea dommmage;
    public Spinner<Double> priceDommage;
    public Spinner<Integer> num;
    public Button rendre;
    public Spinner<Double> carb;

    public void rendre(ActionEvent actionEvent) {
        Vehicule v = Session.getInstance().getVeh();
        if (v == null){
            PopUp.popup("Veuillez Selectionner un client et un vehicule", (Stage)(priceDommage.getScene().getWindow()), true);
        }
        else if (dommmage == null){
            PopUp.popup("Veuillez saisir une description des dommages", (Stage)(priceDommage.getScene().getWindow()), true);
        }
        else{
            retour();
        }
    }

    public void retour(){
        String strSql = "select location_en_cours from RentACar.loue where matricule = '"+ Session.getInstance().getVeh().getMatricule()+"' and location_en_cours= true;";
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
            PopUp.popup("Erreur dans l'ajout du vehicule", (Stage)priceDommage.getScene().getWindow(), true);
        }
        String url = props.getProperty("jdbc.url");
        String login = props.getProperty("jdbc.login");
        String password = props.getProperty("jdbc.password");
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSql);
            if (!resultSet.next()){
                PopUp.popup("Vehicule non reserve par le client", (Stage)priceDommage.getScene().getWindow(), true);
            }
            else {
                strSql = "update loue set location_en_cours=false, frais_dommage="+priceDommage.getValue()+", etat_vehicule='"+dommmage.getText()+"', niveau_essence_fin ="+ carb.getValue()+", retard ="+num.getValue()+" where matricule = '"+ Session.getInstance().getVeh().getMatricule()+"' and location_en_cours = true";
                Statement stmt2 = connection.createStatement();
                stmt2.executeUpdate(strSql);
                PopUp.popup("Vehicule rendu avec succes", (Stage)priceDommage.getScene().getWindow(), true);
                //TODO devis


            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


}
