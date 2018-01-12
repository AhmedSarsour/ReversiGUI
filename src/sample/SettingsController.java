package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class SettingsController{
    @FXML
    public Pane pane;
    @FXML
    public ComboBox<String> firstColor, secondColor;
    @FXML
    private ComboBox<Integer> size;
    @FXML
    public Button applyButton;
    final String fileName = "menuInfo.txt";
    public void applyAction() {

        SettingsReader sr = new SettingsReader(fileName);
        //f.clearFile();
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
        sr.writeToFile(fileName, listSets);

    }

    public void backAction() {
        try {
            Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            //Getting the stage from the root
            Stage window = (Stage)pane.getScene().getWindow();

            Scene menuScene = new Scene(menu);

            window.setScene(menuScene);
            menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            window.show();
        } catch (IOException e) {
            System.out.println("Porblem reading Menu.fxml");
        }

    }
}
