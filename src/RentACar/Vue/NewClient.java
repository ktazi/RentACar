package RentACar.Vue;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NewClient extends VBox {
    public NewClient() {
        super();
        try {
            setAlignment(Pos.CENTER);
            Parent root = FXMLLoader.load(getClass().getResource("newCli.fxml"));
            getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
