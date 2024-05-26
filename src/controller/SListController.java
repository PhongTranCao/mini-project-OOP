package controller;

import model.SList;
import view.SListView;

public class SListController extends AbstractControllerFactory {
    public SListController() {
        SList<Integer> sList = new SList<>();
        setModelType(sList);
        setView(new SListView());
        initialize();
    }

    @Override
    protected void handleInsert() {
        if (inputField.getText().isEmpty()
                || !(inputField.getText().matches("\\d*")
                || inputField.getText().matches("\\d \\d*"))) {
            setStatus("Please enter number(s) to continue!");
        }
        else {
            if (inputField.getText().matches("\\d*")){
                if (!modelType.insert(Integer.parseInt(inputField.getText())))
                    setStatus("Insertion Failed!");
                else {
                    if (!modelType.isEmpty()) view.removeNumber();
                    view.display(modelType);
                    setStatus(Integer.parseInt(inputField.getText()) + " has been inserted!");
                }
            }
            else {
                if (!modelType.insert(Integer.parseInt(inputField.getText())))
                    setStatus("Insertion Failed!");
            }
        }
        inputField.clear();
    }
}
