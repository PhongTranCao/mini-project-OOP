package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.AbstractArray;
import view.AbstractViewFactory;

import java.io.IOException;

public class AbstractControllerFactory {
    @FXML
    protected BorderPane borderPane = new BorderPane();
    @FXML
    protected AbstractViewFactory view;
    @FXML
    protected Button backButton;
    @FXML
    protected TextField inputField;
    @FXML
    protected Label statusLabel;
    protected AbstractArray<Integer> modelType;

    public void initialize(){
        borderPane.setCenter(view);
    }


    public void setModelType(AbstractArray<Integer> modelType) {
        this.modelType = modelType;
    }

    public void setView(AbstractViewFactory view) {
        this.view = view;
    }

    @FXML
    protected void handleBack(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main_menu.fxml"));
            Parent root = loader.load();
            MainMenuController mainMenuController = loader.getController();

            Stage stage = (Stage) backButton.getScene().getWindow();
            mainMenuController.setMainStage(stage);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getStylesheets().add("Queue.css");
            stage.setFullScreen(true);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void addInputListener(){
        inputField.textProperty().addListener((_, _, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setStatus("Please enter a valid number");
            } else if (!newValue.matches("")) {
                setStatus("");
            }
        });
    }

    @FXML
    protected void handleCreate() {
        addInputListener();
        if (inputField.getText().isEmpty() || !inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            view.create(Integer.parseInt(inputField.getText()), modelType);
            setStatus("Model created!");
        }
        inputField.clear();
    }
    @FXML
    protected void handleInsert(){
        if (inputField.getText().isEmpty() || !inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            if (!modelType.insert(Integer.parseInt(inputField.getText())))
                setStatus("Insertion Failed!");
            else {
                if (!modelType.isEmpty()) view.removeNumber();
                view.display(modelType);
                setStatus(Integer.parseInt(inputField.getText()) + " has been inserted!");
            }
        }
        inputField.clear();
    }

    @FXML
    protected void handleDelete(){
        //Nhân sửa lại SetStatus
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            int tmp = 0;
            if (!modelType.isEmpty()) tmp = modelType.getRoot().getElement();
            if (!modelType.delete())
                setStatus("Deletion Failed! The model is empty.");
            else {
                view.removeNumber();
                view.display(modelType);
                setStatus("Element with value " + tmp + " has been deleted!");
            }
        }
        inputField.clear();
    }

    @FXML
    protected void handleSearch(){
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            if (modelType.isEmpty()|| !modelType.search(Integer.parseInt(inputField.getText()))
                    || view.getChildren().isEmpty() || inputField.getText().isEmpty()) {
                setStatus("Search Failed!");
            } else view.highlightBox(Integer.parseInt(inputField.getText()));
        }
        inputField.clear();
    }

    @FXML
    protected void handleSort(){
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            if (!modelType.sort()) {
                setStatus("Sort Failed! The model is empty.");
            } else {
                view.removeNumber();
                view.display(modelType);
                setStatus(" has been sorted!");
            }
        }
        inputField.clear();
    }

    public void setStatus(String msg) {
        statusLabel.setAlignment(Pos.TOP_CENTER);
        HBox.setMargin(statusLabel, new Insets(10));
        statusLabel.setText(msg);
    }
}
