package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * Main.
 * Our main function of the game.
 * Starts with the menu.
 */
public class Main extends Application {
    /**
     * start - an application method.
     * Starts our game - accessing to the menu.
     * @param primaryStage a stage.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            //Loading the menu.
            GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
            //Creating scene of the menu.
            Scene scene = new Scene(root,750,500);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi game");
            //Showing the menu.
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * main
     * @param args in our case we get the current state.
     * The main function.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
