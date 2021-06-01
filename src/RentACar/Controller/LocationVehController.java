package RentACar.Controller;

import RentACar.Model.Client;
import RentACar.Model.Location;
import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;

public class LocationVehController {
    public DatePicker date;
    public Spinner<Integer> num;
    public ChoiceBox<String> assur;

    public void louer(ActionEvent actionEvent) {
        Client c = Session.getInstance().getCli();
        Vehicule v = Session.getInstance().getVeh();
        if (c == null || v == null){
            PopUp.popup("Veuillez Selectionner un client et un vehicule", (Stage)(date.getScene().getWindow()), true);
        }
        else if (date.getValue() == null){
            PopUp.popup("Veuillez Selectionner une date", (Stage)(date.getScene().getWindow()), true);
        }
        else{
            Location location = new Location(c,v,date.getValue().toString(),num.getValue(),assur.getValue());
            loue();
        }
    }

    public void loue(){
        String strSql = "select location_en_cours from RentACar.loue where matricule = '"+ Session.getInstance().getVeh().getMatricule()+"' and idclient="+Session.getInstance().getCli().getId()+" and date_location= '"+date.getValue().toString()+"';";
        String strSql5 = "select * from RentACar.loue where idclient="+Session.getInstance().getCli().getId()+ " and location_en_cours=true  ;";
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
            PopUp.popup("Erreur dans l'ajout du vehicule", (Stage)date.getScene().getWindow(), true);
        }
        String url = props.getProperty("jdbc.url");
        String login = props.getProperty("jdbc.login");
        String password = props.getProperty("jdbc.password");
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(strSql);
            if (resultSet.next()){
                boolean loc =  resultSet.getBoolean("location_en_cours");
                if (loc){
                    PopUp.popup("La location du vehicule a deja ete validee", (Stage)date.getScene().getWindow(), true);
                }
                else {
                    String strSql2 = "UPDATE loue SET type_assurance = '" + assur.getValue() + "' , duree_location = " + num.getValue() + ", location_en_cours = true where matricule = '"+ Session.getInstance().getVeh().getMatricule()+"' and idclient="+Session.getInstance().getCli().getId()+" and date_location= '"+date.getValue().toString()+"';";
                    Statement stmt2 = connection.createStatement();
                    stmt2.executeUpdate(strSql2);
                }
            }
            else{
                String strSql3 = "select date_location, duree_location from RentACar.loue where matricule = '"+ Session.getInstance().getVeh().getMatricule()+"' order by date_location;";
                Statement stmt2 = connection.createStatement();
                ResultSet resultSet2 = stmt2.executeQuery(strSql3);
                HashMap<Date, Integer> schedule = new HashMap<>();
                System.out.println(strSql3);
                while (resultSet2.next()) {
                    schedule.put(resultSet2.getDate("date_location"), resultSet2.getInt("duree_location"));
                }
                boolean available = true;
                Date da = Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                GregorianCalendar c = new GregorianCalendar();
                c.setTime(da);
                c.add(GregorianCalendar.DATE, num.getValue());
                Date daf = c.getTime();
                for (Map.Entry<Date, Integer> entry : schedule.entrySet()) {
                    Date d = entry.getKey();
                    c = new GregorianCalendar();
                    c.setTime(d);
                    c.add(GregorianCalendar.DATE, entry.getValue());
                    Date fin = c.getTime();
                    available = available && ((fin.before(da) && fin.before(daf)) || (d.after(da) && d.after(daf)));
                }
                if (available) {
                    Statement stmt5 = connection.createStatement();
                    ResultSet resSet3 = stmt5.executeQuery(strSql5);
                    if (resSet3.next()){
                        PopUp.popup("Erreur Client loue deja un vehicule", (Stage)date.getScene().getWindow(), true);
                    }
                    else {
                        strSql = "INSERT INTO loue (idClient, matricule, date_location, duree_location, location_en_cours, type_assurance)" +
                                "VALUES (" + Session.getInstance().getCli().getId() + ", '" + Session.getInstance().getVeh().getMatricule() + "', '"+ date.getValue().toString() +"', " + num.getValue() + ", true, '" + assur.getValue() +"');";
                        Statement stmt3 = connection.createStatement();
                        stmt3.executeUpdate(strSql);
                        PopUp.popup("Vehicule Loue avec succes", (Stage)date.getScene().getWindow(), false);
                    }
                }
                else{
                    PopUp.popup("Erreur Vehicule non disponible", (Stage)date.getScene().getWindow(), true);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            PopUp.popup("Erreur bdd", (Stage)date.getScene().getWindow(), true);
        }



    }


}
