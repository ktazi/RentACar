package RentACar.Controller;

import RentACar.Model.Client;
import RentACar.Model.Reservation;
import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

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
        //TODO : implement reservation
        else{
           Reservation reservation = new Reservation(date.getValue().toString(),num.getValue(),c,v);
            System.out.println(reservation);
        }
    }
}
