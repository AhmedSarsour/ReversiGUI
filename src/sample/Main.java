package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {

            Pane sets = (Pane)FXMLLoader.load(getClass().getResource("settings.fxml"));
            Scene setsScene = new Scene(sets, 650, 500);
            setsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Button back = new Button("Back");
            sets.getChildren().add(back);
            //back.setLayoutX(300.0);
            //back.setLayoutY(300.0);
            VBox menu = new VBox();
            Scene menuScene = new Scene(menu, 650, 500);
            menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            Button startButton = new Button("     START     ");
            Button setsButton = new Button("  SETTINGS  ");
            Button exitButton = new Button("      EXIT      ");
            startButton.prefWidth(40);
            setsButton.prefWidth(40);
            exitButton.prefWidth(40);

            menu.setSpacing(20);
            menu.setAlignment(Pos.CENTER);
            menu.getChildren().add(startButton);
            menu.getChildren().add(exitButton);
            menu.getChildren().add(setsButton);
            startButton.setOnAction(e -> {
                try {
                    HBox root = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
                    Scene scene = new Scene(root,650,500);
                    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                    primaryStage.setScene(scene);
                }catch(Exception a) {
                    a.printStackTrace();
                }
            });
            setsButton.setOnAction(e -> primaryStage.setScene(setsScene));
            exitButton.setOnAction(e -> primaryStage.close());
            back.setOnAction(e -> primaryStage.setScene(menuScene));
            primaryStage.setTitle("Reversi game");
            primaryStage.setScene(menuScene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
