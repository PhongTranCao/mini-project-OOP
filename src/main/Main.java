package main;

import controller.MainMenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/main_menu.fxml"));
        Parent root = fxmlLoader.load();

        MainMenuController controller = fxmlLoader.getController();
        controller.setMainStage(primaryStage);
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Queue.css");

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }
}