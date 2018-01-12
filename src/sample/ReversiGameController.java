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
 * Created by eliad1998 on 09/01/2018.
 */
public class ReversiGameController implements Initializable {
    @FXML
    private HBox root;


     @Override
     public void initialize(URL location, ResourceBundle resources) {

         //Getting information from the file.
         SettingsReader settingsReader = new SettingsReader("menuInfo.txt");
         int size = settingsReader.getBoardSize();
         ReversiBoard reversiBoard = new ReversiBoard(size,size);
         reversiBoard.setPrefWidth(400);
         reversiBoard.setPrefHeight(400);

         reversiBoard.setPadding(new Insets(20,20,20,20));
         reversiBoard.setAlignment(Pos.CENTER);

         root.getChildren().add(0, reversiBoard);
         reversiBoard.draw();
         VBox vBox = (VBox) root.getChildren().get(1);
         Label lblCurrent = (Label)vBox.getChildren().get(1);
         Label lblBlack = (Label)vBox.getChildren().get(2);
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
     * startAction
     * The function called while clicking on start button
     */
     public void menuAction(ActionEvent event) throws  IOException {

         AlertBox.display("alert", "This game is going to be restarted.\n Are you sure?");
         try {
             Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
             //Getting the stage from the root
             Stage window = (Stage)root.getScene().getWindow();

             Scene menuScene = new Scene(menu);

             window.setScene(menuScene);
             menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

             window.show();
         } catch (IOException e) {
            System.out.println("Porblem reading Menu.fxml");
         }
        // Stage windows = (Stage) ((Node) event.getSource().get
     }

}
