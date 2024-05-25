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
}
