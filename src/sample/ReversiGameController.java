package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    @FXML
    private Button btn;
     @Override
     public void initialize(URL location, ResourceBundle resources) {
         ReversiBoard reversiBoard = new ReversiBoard(8,8);
         reversiBoard.setPrefWidth(400);
         reversiBoard.setPrefHeight(400);

         root.getChildren().add(0, reversiBoard);
         reversiBoard.draw();

     }

    /**
     * startAction
     * The function called while clicking on start button
     */
     public void startAction() {
         System.out.println("clicked me");
         AlertBox.display("alert", "clicked");
     }

    public void endAction() {
        AlertBox.display("End", "You chose to end the game");
    }
}
