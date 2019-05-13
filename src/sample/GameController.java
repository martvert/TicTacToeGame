package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GameController {

    private boolean firstPlayer = true;
    private boolean secondPlayer = false;
    private List<Integer> clickedIdxValues = new ArrayList<>();
    private ImageView winnerIcon = new ImageView(new Image("/sample/winner-icon.jpg"));
    private int[][] winnerCheck = new int[][] {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    @FXML private BorderPane rootPane;
    @FXML private TextField player1;
    @FXML private TextField player2;
    @FXML private TextField winner;
    @FXML private Label winnerLabel;
    @FXML private Button newGameButton;
    @FXML private Button resetButton;
    @FXML private Button backToMenuButton;
    @FXML private Button crossCircleButton1;
    @FXML private Button crossCircleButton2;
    @FXML private Button crossCircleButton3;
    @FXML private Button crossCircleButton4;
    @FXML private Button crossCircleButton5;
    @FXML private Button crossCircleButton6;
    @FXML private Button crossCircleButton7;
    @FXML private Button crossCircleButton8;
    @FXML private Button crossCircleButton9;
    @FXML private List<Button> buttonList;


    public GameController(){

        buttonList = new ArrayList<>();
        buttonList.add(crossCircleButton1);
        buttonList.add(crossCircleButton2);
        buttonList.add(crossCircleButton3);
        buttonList.add(crossCircleButton4);
        buttonList.add(crossCircleButton5);
        buttonList.add(crossCircleButton6);
        buttonList.add(crossCircleButton7);
        buttonList.add(crossCircleButton8);
        buttonList.add(crossCircleButton9);

    }


    @FXML public void initialize() {
        newGameButton.setDisable(true);
        for (int i = 0; i < 9; ++i) {
            buttonList.get(i).setDisable(true);
        }
    }


    @FXML public void reset() {
        for (int i = 0; i < 9; ++i) {
            buttonList.get(i).setStyle("");
        }
        firstPlayer = true;
        secondPlayer = false;
        player1.setText("");
        player2.setText("");
        winnerLabel.setGraphic(null);
        winnerLabel.setText("");
        winner.setText("");
        clickedIdxValues.clear();

        handleKeyReleased();
    }


    @FXML public void handleBackToMenuButton() throws IOException {
        BorderPane pane = FXMLLoader.load(getClass().getResource("menu.fxml"));
        rootPane.setCenter(pane);
        rootPane.setTop(null);
        rootPane.setBottom(null);
    }


    @FXML public void handleCrossCircleButton(ActionEvent e) {
        int currentIdx = buttonList.indexOf(e.getSource());

        if (!clickedIdxValues.contains(currentIdx) && winner.getText().isEmpty()) {
            clickedIdxValues.add(currentIdx);

            if (firstPlayer) {
                buttonList.get(currentIdx).setStyle("-fx-background-image: url('sample/x-icon.png')");
            } else {
                buttonList.get(currentIdx).setStyle("-fx-background-image: url('sample/circle-icon.png')");
            }

            firstPlayer = secondPlayer;
            secondPlayer = !firstPlayer;

            whoIsTheWinner();
            thereIsNoWinner();
        }
    }


    @FXML public void handleNewGameButton() {
        for (int i = 0; i < 9; ++i) {
            buttonList.get(i).setStyle("");
        }
        firstPlayer = true;
        secondPlayer = false;
        winnerLabel.setGraphic(null);
        winnerLabel.setText("");
        winner.setText("");
        clickedIdxValues.clear();

    }


    @FXML public void handleKeyReleased() {
        boolean playerOneIsEmpty = player1.getText().isEmpty() || player1.getText().trim().isEmpty();
        boolean playerTwoIsEmpty = player2.getText().isEmpty() || player2.getText().trim().isEmpty();
        newGameButton.setDisable(playerOneIsEmpty || playerTwoIsEmpty);
            for (int i = 0; i < 9; ++i) {
                buttonList.get(i).setDisable(playerOneIsEmpty || playerTwoIsEmpty);
            }
    }


    @FXML public void whoIsTheWinner() {
        for (int i = 0; i < winnerCheck.length; ++i) {
            if (buttonList.get(winnerCheck[i][0]).getStyle() == "-fx-background-image: url('sample/x-icon.png')"
                && buttonList.get(winnerCheck[i][1]).getStyle() == "-fx-background-image: url('sample/x-icon.png')"
                && buttonList.get(winnerCheck[i][2]).getStyle() == "-fx-background-image: url('sample/x-icon.png')") {

                setTheWinnerPlayerOne();

            } else if (buttonList.get(winnerCheck[i][0]).getStyle() == "-fx-background-image: url('sample/circle-icon.png')"
                        && buttonList.get(winnerCheck[i][1]).getStyle() == "-fx-background-image: url('sample/circle-icon.png')"
                        && buttonList.get(winnerCheck[i][2]).getStyle() == "-fx-background-image: url('sample/circle-icon.png')") {

                setTheWinnerPlayerTwo();

            }
        }
    }


    public void setTheWinnerPlayerOne() {
        winnerLabel.setGraphic(winnerIcon);
        winnerLabel.setText("The winner is: ");
        winner.setText(player1.getText());
    }


    public void setTheWinnerPlayerTwo() {
        winnerLabel.setGraphic(winnerIcon);
        winnerLabel.setText("The winner is: ");
        winner.setText(player2.getText());
    }


    @FXML public void thereIsNoWinner() {
        if (clickedIdxValues.size() == 9 && winner.getText().isEmpty()) {
            winner.setText("There is no winner!");
        }
    }
}