package model;

import java.util.ArrayList;
import java.util.Collections;

public class Queue<E extends Comparable<E>> extends AbstractArray<E> {
    protected QueueNode<E> root;
    protected int maxSize = 0, size = 0;
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

    public void setMaxSize(int maxSize){this.maxSize = maxSize;}

    public int getSize() {
        return size;
    }
    public QueueNode<E> createNode (E e){
        return new QueueNode<>(e);
    }

    @Override
    public boolean insert(E e) {//chỉ Nhân không phải sửa
        if (size == 0){
            root = createNode(e);
            size++;
            return true;
        }
        if (size >= maxSize) return false;
        QueueNode<E> tmp = root;
        while(tmp.next != null) tmp = tmp.next;
        tmp.next = createNode(e);
        size++;
        return true;
    }

    @Override
    public boolean delete() { // cả 3 phải sửa
        if (size == 0) return false;
        root = root.next;
        size--;
        return true;
    }

    public void deleteAll(){
        while (root != null){
            root = root.next;
        }
        size = 0;
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
        if (size == 0) return false;
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
