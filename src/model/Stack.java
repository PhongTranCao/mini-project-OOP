package model;

import java.util.ArrayList;
import java.util.Collections;

public class Stack <E extends Comparable<E>> extends AbstractArray<E> {
    protected StackNode<E> root;
    protected int maxSize = 0, size = 0;
    public static class StackNode<E> {
        protected E element;
        protected StackNode<E> next;
        StackNode(E e) {
            element = e;
        }
        public E getElement() {
            return element;
        }
        public StackNode<E> getNext(){
            return next;
        }
    }

    public StackNode<E> getRoot(){
        return root;
    }
    public void setMaxSize (int maxSize){
        this.maxSize = maxSize;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public int getSize() {
        return size;
    }
    public StackNode<E> createNode (E e){
        return new StackNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        if (size == 0){
            root = createNode(e);
            size++;
            return true;
        }
        if (size >= maxSize) return false; // thêm ngoại lệ vào đây
        StackNode<E> cur = createNode(e);
        cur.next = root;
        root = cur;
        size++;
        return true;
    }

    @Override
    public boolean delete() {
        if (size == 0) return false;
        root = root.next;
        size--;
        return true;
    }

    public void deleteAll(){
        while (root != null) {
            root = root.next;
        }
        size = 0;
    }

    @Override
    public boolean search(E e) {
        StackNode<E> tmp = root;
        while(tmp != null){
            if (tmp.element.equals(e)) return true;
            tmp = tmp.next;
        }
        return false; // ngoại lệ khi stack rỗng
    }

    private ArrayList<E> getElements(StackNode<E> tmpNode){
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
        if (size == 0) return false; // ngoại lệ khi stack rỗng
        ArrayList<E> array = getElements(root);
        Collections.sort(array);
        deleteAll();
        for (E e: array) {
            insert(e);
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }
}
