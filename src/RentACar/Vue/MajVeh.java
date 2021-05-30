package RentACar.Vue;

import RentACar.Controller.Session;
import RentACar.Model.Vehicule;
import RentACar.Vue.Interface.VehiculeResearchObserver;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class MajVeh extends VBox implements VehiculeResearchObserver {
    ArrayList<TextField> textFields;
    ArrayList<CheckBox> checkBoxes;
    ChoiceBox<String> choiceBox;
    public MajVeh() {
        super();
        try {
            setAlignment(Pos.CENTER);
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheVeh.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("MajVeh.fxml")));
            Session.getInstance().setVehiculeResearchObserver(this);
            textFields = new ArrayList<>();
            checkBoxes = new ArrayList<>();
            for (Node n : ((GridPane)(((VBox)(getChildren().get(1))).getChildren().get(1))).getChildren()) {
                if (n instanceof TextField) {
                    System.out.println(n);
                    textFields.add((TextField)n);
                }
                else if (n instanceof CheckBox){
                    System.out.println(n);
                    checkBoxes.add((CheckBox) n);
                }
                else if (n instanceof ChoiceBox){
                    choiceBox = (ChoiceBox<String>) n;
                    System.out.println(n);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void notify(Vehicule v) {
        choiceBox.setValue(v.getCategorie());
        textFields.get(0).setText(v.getMatricule());
        textFields.get(1).setText(Integer.toString(v.getKilometrage()));
        textFields.get(2).setText(v.getTypeCarburant());
        textFields.get(3).setText(v.getModele());
        textFields.get(4).setText(v.getMarque());
        checkBoxes.get(0).setSelected(v.isClim());
        checkBoxes.get(1).setSelected(v.isBoiteMan());
    }
}