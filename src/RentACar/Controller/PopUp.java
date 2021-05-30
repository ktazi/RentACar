package RentACar.Controller;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PopUp {

    public static void popup(String message, Stage stage) {
        Popup popup = new Popup();
        VBox content = new VBox();
        content.setStyle("-fx-background-color: white; -fx-border-color: red");
        content.setPadding(new Insets(10,10,10,10));
        content.getChildren().add(new Label("Erreur :"));
        content.getChildren().add(new Label(message));
        Button button = new Button("OK");
        button.setOnMouseClicked((event -> {
            popup.hide();
        }));
        content.getChildren().add(button);
        popup.getContent().add(content);
        popup.show(stage);
    }

    public static void popup(String message, Stage stage, boolean error) {
        Popup popup = new Popup();
        VBox content = new VBox();
        if (!error){
            content.setStyle("-fx-background-color: white; -fx-border-color: green");
            content.getChildren().add(new Label("Message :"));
        }
        else{
            content.setStyle("-fx-background-color: white; -fx-border-color: red");
            content.getChildren().add(new Label("Erreur :"));
        }
        content.getChildren().add(new Label(message));
        content.setPadding(new Insets(10,10,10,10));
        Button button = new Button("OK");
        button.setOnMouseClicked((event -> {
            popup.hide();
        }));
        content.getChildren().add(button);
        popup.getContent().add(content);
        popup.show(stage);
    }
}
