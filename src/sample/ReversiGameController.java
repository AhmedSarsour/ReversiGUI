package sample;

import Reversi.GuiGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * ReversiGameController.
 * This class handles our game - we create here the board and playing the game.
 */
public class ReversiGameController implements Initializable {
    @FXML
    //Our root HBOX.
    private HBox root;
    @Override
    /**
     * Called to initialize a controller after its root element has been
     * completely processed. In our we want to put display the board and handle screen resize.
     * @param location
     * The location used to resolve relative paths for the root object.
     * @param resources
     * The resources used to localize the root object.
     */
    public void initialize(URL location, ResourceBundle resources) {
        //Getting information from the file.
        SettingsReader settingsReader = new SettingsReader("menuInfo.txt");
        //Getting the size from the file.
        int size = settingsReader.getBoardSize();
        //Creating new reversiBoard.
        ReversiBoard reversiBoard = new ReversiBoard(size,size);
        //reversiBoard is also a GridPane so we can set it's properties.
        reversiBoard.setPrefWidth(400);
        reversiBoard.setPrefHeight(400);
        reversiBoard.setPadding(new Insets(20,20,20,20));
        reversiBoard.setAlignment(Pos.CENTER);
        //Adding the board to our HBOX.
        root.getChildren().add(0, reversiBoard);
        //Drowing our board.
        reversiBoard.draw();
        //Getting the labels of information about every stage in the game.
        VBox vBox = (VBox) root.getChildren().get(1);
        //The current player label.
        Label lblCurrent = (Label)vBox.getChildren().get(1);
        //The first player label.
        Label lblBlack = (Label)vBox.getChildren().get(2);
        //The second player label.
        Label lblWhite = (Label)vBox.getChildren().get(3);
        //Creating gui game.
        GuiGame game = new GuiGame(reversiBoard.getBoard());
        //Giving our game the color names.
        game.setColors(settingsReader.getFirstPlayer(), settingsReader.getSecondPlayer());
        //Closing the file.
        settingsReader.closeTheFile();
        //Giving it our reversi board.
        game.setReversiBoard(reversiBoard);
        //Giving it the labels
        game.setLabels(lblBlack, lblWhite, lblCurrent);
       try {
            Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
           // Stage window = (Stage)root.getScene().getWindow();
            game.setWindowMenu(menu, root);
        } catch (IOException e) {
           System.out.println("Problem reading the menu");
        }
        //Running the game.
        game.run();
        //Handaling window resize - the code is from the recitation.
        //Handaling width resize.
        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue() - 240;
            reversiBoard.setPrefWidth(boardNewWidth);
            reversiBoard.draw();
        });
        //Handaling height resize.
        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            reversiBoard.setPrefHeight(newValue.doubleValue() - 120);
            reversiBoard.draw();
        });
    }

    /**
     * menuAction
     * The function called while clicking on menu button
     */
    public void menuAction(ActionEvent event) throws  IOException {
        //True - the user clicked yes , false - otherwise
        boolean clicked = AlertBox.yesNo("alert", "This game is going to be restarted.\n Are you sure?");
        //If clicked yes go to menu screen, otherwise do nothing.
        if (clicked) {
            try {
                Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                //Getting the stage from the root
                Stage window = (Stage) root.getScene().getWindow();
                Scene menuScene = new Scene(menu);
                //Switching to the menu screen
                window.setScene(menuScene);
                //Adding css and showing it
                menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                window.show();
            } catch (IOException e) {
                System.out.println("Porblem reading Menu.fxml");
            }
        }
    }

}
