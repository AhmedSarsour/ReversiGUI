package sample;

import Reversi.ReadFile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private Pane root;
    @FXML
    public ComboBox<String> firstColor, secondColor;
    @FXML
    private ComboBox<Integer> size;
    @FXML
    public Button applyButton;
    public void applyAction() {
        ReadFile f = new ReadFile();
        f.openFile();
        List listSets = new ArrayList<>();
        if (firstColor.getValue() == null) {
            firstColor.getSelectionModel().select(0);
        }
        if (secondColor.getValue() == null) {
            secondColor.getSelectionModel().select(0);
        }
        if (size.getValue() == null) {
            size.getSelectionModel().select(4);
        }
        listSets.add(firstColor.getValue());
        listSets.add(secondColor.getValue());
        listSets.add(size.getValue());
        for (int i = 0; i < listSets.size(); i++) {
            System.out.println(listSets.get(i));
        }
        f.writeToFile(listSets);
        f.closeTheFile();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * i dont know but i think we need to add something like Pane.draw()?
         */
        //        root.widthProperty().addListener((observable, oldValue, newValue) -> {
//            double boardNewWidth = newValue.doubleValue() - 240;
//            reversiBoard.setPrefWidth(boardNewWidth);
//            reversiBoard.draw();
//        });
//        //Handling height resize.
//        root.heightProperty().addListener((observable, oldValue, newValue) -> {
//            reversiBoard.setPrefHeight(newValue.doubleValue() - 120);
//            reversiBoard.draw();
//        });
    }
}
