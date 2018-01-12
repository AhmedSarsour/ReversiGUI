package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements  Initializable{
    @FXML
    private GridPane menu;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    public void StartAction() throws IOException {
        try {
            Stage primaryStage = (Stage) menu.getScene().getWindow();

            HBox root = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));

            Scene scene = new Scene(root,650,500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setTitle("Reversi game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Porblem reading ReversiGame.fxml");
        }
    }
    public void SettingsAction() throws IOException {
        try {
            Stage primaryStage = (Stage) menu.getScene().getWindow();

            Parent settings = FXMLLoader.load(getClass().getResource("settings.fxml"));
            //Getting the stage from the root

            Scene settingsScene = new Scene(settings);

            primaryStage.setScene(settingsScene);
            settingsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        } catch (IOException e) {
            System.out.println("Porblem reading settings.fxml");
        }
    }
    public void ExitAction() {
        Stage primaryStage = (Stage) menu.getScene().getWindow();
        primaryStage.close();
    }
}
