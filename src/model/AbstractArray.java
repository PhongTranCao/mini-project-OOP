package model;

import java.util.ArrayList;
import java.util.Collections;

public abstract class AbstractArray<E extends Comparable<E>> implements Array<E> {
    protected MyNode<E> root;
    protected int maxSize = 0, size = 0;

    public MyNode<E> getRoot(){
        return root;
    }

    public void setMaxSize(int maxSize){this.maxSize = maxSize;}

    public int getSize() {
        return size;
    }

    public MyNode<E> createNode (E e){
        return new MyNode<>(e);
    }

    public void deleteAll(){
        while (root != null){
            root = root.next;
        }
        size = 0;
    }

    @Override
    public boolean search(E e) {
        MyNode<E> tmp = root;
        while(tmp != null){
            if (tmp.element.equals(e)) return true;
            tmp = tmp.next;
        }
        return false;
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

    private ArrayList<E> getElements(MyNode<E> tmpNode){
        ArrayList<E> arrayList = new ArrayList<>();
        arrayList.add(tmpNode.element);
        while (tmpNode.next != null){
            tmpNode = tmpNode.next;
            arrayList.add(tmpNode.element);
        }
        return arrayList;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }
}
