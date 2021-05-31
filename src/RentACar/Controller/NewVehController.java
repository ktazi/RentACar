package RentACar.Controller;

import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class NewVehController {
    public TextField mat;
    public TextField kilo;
    public TextField carb;
    public TextField modele;
    public TextField marque;
    public Button buttonAddVeh;
    public CheckBox clim;
    public CheckBox vit;
    public ChoiceBox<String> cat;

    public void addVeh(ActionEvent actionEvent) {

        Vehicule vehicule = new Vehicule(mat.getText(),carb.getText(),Integer.parseInt(kilo.getText()),false,vit.isSelected(),clim.isSelected(),modele.getText(),marque.getText(),cat.getValue());
        //TODO implementer ajout vehicule

    }

}
