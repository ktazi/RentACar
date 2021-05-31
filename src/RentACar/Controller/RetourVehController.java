package RentACar.Controller;

import RentACar.Model.Client;
import RentACar.Model.Retour;
import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class RetourVehController {
    public TextArea dommmage;
    public Spinner<Double> priceDommage;
    public DatePicker date;
    public Spinner<Integer> num;
    public Button rendre;

    public void rendre(ActionEvent actionEvent) {
        Vehicule v = Session.getInstance().getVeh();
        if (v == null){
            PopUp.popup("Veuillez Selectionner un client et un vehicule", (Stage)(date.getScene().getWindow()), true);
        }
        else if (date.getValue() == null){
            PopUp.popup("Veuillez Selectionner une date de location", (Stage)(date.getScene().getWindow()), true);
        }
        else if (dommmage == null){
            PopUp.popup("Veuillez saisir une description des dommages", (Stage)(date.getScene().getWindow()), true);
        }
        else{
            Retour r = new Retour(v,date.getValue().toString(),num.getValue(),dommmage.getText(),priceDommage.getValue());




            System.out.println(r);
        }
    }

    public void retour(){

    }


}
