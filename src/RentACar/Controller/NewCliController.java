package RentACar.Controller;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class NewCliController {

    public TextField surname;
    public TextField name;
    public TextField email;
    public TextField phone;
    public TextField street;
    public TextField city;
    public TextField post;

    public void addClient(ActionEvent actionEvent) {
        //TODO : implementer ajout client
        boolean success = true;
        if (success){
            PopUp.popup("Client ajoute avec succees", (Stage)email.getScene().getWindow(), false);
        }
        else {
            PopUp.popup("Erreur dans l'ajout du client", (Stage)email.getScene().getWindow(), true);
        }
    }


}
