package model;

import java.util.ArrayList;
import java.util.Collections;

public class Queue<E extends Comparable<E>> extends AbstractArray<E> {
    protected QueueNode<E> root;
    protected int size = 0, tail = 0;
    public static class QueueNode<E> {
        protected E element;
        protected QueueNode<E> next;
        QueueNode(E e) {
            element = e;
        }
        public E getElement() {
            return element;
        }
        public QueueNode<E> getNext(){
            return next;
        }
    }

    public QueueNode<E> getRoot(){
        return root;
    }
    public void setSize(int size){this.size = size;}
    @Override
    public int getSize() {
        return size;
    }
    public int getTail() {
        return tail;
    }
    public QueueNode<E> createNode (E e){
        return new QueueNode<>(e);
    }

    @Override
    public boolean insert(E e) {//chỉ Nhân không phải sửa
        if (tail == 0){
            root = createNode(e);
            tail++;
            return true;
        }
        if (tail >= size) return false;
        QueueNode<E> tmp = root;
        while(tmp.next != null) tmp = tmp.next;
        tmp.next = createNode(e);
        tail++;
        return true;
    }

    @Override
    public boolean delete() { // cả 3 phải sửa
        if (tail == 0) return false;
        root = root.next;
        tail--;
        return true;
    }

    public void deleteAll(){
        while (root != null){
            root = root.next;
        }
        tail = 0;
    }

    @Override
    public boolean search(E e) {
        QueueNode<E> tmp = root;
        while(tmp != null){
            if (tmp.element.equals(e)) return true;
            tmp = tmp.next;
        }
        return false;
    }

    private ArrayList<E> getElements(QueueNode<E> tmpNode){
        ArrayList<E> arrayList = new ArrayList<>();
        arrayList.add(tmpNode.element);
        while (tmpNode.next != null){
            tmpNode = tmpNode.next;
            arrayList.add(tmpNode.element);
        }
        return arrayList;
    }

    @Override
    public boolean sort() {
        if (tail == 0) return false;
        ArrayList<E> array = getElements(root);
        Collections.sort(array);
        deleteAll();
        for (E e:
             array) {
            insert(e);
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }
}
