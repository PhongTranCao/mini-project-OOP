package model;

public class SList<E extends Comparable<E>> extends AbstractArray<E>{
    public boolean insert(E e) {
        if (size == 0) {
            root = createNode(e);
            size++;
            return true;
        }
        MyNode<E> tmp = root;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = createNode(e);
        size++;
        return true;
    }

    @Override
    public boolean delete() {
        return false;
    }

    public boolean insert(E e, E value) {
        if (size == 0 || !search(e)) return false;
        MyNode<E> tmp = root;
        while (tmp.getElement() != value) tmp = tmp.next;
        MyNode<E> tmpNext = tmp.next;
        tmp.next = createNode(e);
        tmp.next.next = tmpNext;
        size++;
        return true;
    }

    public boolean delete(E e) {
        if (size == 0) return false;
        MyNode<E> tmp = root, prev = null;
        while (tmp.getElement() != e) {
            prev = tmp;
            tmp = tmp.next;
        }
        assert prev != null;
        prev.next = tmp.next;
        size--;
        return true;
    }
    
}

