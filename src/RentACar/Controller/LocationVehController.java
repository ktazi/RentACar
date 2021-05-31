package RentACar.Controller;

import RentACar.Model.Client;
import RentACar.Model.Location;
import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

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
            System.out.println(location);
        }
    }
}
