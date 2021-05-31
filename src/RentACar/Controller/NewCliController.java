package RentACar.Controller;

import RentACar.Model.Client;
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
        boolean success = true;
        if (success){
            PopUp.popup("Client ajoute avec succees", (Stage)email.getScene().getWindow(), false);
            Client client = new Client.ClientBuilder().setEmail(email.getText()).setCodePostal(Integer.parseInt(post.getText())).setNom(surname.getText()).setPrenom(name.getText()).setNumTel(phone.getText()).setRue(street.getText()).setVille(city.getText()).build();
            //TODO : implementer ajout client

        }
        else {
            PopUp.popup("Erreur dans l'ajout du client", (Stage)email.getScene().getWindow(), true);
        }
    }


}
