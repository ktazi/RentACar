package RentACar.Controller;

import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SupVehController {
    public Button button;

    public void supVehi(ActionEvent actionEvent) {

        Vehicule vehicule = Session.getInstance().getVeh();
        if (vehicule == null){
            PopUp.popup("Veuillez Selectionner un vehicule a supprimer", (Stage)(button.getScene().getWindow()), true);
        }
        else {
            //TODO : implementer suppression
        }
    }
}
