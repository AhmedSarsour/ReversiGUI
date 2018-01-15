package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.QuadCurve;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * SettingsController.
 * This is behind the scenes of the settings screen - what happanes when clicking on buttons and hot i get the
 * information from the user.
 */
public class SettingsController implements Initializable{
    @FXML
    //Our pane
    public Pane pane;
    @FXML
    //The first and second color combo box.
    public ComboBox<String> firstColor, secondColor;
    @FXML
    //The combo box of the size of the board..
    private ComboBox<Integer> size;
    @FXML
    //Our apply button.
    public Button applyButton;
    @FXML
    //The back button and the other buttons.
    public Button backButton,player1, player2,btnSize;
    @FXML
    public TextField text;
    @FXML
    public QuadCurve firstCurve, secondCurve;
    private final String fileName = "menuInfo.txt";

    /**
     * applyAction.
     * This is the apply button action - saving the settings.
     */
    public void applyAction() {
        SettingsReader sr = new SettingsReader(fileName);
        //f.clearFile();
        List listSets = new ArrayList<>();
        //Getting default values in cases of no choosing anything to prevent problems.
        if (firstColor.getValue() == null) {
            firstColor.getSelectionModel().select(0);
        }
        if (secondColor.getValue() == null) {
            secondColor.getSelectionModel().select(0);
        }
        if (size.getValue() == null) {
            size.getSelectionModel().select(4);
        }
        //Adding those properties from the combo boxes to list.
        listSets.add(firstColor.getValue());
        listSets.add(secondColor.getValue());
        listSets.add(size.getValue());
        //Writing the values from the combo boxes to the file.
        sr.writeToFile(fileName, listSets);
        AlertBox.display("Settings", "The settings has changed.");
        //Return to the menu.
        backAction();
    }

    /**
     * backAction.
     * Called when pressing on the back button.
     */
    public void backAction() {
        try {
            //Loading the menu from it's fxml.
            Parent menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            //Getting the stage from the root
            Stage window = (Stage)pane.getScene().getWindow();
            Scene menuScene = new Scene(menu, 750, 500);
            //Moving to the menu scene.
            window.setScene(menuScene);
            menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            window.show();
        } catch (IOException e) {
            System.out.println("Problem reading Menu.fxml");
        }
    }
    @Override
    /**
     * Called to initialize a controller after its root element has been
     * completely processed. In our case we use it to handle screen resize and get settings.
     * @param location
     * The location used to resolve relative paths for the root object.
     * @param resources
     * The resources used to localize the root object.
     */
    public void initialize(URL location, ResourceBundle resources) {
        //Reading the settings from our settings file.
        SettingsReader f = new SettingsReader("menuInfo.txt");
        //Select our selections by the settings file.
        firstColor.getSelectionModel().select(f.getFirstPlayer());
        secondColor.getSelectionModel().select(f.getSecondPlayer());
        size.getSelectionModel().select(f.getBoardSize() - 4);
        //Closing our file.
        f.closeTheFile();
        //Adding listener to pane's width in order to handle screen resize.
        pane.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue()/10;
            //Now we will set the weight of each element in our screen.
            //Settings the back and apply buttons.
            applyButton.setPrefWidth(boardNewWidth);
            backButton.setPrefWidth(boardNewWidth);
            //Settings the width of the players.
            firstColor.setPrefWidth(boardNewWidth + boardNewWidth / 2);
            secondColor.setPrefWidth(boardNewWidth + boardNewWidth / 2);
            size.setPrefWidth(boardNewWidth + boardNewWidth / 2);
            player1.setPrefWidth(boardNewWidth + boardNewWidth / 3);
            player2.setPrefWidth(boardNewWidth + boardNewWidth / 3);
            //Settings size elements.
            btnSize.setPrefWidth(boardNewWidth + boardNewWidth / 3);
            text.setPrefWidth(newValue.doubleValue());
            //Settings layout width - the x value.
            player1.setLayoutX(newValue.doubleValue() / 3);
            player2.setLayoutX(newValue.doubleValue() / 3);
            btnSize.setLayoutX(newValue.doubleValue() / 3);
            applyButton.setLayoutX(newValue.doubleValue() / 3 + 2 * boardNewWidth / 3);
            firstColor.setLayoutX(newValue.doubleValue() / 3 + 6 * boardNewWidth / 5 + 10);
            secondColor.setLayoutX(newValue.doubleValue() / 3 + 6 * boardNewWidth / 5 + 10);
            size.setLayoutX(newValue.doubleValue() / 3 + 6 * boardNewWidth / 5 + 10);
            secondCurve.setLayoutX(newValue.doubleValue() - 50);
            }
        );
        //Handaling height resize.
        //Adding listener to pane's height in order to handle screen resize.
        pane.heightProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewHeight = newValue.doubleValue()/10;
            //Now we will set the height of each element in our screen.
            //Settings the back and apply buttons.
            applyButton.setPrefHeight(boardNewHeight / 2);
            backButton.setPrefHeight(boardNewHeight / 2);
            //Settings the height of the players.
            firstColor.setPrefHeight(boardNewHeight /2);
            secondColor.setPrefHeight(boardNewHeight / 2);
            //Settings size elements.
            size.setPrefHeight(boardNewHeight / 2);
            //Setting size of other elements - not the combo box.
            player1.setPrefHeight(boardNewHeight / 2);
            player2.setPrefHeight(boardNewHeight / 2);
            btnSize.setPrefHeight(boardNewHeight / 2);
            text.setPrefHeight(newValue.doubleValue());
            firstCurve.setLayoutY(newValue.doubleValue());
            }
        );
    }
}
