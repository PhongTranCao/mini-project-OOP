package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.SList;
import view.SListView;

public class SListController extends AbstractControllerFactory {
    @FXML
    private TextField inputField2;
    private final SList<Integer> sList = new SList<>();

    public SListController() {
        setModelType(sList);
        setView(new SListView());
        initialize();
    }

    protected void addInputListener(){
        inputField2.textProperty().addListener((_, _, newValue) -> {
            if (!newValue.matches("\\d*")) {
                setStatus("Please enter a valid number");
            } else if (!newValue.matches("")) {
                setStatus("");
            }
        });
    }

    @Override
    protected void handleCreate() {
        addInputListener();
        super.handleCreate();
        inputField2.clear();
    }

    @Override
    protected void handleInsert() {
        if (inputField.getText().isEmpty()
                || !inputField.getText().matches("\\d*")) {
            setStatus("Please enter numbers to continue!");
        }
        else if (inputField2.getText().isEmpty()) {
            super.handleInsert();
        }
        else if (!inputField2.getText().matches("\\d*")) {
            setStatus("Please enter numbers to continue!");
        }
        else {
            if (!sList.insert(Integer.parseInt(inputField.getText()),
                    Integer.parseInt(inputField2.getText()))){
                setStatus("Insertion failed!");
            }
            else {
                if (!modelType.isEmpty()) view.removeNumber();
                view.display(modelType);
                setStatus(Integer.parseInt(inputField.getText())
                        + " has been inserted after"
                        + Integer.parseInt(inputField2.getText()));
            }
        }
        inputField.clear();
        inputField2.clear();
    }

    @Override
    protected void handleDelete() {
        if (inputField.getText().isEmpty()
                || !inputField.getText().matches("\\d*")) {
            setStatus("Please enter a number to continue!");
        }
        else {
            if (!sList.delete(Integer.parseInt(inputField.getText()))){
                setStatus("Deletion failed!");
            }
            else {
                if (!modelType.isEmpty()) view.removeNumber();
                view.display(modelType);
                setStatus(Integer.parseInt(inputField.getText())
                        + " has been deleted!");
            }
        }
        inputField.clear();
        inputField2.clear();
    }

    @Override
    protected void handleSearch() {
        super.handleSearch();
        inputField2.clear();
    }

    @Override
    protected void handleSort() {
        super.handleSort();
        inputField2.clear();
    }

    public void showDialog() {
        super.showDialog("List",
                """
                        Created + <Number> : Create <Number> box(es), indent from 0 to <Number> - 1
                        Insert + <Number>: Insert <Number> into the first boxes of the list
                        Insert + <Number 1> + <Number 2>: Insert <Number 1> right before the position of <Number 2>
                        Delete + <Number>: Delete <Number> from the list
                        Sort: Sort the elements into a new list, in increasing order
                        Search + <Number>: Search <Number> in the list
                        """);
    }
}
