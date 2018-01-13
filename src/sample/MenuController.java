package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * MenuController.
 * This class is behind the scenes of the menu - here we do the menu options - start, exit and settings.
 */
public class MenuController{
    @FXML
    //Our menu gridpane.
    private GridPane menu;

    /**
     * StartAction
     * Here we move to the window of the game itself.
     * @throws IOException in case of problem with reading the game's fxml.
     */
    public void startAction() throws IOException {
        //Trying move to the game.
        try {
            //Getting our stage.
            Stage primaryStage = (Stage) menu.getScene().getWindow();
            //Loading the game.
            HBox root = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            Scene scene = new Scene(root,650,500);
            //Applying the style.
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Reversi game");
            //Moving to the scene of the game itself.
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Porblem reading ReversiGame.fxml");
        }
    }

    /**
     * settingsAction.
     * This is what happaned when we click on settings button - we move to the settings of this game and edit it.
     * @throws IOException in case of problem with reading the menu's fxml.
     */
    public void settingsAction() throws IOException {
        //Trying move to the settings.
        try {
            //Getting the stage from our menu.
            Stage primaryStage = (Stage) menu.getScene().getWindow();
            //Getting the settings by loading it from it's fxml.
            Parent settings = FXMLLoader.load(getClass().getResource("settings.fxml"));
            //Getting the stage from the root
            Scene settingsScene = new Scene(settings);
            //Moving to the settings scene.
            primaryStage.setScene(settingsScene);
            settingsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        } catch (IOException e) {
            System.out.println("Porblem reading settings.fxml");
        }
    }

    /**
     * exitAction.
     * When clicking on exit button we will exit the game.
     */
    public void exitAction() {
        AlertBox.display("Exit", "You chose to exit the game");
        //Getting our stage.
        Stage primaryStage = (Stage) menu.getScene().getWindow();
        //Closing our screen.
        primaryStage.close();
    }
}
