package sample;

import Reversi.ReadFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class SettingsController{
    @FXML
    public HBox root;
    @FXML
    public ComboBox<String> firstColor, secondColor;
    @FXML
    private ComboBox<Integer> size;
    @FXML
    public Button applyButton;
    public void applyAction() {
        ReadFile f = new ReadFile();
        f.openFile();
        //f.clearFile();
        List listSets = new ArrayList<>();
        listSets.add(firstColor.getValue());
        listSets.add(secondColor.getValue());
        listSets.add(size.getValue());
        for (int i = 0; i < listSets.size(); i++) {
            System.out.println(listSets.get(i));
        }
        f.writeToFile(listSets);
        f.closeTheFile();
    }
}
