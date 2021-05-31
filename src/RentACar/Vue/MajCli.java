package RentACar.Vue;

import RentACar.Controller.Session;
import RentACar.Model.Client;
import RentACar.Vue.Interface.ClientResearchObserver;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class MajCli extends VBox implements ClientResearchObserver {

    ArrayList<TextField> textField;
    public void notify(Client c){
        textField.get(0).setText(c.getNom());
        textField.get(1).setText(c.getPrenom());
        textField.get(2).setText(c.getEmail());
        textField.get(3).setText(c.getNumTel());
        textField.get(4).setText(c.getRue());
        textField.get(5).setText(c.getVille());
        textField.get(6).setText(Integer.toString(c.getCodePostal()));
    }

    public MajCli() {
        super();
        try {
            Session.getInstance().getClientEntry().clear();
            setAlignment(Pos.CENTER);
            getChildren().add(FXMLLoader.load(getClass().getResource("RechercheClient.fxml")));
            getChildren().add(FXMLLoader.load(getClass().getResource("majCli.fxml")));
            textField = new ArrayList<>();
            for (Node n :((GridPane)(((VBox)(getChildren().get(1))).getChildren().get(1))).getChildren())
                if (n instanceof TextField)
                    textField.add((TextField)n);
            Session.getInstance().setClientResearchObserver(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
