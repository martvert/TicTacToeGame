package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    static private final String MAIN_ICON_PATH = "sample/main-icon.png";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.getIcons().add(new Image(MAIN_ICON_PATH));
        primaryStage.setScene(new Scene(root, 530, 465));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}