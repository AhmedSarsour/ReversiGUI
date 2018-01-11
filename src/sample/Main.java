package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			HBox root = (HBox)FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
			Scene scene = new Scene(root,650,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			HBox sets = (HBox)FXMLLoader.load(getClass().getResource("settings.fxml"));
			Scene setsScene = new Scene(sets, 650, 500);
			setsScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Button apply = new Button("Apply");
			//sets.getChildren().add(apply);
			//apply.setOnAction(e -> {
			//	primaryStage.setScene(scene);
			//});

			primaryStage.setTitle("Reversi game");
			primaryStage.setScene(setsScene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
