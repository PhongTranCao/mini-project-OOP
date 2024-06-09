package model;

public interface Array<E> {
    boolean insert(E e);
    boolean delete();
    boolean search(E e);
    boolean sort();
    boolean isEmpty();
    int getSize();
}
