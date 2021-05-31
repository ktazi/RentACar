package RentACar.Controller;

import RentACar.Vue.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.Properties;

public class GeneralController {

    public VBox container;
    public PasswordField passw;
    public TextField username;
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

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = digest.digest(passw.getText().getBytes(StandardCharsets.UTF_8));
        String password = Base64.getEncoder().encodeToString(hash);

        String strSql = "SELECT * FROM Employe WHERE loginEmploye =? AND mdpEmploye =?";
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("Config/conf.properties")) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName(props.getProperty("jdbc.driver.class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            PopUp.popup("Erreur dans le chargement du driver", (Stage)username.getScene().getWindow(), true);

        }
        String url = props.getProperty("jdbc.url");
        String login = props.getProperty("jdbc.login");
        String ps = props.getProperty("jdbc.password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, ps);
            PreparedStatement stmt = connection.prepareStatement(strSql);
            stmt.setString(1, username.getText());
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                PopUp.popup("Mauvais identifiants/mdp",(Stage)username.getScene().getWindow() , true);
            }
            else {

                Session.getInstance().setUsername(username.getText());
                swapVue("../Vue/menu.fxml", (Stage)username.getScene().getWindow(), true);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            PopUp.popup("Erreur lors de la connexion",(Stage)username.getScene().getWindow() , true);

        }

    }

    @FXML
    protected void handleDisconnect(ActionEvent event){
        System.out.println("Button disconnect Employee Pressed");
        //TODO : implementer disconnect
        Session.getInstance().setUsername("");
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
                    t.setText(Session.getInstance().getUsername());
                });
                changeName.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
