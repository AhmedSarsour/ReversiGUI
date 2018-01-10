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

    public static void display(String title, String message) {
        Stage alert = new Stage();

        //Block events to other windows
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> alert.close());

        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        alert.setScene(new Scene(layout));
        alert.showAndWait();
    }

}