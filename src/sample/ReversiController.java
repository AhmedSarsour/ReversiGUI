package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by eliad1998 on 09/01/2018.
 */
public class ReversiController implements Initializable {
    @FXML
    private HBox root;
     @Override
     public void initialize(URL location, ResourceBundle resources) {
         ReversiBoard mazeBoard = new ReversiBoard(8,8);
         mazeBoard.setPrefWidth(400);
         mazeBoard.setPrefHeight(400);
         root.getChildren().add(0, mazeBoard);
         mazeBoard.draw();
     }
}
