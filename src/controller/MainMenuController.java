package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenuController {
    private Stage mainStage; // Add this field
    @FXML
    private static final Logger LOGGER = Logger.getLogger(MainMenuController.class.getName());
    public void initialize() {}
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
    @FXML
    private void handleQueue() {
        handleTreeTypeSelection("/view/Queue.fxml", "Queue Visualization");
    }
    @FXML
    private void handleStack() {
        handleTreeTypeSelection("/view/Stack.fxml", "Stack Visualization");
    }
    @FXML
    private void showHelpDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Visualization App Help");
        alert.setContentText("This is a visualization application. "
                + "Please select a type of data structure to visualize.");

        alert.showAndWait();
    }
    @FXML
    private void askForConfirmationAndExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Platform.runLater(() -> {
                    if (mainStage != null) {
                        mainStage.close();
                    }
                });
            }
        });
    }

    private void handleTreeTypeSelection(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            scene.getStylesheets().add("Queue.css");
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle(title);
            stage.show();

            if (mainStage != null) {
                mainStage.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load the FXML file: " + fxmlFile, e);
        }
    }

}

