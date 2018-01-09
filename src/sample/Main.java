package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Object fxmlLoader = FXMLLoader.load(getClass().getResource("ReversiGame.fxml"));
            HBox root = (HBox)fxmlLoader;

            Scene scene = new Scene(root,520,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setTitle("Maze game"); primaryStage.setScene(scene); primaryStage.show();
        }
    catch(Exception e) {
            e.printStackTrace(); }
    }
    public static void main(String[] args) {
        launch(args); }
}
