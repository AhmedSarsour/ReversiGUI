package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController implements Initializable{
    @FXML
    public Pane pane;
    @FXML
    public ComboBox<String> firstColor, secondColor;
    @FXML
    private ComboBox<Integer> size;
    @FXML
    public Button applyButton;
    @FXML
    public Button backButton,player1, player2,btnSize;
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
        AlertBox.display("Settings", "The settings has changed.");
        backAction();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue()/10;
            applyButton.setPrefWidth(boardNewWidth);
            backButton.setPrefWidth(boardNewWidth);
            firstColor.setPrefWidth(boardNewWidth);
            secondColor.setPrefWidth(boardNewWidth);
            size.setPrefWidth(boardNewWidth);
            player1.setPrefWidth(boardNewWidth);
            player2.setPrefWidth(boardNewWidth);
            btnSize.setPrefWidth(boardNewWidth);

        });
        //Handaling height resize.
        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewHeight = newValue.doubleValue()/10;
            applyButton.setPrefHeight(boardNewHeight);
            backButton.setPrefHeight(boardNewHeight);
            firstColor.setPrefHeight(boardNewHeight /2);
            secondColor.setPrefHeight(boardNewHeight / 2);
            size.setPrefHeight(boardNewHeight / 2);
            player1.setPrefWidth(boardNewHeight);
            player2.setPrefWidth(boardNewHeight);
            btnSize.setPrefWidth(boardNewHeight);

        });
    }

}
