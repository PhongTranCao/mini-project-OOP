package view;

import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.Stack;
import model.Stack.StackNode;

public class StackView extends HBox {
    private final Stack<Integer> stack;

    public StackView(Stack<Integer> stack) {
        this.stack = stack;
        this.setAlignment(Pos.CENTER);
    }

    public void createStack(int inputNumber) {
        this.getChildren().clear();
        stack.deleteAll();
        stack.setMaxSize(inputNumber);
        for (int i = 0; i < inputNumber; i++) {
            StackPane stack = new StackPane();

            Rectangle rect = new Rectangle(100, 100);
            rect.setFill(Color.LIGHTGRAY);
            rect.setStroke(Color.BLACK);

            Label numberLabel = new Label();
            numberLabel.setFont(new Font(20));
            stack.getChildren().addAll(rect, numberLabel);
            stack.setAlignment(Pos.CENTER);

            Label indexLabel = new Label(String.valueOf(i));
            indexLabel.setFont(new Font(20));
            VBox boxWithLabel = new VBox(5);
            boxWithLabel.getChildren().addAll(stack, indexLabel);

            this.getChildren().add(boxWithLabel);
            boxWithLabel.setAlignment(Pos.CENTER);
        }
    }

    public void removeStackNumber() {
        int count = 0;
        for (Node node : this.getChildren()) {
            if (count == this.getChildren().size()) break;
            if (node instanceof VBox boxWithLabel) {
                StackPane stack = (StackPane) boxWithLabel.getChildren().get(0);
                Label numberLabel = (Label) stack.getChildren().get(1);
                numberLabel.setText("");
                count++;
            }
        }
    }

    public void displayStack() {
        removeStackNumber(); // Clear the displayed numbers
        if (stack.getRoot() != null) {
            displayStack(stack.getRoot());
        }
    }

    public void displayStack(StackNode<Integer> root) {
        int count = 0;
        for (Node node : this.getChildren()) {
            if (count == stack.getSize() || root == null) break;
            if (node instanceof VBox boxWithLabel) {
                StackPane stack = (StackPane) boxWithLabel.getChildren().get(0);
                Label numberLabel = (Label) stack.getChildren().get(1);
                numberLabel.setText(String.valueOf(root.getElement()));
                root = root.getNext();
                count++;
            }
        }
    }

    public void highlightBox(int value) {
        for (Node n : this.getChildren()) {
            if (n instanceof VBox boxWithLabel) {
                StackPane stack = (StackPane) boxWithLabel.getChildren().get(0);
                Rectangle rect = (Rectangle) stack.getChildren().get(0);
                Label numberLabel = (Label) stack.getChildren().get(1);
                if (numberLabel.getText().isEmpty()) continue;
                if (Integer.parseInt(numberLabel.getText()) == value){
                    rect.setFill(Color.AQUA);
                    numberLabel.setTextFill(Color.RED);
                    PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
                        numberLabel.setTextFill(Color.BLACK);
                        rect.setFill(Color.LIGHTGRAY);
                    });
                    pause.play();
                }
            }
        }
    }
}
