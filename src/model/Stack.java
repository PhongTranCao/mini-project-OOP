package model;


public class Stack <E extends Comparable<E>> extends AbstractArray<E> {
    @Override
    public boolean insert(E e) {
        if (size == 0){
            root = createNode(e);
            size++;
            return true;
        }
        if (size >= maxSize) return false; // thêm ngoại lệ vào đây
        MyNode<E> cur = createNode(e);
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
}
