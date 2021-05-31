package RentACar.Controller;

import RentACar.Model.Client;
import RentACar.Model.Reservation;
import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;

public class ReservationVehController {
    public DatePicker date;
    public Spinner<Integer> num;

    public void reserve(ActionEvent actionEvent) {
        Client c = Session.getInstance().getCli();
        Vehicule v = Session.getInstance().getVeh();
        if (c == null || v == null){
            PopUp.popup("Veuillez Selectionner un client et un vehicule", (Stage)(date.getScene().getWindow()), true);
        }
        else if (date.getValue() == null){
            PopUp.popup("Veuillez Selectionner une date", (Stage)(date.getScene().getWindow()), true);
        }
        else{
           Reservation reservation = new Reservation(date.getValue().toString(),num.getValue(),c,v);
            System.out.println(reservation);
            validReservation();
        }
    }
    public  void validReservation() {
        String strSql = "select date_location, duree_location from RentACar.loue where matricule = '"+ Session.getInstance().getVeh().getMatricule()+"' order by date_location;";

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
            HashMap<Date, Integer> schedule = new HashMap<>();
            while (resultSet.next()) {
                schedule.put(resultSet.getDate("date_location"), resultSet.getInt("duree_location"));
                System.out.println(resultSet.getDate("date_location"));
                System.out.println(resultSet.getInt("duree_location"));

            }
            boolean available = true;
            Date da = Date.from(date.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(da);
            c.add(GregorianCalendar.DATE, num.getValue());
            Date daf = c.getTime();
            System.out.println(da.toString());
            System.out.println(daf.toString());

            for (Map.Entry<Date, Integer> entry : schedule.entrySet()) {
                Date d = entry.getKey();
                c = new GregorianCalendar();
                c.setTime(d);
                c.add(GregorianCalendar.DATE, entry.getValue());

                Date fin = c.getTime();
                System.out.println("Time :" + d + " "+ fin);
                System.out.println("Time :" + da + " "+ daf);

                available = available && ((fin.before(da) && fin.before(daf)) || (d.after(da) && d.after(daf)));
                System.out.println(available);
            }
            if (available) {
                strSql = "INSERT INTO loue (idClient, matricule, date_location, date_reservation, duree_location, location_en_cours)" +
                        "VALUES (" + Session.getInstance().getCli().getId() + ", '" + Session.getInstance().getVeh().getMatricule() + "', '"+ date.getValue().toString() +"', '" + date.getValue().toString() + "', " + num.getValue() + ", false);";
                System.out.println(strSql);
                Statement stmt2 = connection.createStatement();
                stmt2.executeUpdate(strSql);
                PopUp.popup("Vehicule Reserve avec succes", (Stage)date.getScene().getWindow(), false);

            } else {
                PopUp.popup("Erreur Vehicule non disponible", (Stage)date.getScene().getWindow(), true);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }


}
