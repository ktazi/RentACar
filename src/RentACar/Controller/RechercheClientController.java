package RentACar.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RechercheClientController {

    public Text surname;
    public Text name;
    public Text email;
    public Text phone;
    public Text street;
    public Text city;
    public Text post;


    public void nameSearch(ActionEvent actionEvent) {
        PopUp.popup("Recherche par nom", (Stage)email.getScene().getWindow(), false);
    }

    public void carSearch(ActionEvent actionEvent) {
        PopUp.popup("Recherche par vehicule loue", (Stage)email.getScene().getWindow(), false);

    }

    public void locSearch(ActionEvent actionEvent) {
        PopUp.popup("Client qui louent", (Stage)email.getScene().getWindow(), false);

    }

    public void val(ActionEvent actionEvent) {
        PopUp.popup("OK", (Stage)email.getScene().getWindow(), false);

    }

    public void allSearch(ActionEvent actionEvent) {
    }
}
