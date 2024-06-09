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
    public void showDialog() {
        super.showDialog("Stack",
                """
                        Created + <Number> : Create <Number> box(es), indent from 0 to <Number> - 1
                        Insert + <Number> : Insert <Number> into the first boxes of the stack
                        Delete : Delete the first element of the stack
                        Sort: Sort the elements into a new stack, in increasing order
                        Search + <Number>: Search <Number> in the stack
                        """);
    }
}
