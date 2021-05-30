package RentACar.Controller;

import RentACar.Vue.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GeneralController {

    public VBox container;
    private Session currentSession;

    @FXML
    protected void handleCreate(){
        System.out.println("ButtonPressed");

    }

    @FXML
    protected void handleMenuBar(ActionEvent event){
        String id = ((MenuItem)(event.getSource())).getId();
        if (id.equals("ajCli")){
            container.getChildren().clear();
            container.getChildren().add(new NewClient());
        }
        else if (id.equals("majCli")){
            container.getChildren().clear();
            container.getChildren().add(new MajCli());
        }
        else if (id.equals("ajVeh")){
            container.getChildren().clear();
            container.getChildren().add(new NewVeh());
        }
        else if (id.equals("supVeh")){
            container.getChildren().clear();
            container.getChildren().add(new SupVeh());
        }
        else if (id.equals("reservation")){
            container.getChildren().clear();
            container.getChildren().add(new ReservationVeh());
        }
        else if (id.equals("location")){
            container.getChildren().clear();
            container.getChildren().add(new LocationVeh());
        }
        else if (id.equals("retour")){
            container.getChildren().clear();
            container.getChildren().add(new RetourVeh());
        }
        else if(id.equals("modifVeh")){
            container.getChildren().clear();
            container.getChildren().add(new MajVeh());
        }
    }

    @FXML
    protected void handleConnectEmployee(ActionEvent event){
        System.out.println("Button Connect Employee Pressed");
        boolean connectSuccessful = true;
        //TODO : implementer connect

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        if (connectSuccessful){
            if (currentSession == null){
                currentSession = Session.getInstance();
            }
            currentSession.setUsername("Jean Mich");
             swapVue("../Vue/menu.fxml", stage, true);
        }
        else {
            PopUp.popup("Probleme lors de la connection", stage, false);
        }
    }

    @FXML
    protected void handleDisconnect(ActionEvent event){
        System.out.println("Button disconnect Employee Pressed");
        //TODO : implementer disconnect
        if (currentSession == null){
            currentSession = Session.getInstance();
        }
        currentSession.setUsername("Jean Mich");
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        swapVue("../Vue/connect.fxml", stage, false);
    }


    private void swapVue(String name, Stage stage, boolean ch){
        try {
            Parent root = FXMLLoader.load(getClass().getResource(name));
            stage.setScene(new Scene(root, 1000, 700));
            stage.getScene().getStylesheets().add(getClass().getResource("../Vue/style.css").toExternalForm());
            stage.show();
            if (ch)
            {
                Thread changeName = new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Text t = (Text) stage.getScene().lookup("#user");
                    t.setText(currentSession.getUsername());
                });
                changeName.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
