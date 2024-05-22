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
import model.Stack;
import view.StackView;

import java.io.IOException;
import java.util.Objects;

public class StackController {
    private final Stack<Integer> stack;
    @FXML
    private final StackView view;
    @FXML
    private BorderPane borderPane = new BorderPane();
    @FXML
    private Button backButton;
    @FXML
    private TextField inputField;
    @FXML
    private Label statusLabel;

    public void initialize(){
        borderPane.setCenter(view);
    }
    public StackController() {
        stack = new Stack<>();
        view = new StackView(stack);
        initialize();
    }

    @FXML
    private void handleBack(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/main_menu.fxml"));
            Parent root = loader.load();
            MainMenuController mainMenuController = loader.getController();

            Stage stage = (Stage) backButton.getScene().getWindow();
            mainMenuController.setMainStage(stage);
            stage.setScene(new Scene(root, 300, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addInputListener(){
        inputField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setStatus("Please enter a valid number");
            } else if (!newValue.matches("")) {
                setStatus("");
            }
        });
    }

    @FXML
    private void handleCreate() {
        addInputListener();
        if (inputField.getText().isEmpty() || !inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            view.createStack(Integer.parseInt(inputField.getText()));
            setStatus("Created Stack!");
        }
        inputField.clear();
    }
    @FXML
    private void handleInsert(){
        if (inputField.getText().isEmpty() || !inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            if (!stack.insert(Integer.parseInt(inputField.getText())))
                setStatus("Insertion Failed!");
            else {
                if (!stack.isEmpty()) view.removeStackNumber();
                view.displayStack();
                setStatus(Integer.parseInt(inputField.getText()) + " has been inserted!");
            }
        }
        inputField.clear();
    }
    @FXML
    private void handleDelete(){
        //Nhân sửa lại SetStatus
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            int tmp = 0;
            if (!stack.isEmpty()) tmp = stack.getRoot().getElement();
            if (!stack.delete())
                setStatus("Deletion Failed! The stack is empty.");
            else {
                view.removeStackNumber();
                view.displayStack();
                setStatus("Element with value " + tmp + " has been deleted!");
            }
        }
        inputField.clear();
    }
    @FXML
    private void handleSearch(){
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            if (!stack.search(Integer.parseInt(inputField.getText()))
                    || view.getChildren().isEmpty() || inputField.getText().isEmpty()) {
                setStatus("Search Failed!");
            } else view.highlightBox(Integer.parseInt(inputField.getText()));
        }
        inputField.clear();
    }
    @FXML
    private void handleSort(){
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            if (!stack.sort()) {
                setStatus("Sort Failed! The stack is empty.");
            } else {
                view.removeStackNumber();
                view.displayStack();
                setStatus("Stack has been sorted!");
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
