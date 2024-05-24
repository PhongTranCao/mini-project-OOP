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
import model.Queue;
import model.MyNode;

public class QueueView extends AbstractViewFactory {
    private final Queue<Integer> queue;

    public QueueView(Queue<Integer> queue) {
        this.queue = queue;
        this.setAlignment(Pos.CENTER);
    }

}
