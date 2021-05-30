package RentACar.Controller;

import RentACar.Model.Client;
import RentACar.Model.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RechercheVehController {
    public ChoiceBox<String> classe;
    public Button classeSearch;
    public TextField marqueField;
    public Button marqueButton;
    public CheckBox loue;
    public ListView<String> list;
    public Text mat;
    public Text kilo;
    public Text carb;
    public Text modele;
    public Text marque;
    public CheckBox clim;
    public CheckBox vit;
    public Text cat;
    public Button buttonSelVeh;
    private Vehicule vehicule;

    public void classSearch(ActionEvent actionEvent) {
        list.getItems().clear();

        //TODO : implementer select par categorie et mettre sous forme de

        /* Debut simulation */
        Session.getInstance().getVehiculeEntry().clear();
        Session.getInstance().getVehiculeEntry().add(new Vehicule("mat6","dsfs", 1932, true, true, true, "lwkeflw", "wekflwemflwemfw", "wefnwkejfnw"));
        Session.getInstance().getVehiculeEntry().add(new Vehicule("mat7","dsfs", 1932, true, true, true, "lwkeflw", "wekflwemflwemfw", "wefnwkejfnw"));
        Session.getInstance().getVehiculeEntry().add(new Vehicule("mat8","dsfs", 1932, true, true, true, "lwkeflw", "wekflwemflwemfw", "wefnwkejfnw"));
        /* Fin simulation */
        refreshList();

    }

    public void brandSearch(ActionEvent actionEvent) {
        list.getItems().clear();

        //TODO : implementer select par categorie et mettre sous forme de

        /* Debut simulation */
        Session.getInstance().getVehiculeEntry().clear();
        Session.getInstance().getVehiculeEntry().add(new Vehicule("mat","dsfs", 1932, true, true, true, "lwkeflw", "wekflwemflwemfw", "wefnwkejfnw"));
        Session.getInstance().getVehiculeEntry().add(new Vehicule("mat2","dsfs", 1932, true, true, true, "lwkeflw", "wekflwemflwemfw", "wefnwkejfnw"));
        Session.getInstance().getVehiculeEntry().add(new Vehicule("mat3","dsfs", 1932, true, true, true, "lwkeflw", "wekflwemflwemfw", "wefnwkejfnw"));
        /* Fin simulation */
        refreshList();
    }

    public void selecVeh(ActionEvent actionEvent) {
        if (vehicule == null)
            PopUp.popup("Veuillez selectionner un vehicule",(Stage)(marque.getScene().getWindow()) ,true);
        else
            Session.getInstance().notifyResearchVehiculeObserver(vehicule);
    }

    private void refreshList(){
        ArrayList<String> vehicules = new ArrayList<>();
        this.vehicule = null;
        for (Vehicule v : Session.getInstance().getVehiculeEntry()){
            vehicules.add(v.toString());
        }
        ObservableList<String> ob = FXCollections.observableArrayList(vehicules);
        list.setItems(ob);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (vehicules.indexOf(observable.getValue()) >=0){
                putVehicule(Session.getInstance().getVehiculeEntry().get(vehicules.indexOf(observable.getValue())));
                Session.getInstance().selVeh(vehicules.indexOf(observable.getValue()));
            }
        });
    }

    private void putVehicule(Vehicule c){
        mat.setText(c.getMatricule());
        kilo.setText(Integer.toString(c.getKilometrage()));
        carb.setText(c.getTypeCarburant());
        modele.setText(c.getModele());
        marque.setText(c.getMarque());
        clim.setSelected(c.isClim());
        vit.setSelected(c.isBoiteMan());
        cat.setText(c.getCategorie());
        this.vehicule = c;
    }




}
