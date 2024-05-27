package model;

public class Queue<E extends Comparable<E>> extends AbstractArray<E> {

    @Override
    public boolean insert(E e) {//chỉ Nhân không phải sửa
        if (size == 0){
            root = createNode(e);
            size++;
            return true;
        }
        if (size >= maxSize) return false;
        MyNode<E> tmp = root;
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

}
