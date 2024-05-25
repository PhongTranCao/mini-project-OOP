package controller;

import model.Stack;
import view.StackView;

public class StackController extends AbstractControllerFactory {
    public StackController() {
        Stack<Integer> stack = new Stack<>();
        setModelType(stack);
        setView(new StackView());
        initialize();
    }

}
