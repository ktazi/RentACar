package RentACar.Controller;

import RentACar.Model.Client;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RechercheClientController {

    public Text email;
    public ListView<String> list;
    public Text surname;
    public Text name;
    public Text phone;
    public Text street;
    public Text city;
    public Text post;
    private Client client;

    public void nameSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        //TODO : implementer select par nom, ordonne et non ordonne, et mettre les resultats sous forme de Client dans session

        /* Debut simulation */
        Session.getInstance().getClientEntry().clear();
        Session.getInstance().getClientEntry().add(new Client("Nom", "Prenom", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        Session.getInstance().getClientEntry().add(new Client("Nom1", "Prenom1", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        Session.getInstance().getClientEntry().add(new Client("Nom2", "Prenom2", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        /* Fin simulation */
        refreshList();    }

    public void carSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        //TODO : implementer select par vehicule loue, ordonne et non ordonne, et mettre les resultats sous forme de Client dans session

        /* Debut simulation */
        Session.getInstance().getClientEntry().clear();
        Session.getInstance().getClientEntry().add(new Client("Nom", "Prenom", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        Session.getInstance().getClientEntry().add(new Client("Nom1", "Prenom1", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        Session.getInstance().getClientEntry().add(new Client("Nom2", "Prenom2", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        /* Fin simulation */
        refreshList();
    }

    public void locSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        //TODO : implementer select vehicules loues, ordonne et non ordonne, et mettre les resultats sous forme de Client dans session

        /* Debut simulation */
        Session.getInstance().getClientEntry().clear();
        Session.getInstance().getClientEntry().add(new Client("Nom", "Prenom", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        Session.getInstance().getClientEntry().add(new Client("Nom1", "Prenom1", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        Session.getInstance().getClientEntry().add(new Client("Nom2", "Prenom2", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        /* Fin simulation */
        refreshList();
    }

    public void val(ActionEvent actionEvent) {
        if (client == null)
            PopUp.popup("Veuillez selectionner un client",(Stage)(email.getScene().getWindow()) ,true);
        else
            Session.getInstance().notifyResearchClientObserver(client);
    }

    public void allSearch(ActionEvent actionEvent) {
        list.getItems().clear();
        //TODO : implementer select all, ordonne et non ordonne, et mettre les resultats sous forme de Client dans session

        /* Debut simulation */
        Session.getInstance().getClientEntry().clear();
        Session.getInstance().getClientEntry().add(new Client("Nom", "Prenom", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        Session.getInstance().getClientEntry().add(new Client("Nom1", "Prenom1", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        Session.getInstance().getClientEntry().add(new Client("Nom2", "Prenom2", "Email", "fwefwe", "Ville", 12321, "123123", 123));
        /* Fin simulation */
        refreshList();
    }

    private void refreshList(){
        ArrayList<String> clients = new ArrayList<>();
        this.client = null;
        for (Client cli : Session.getInstance().getClientEntry()){
            clients.add(cli.toString());
        }
        ObservableList<String> ob = FXCollections.observableArrayList(clients);
        list.setItems(ob);
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (clients.indexOf(observable.getValue()) >= 0){
                putClient(Session.getInstance().getClientEntry().get(clients.indexOf(observable.getValue())));
                Session.getInstance().selCli(clients.indexOf(observable.getValue()));
            }
        });
    }

    private void putClient(Client c){
        surname.setText(c.getNom());
        name.setText(c.getPrenom());
        phone.setText(c.getNumTel());
        email.setText(c.getEmail());
        street.setText(c.getRue());
        city.setText(c.getVille());
        post.setText(Integer.toString(c.getCodePostal()));
        this.client = c;
    }

}
