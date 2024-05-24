package model;

import java.util.ArrayList;
import java.util.Collections;

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

    @Override
    public boolean search(E e) {
        MyNode<E> tmp = root;
        while(tmp != null){
            if (tmp.element.equals(e)) return true;
            tmp = tmp.next;
        }
        return false; // ngoại lệ khi stack rỗng
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }
}
