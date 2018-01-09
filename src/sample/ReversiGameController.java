package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
     @Override
     public void initialize(URL location, ResourceBundle resources) {
         ReversiBoard reversiBoard = new ReversiBoard(8,8);
         reversiBoard.setPrefWidth(400);
         reversiBoard.setPrefHeight(400);
         root.getChildren().add(0, reversiBoard);
         reversiBoard.draw();

     }

}
