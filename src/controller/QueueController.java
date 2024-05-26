package controller;

import model.Queue;
import view.QueueView;

public class QueueController extends AbstractControllerFactory{
    public QueueController (){
        Queue<Integer> queue = new Queue<>();
        setModelType(queue);
        setView(new QueueView());
        initialize();
    }
    public void showDialog() {
        super.showDialog("Queue",
                """
                        Created + <Number> : Create <Number> box(es), indent from 0 to <Number> - 1
                        Insert + <Number> : Insert <Number> into the first boxes of the queue
                        Delete : Delete the last element of the queue
                        Sort : Sort the elements into a new queue, in increasing order
                        Search + <Number> w: Search <Number> in the queue
                        """);
    }
}
