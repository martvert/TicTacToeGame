package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MenuController {

    @FXML private BorderPane rootPane;
    @FXML private Button gameButton;
    @FXML private Button exitButton;


    @FXML public void handleGameButton() throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("game.fxml"));
        rootPane.setCenter(pane);
    }


    @FXML public void handleExitButton() {
        Platform.exit();
    }
}
