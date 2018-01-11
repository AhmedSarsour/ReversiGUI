package sample;

import Reversi.Board;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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
         int size = 8;
         ReversiBoard reversiBoard = new ReversiBoard(size,size);
         reversiBoard.setPrefWidth(400);
         reversiBoard.setPrefHeight(400);

         reversiBoard.setPadding(new Insets(50,50,50,50));
         //reversiBoard.setVgap(16);
         //reversiBoard.setHgap(20);
         reversiBoard.setAlignment(Pos.CENTER);

         root.getChildren().add(0, reversiBoard);
         reversiBoard.draw();

         reversiBoard.setOnMouseClicked(e -> {
             Board board = reversiBoard.getBoard();
             AlertBox.display("title", "Just clicked " + reversiBoard.getClicked().toString());
             int xPut = reversiBoard.getClicked().getX() + 1;
             int yPut = reversiBoard.getClicked().getY() + 1;
             // In case of wrong point to put it won't put it
             board.put(xPut, yPut, 'X');
             board.show();
             //Rest the clicked point to -1,-1 in case of clicking out of board area
             reversiBoard.resetClicked();

         });


         root.widthProperty().addListener((observable, oldValue, newValue) -> {
             double boardNewWidth = newValue.doubleValue() - 240;
             reversiBoard.setPrefWidth(boardNewWidth);
             reversiBoard.draw();
         });
         root.heightProperty().addListener((observable, oldValue, newValue) -> {
             reversiBoard.setPrefHeight(newValue.doubleValue() - 120);
             reversiBoard.draw();
         });
     }

    /**
     * startAction
     * The function called while clicking on start button
     */
     public void startAction() {

         AlertBox.display("alert", "clicked");
     }

    public void endAction() {
        AlertBox.display("End", "You chose to end the game");
    }
}
