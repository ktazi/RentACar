package RentACar.Controller;

import RentACar.Model.Vehicule;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MajVehController {

    public Button buttonMajVeh;
    public TextField mat;
    public TextField kilo;
    public TextField carb;
    public TextField modele;
    public ChoiceBox<String> cat;
    public CheckBox vit;
    public CheckBox clim;
    public TextField marque;

    public void majVeh(ActionEvent actionEvent) {
        //TODO : implementer mise a jour vehicule
        Vehicule v = Session.getInstance().getVeh();
        if (v == null)
            PopUp.popup("Veuillez Selectionner un Vehicule", (Stage)(mat.getScene().getWindow()), true);
        else {
            v.setMatricule(mat.getText());
            v.setKilometrage(Integer.parseInt(kilo.getText()));
            v.setTypeCarburant(carb.getText());
            v.setModele(modele.getText());
            v.setClim(clim.isSelected());
            v.setBoiteMan(vit.isSelected());
            v.setMarque(marque.getText());
            v.setCategorie(cat.getValue());
            System.out.println(v);
        }

    }
}
