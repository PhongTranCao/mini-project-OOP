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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Queue;
import view.QueueView;

import java.io.IOException;
import java.util.Objects;

public class QueueController{
    private final Queue<Integer> queue;
    @FXML
    private final QueueView view;
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
    public QueueController() {
        queue = new Queue<>();
        view = new QueueView(queue);
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

    private void addInputListener(){ //My + Cương hàm matches sửa đc thì càng tốt
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
            setStatus("Please enter a number!");
        }
        else {
            view.createQueue(Integer.parseInt(inputField.getText()));
            setStatus("Created Queue!");
        }
        inputField.clear();
    }
    @FXML
    private void handleInsert(){ //My + Cương sửa lại tham số trong hàm queue.insert()
        //và sửa setStatus
        if (inputField.getText().isEmpty() || !inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number!");
        }
        else {
            if (!queue.insert(Integer.parseInt(inputField.getText())))
                setStatus("Insertion Failed!");
            else {
                if (!queue.isEmpty()) view.removeQueueNumber();
                view.displayQueue();
                setStatus(Integer.parseInt(inputField.getText()) + " has been inserted!");
            }
        }
        inputField.clear();
    }
    @FXML
    private void handleDelete(){//My + Cương sửa lại số trong hàm queue.delete() và sửa setStatus
        //Nhân sửa lại SetStatus
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number!");
        }
        else {
            int tmp = 0;
            if (!queue.isEmpty()) tmp = queue.getRoot().getElement();
            if (!queue.delete())
                setStatus("Deletion Failed!");
            else {
                view.removeQueueNumber();
                view.displayQueue();
                setStatus(tmp + " element has been deleted!");
            }
        }
        inputField.clear();
    }
    @FXML
    private void handleSearch(){
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number!");
        }
        else {
            if (queue.isEmpty() || !queue.search(Integer.parseInt(inputField.getText()))
                || view.getChildren().isEmpty() || inputField.getText().isEmpty()) {
                setStatus("Search Failed!");
            } else view.highlightBox(Integer.parseInt(inputField.getText()));
        }
        inputField.clear();
    }
    @FXML
    private void handleSort(){
        if (!inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number!");
        }
        else {
            if (!queue.sort()) {
                setStatus("Sort Failed!");
            } else {
                view.removeQueueNumber();
                view.displayQueue();
                setStatus("Queue has been sorted!");
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
