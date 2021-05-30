package RentACar.Controller;

import RentACar.Model.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MajController {
    public TextField surname;
    public TextField name;
    public TextField email;
    public TextField phone;
    public TextField street;
    public TextField city;
    public TextField post;
    public Button buttonMAJClient;

    public void majClient(ActionEvent actionEvent) {
        //TODO : implementer mise a jour client

        Client c = Session.getInstance().getCli();
        if (c == null)
            PopUp.popup("Veuillez Selectionner un client", (Stage)(email.getScene().getWindow()), true);
        else {
            c.setNom(surname.getText());
            c.setPrenom(name.getText());
            c.setCodePostal(Integer.parseInt(post.getText()));
            c.setEmail(email.getText());
            c.setNumTel(phone.getText());
            c.setRue(street.getText());
            c.setVille(city.getText());
            System.out.println(c);
        }


    }

    public void setClient(Client c){
        surname.setText(c.getNom());
        name.setText(c.getPrenom());
        phone.setText(c.getNumTel());
        email.setText(c.getEmail());
        street.setText(c.getRue());
        city.setText(c.getVille());
        post.setText(Integer.toString(c.getCodePostal()));

    }

}
