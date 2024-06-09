package model;

public class MyNode<E> {
    protected E element;
    protected MyNode<E> next;
    MyNode(E e) {
        element = e;
    }
    public E getElement() {
        return element;
    }
    public MyNode<E> getNext(){
        return next;
    }
}
