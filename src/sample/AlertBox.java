package sample;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 * AlertBox.
 * This class represents an alert box like javascript.
 * This class contains only function display which we create a new windows of alert box.
 */
public class AlertBox {
    //Boolean setting to yes in case of clicking yes.
    private boolean yes = false;

    /**
     * display.
     * Displaying alert on the screen.
     * @param title the title of the alert window.
     * @param message the message of the alert
     */
    public static void display(String title, String message) {
        //New stage because we open new window.
        Stage alert = new Stage();
        //Block events to other windows
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setMinWidth(250);
        //The message of the alert.
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> alert.close());
        //Showingthe data.
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        //Display window and wait for it to be closed before returning
        alert.setScene(new Scene(layout));
        alert.showAndWait();
    }

    /**
     * setYes.
     * Setting the yes boolean.
     * @param bool the boolean we want to set yes to.
     */
    public void setYes(boolean bool) {
        this.yes = bool;
    }

    /**
     * getYes.
     * @return the our property yes.
     */
    public boolean getYes() {
        return this.yes;
    }

    /**
     * yesNo.
     * Creates alert with yes and no buttons.
     * @param title the title of the alert.
     * @param message the message in the alert.
     * @return true - clicked on yes, false otherwise.
     */
    public static boolean yesNo(String title, String message){
        //Creating new AlertBox because it holds the yes boolean.
        AlertBox alertBox = new AlertBox();
        //New stage because we open new window.
        Stage alert = new Stage();
        //Block events to other windows
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setMinWidth(250);
        //Showing the message.
        Label label = new Label();
        label.setText(message);
        //The yes button - setting yes to true and close.
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
                    alert.close();
                    alertBox.setYes(true);
                }
        );
        //The yes button - setting yes to false and close.
        Button noButton = new Button("No");
        noButton.setOnAction(e-> {
                    alert.close();
                    alertBox.setYes(false);
        });
        //Showing the data.
        VBox layout = new VBox(20);
        //Holds the buttons.
        HBox buttons = new HBox(20);
        //Adding the buttons.
        buttons.getChildren().addAll(yesButton, noButton);
        buttons.setAlignment(Pos.CENTER);
        //Adding message and buttons.
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);
        //Display window and wait for it to be closed before returning
        alert.setScene(new Scene(layout));
        alert.showAndWait();
        return alertBox.getYes();
    }

}