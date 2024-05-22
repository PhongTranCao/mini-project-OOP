package model;

public abstract class AbstractArray<E> implements Array<E> {
    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }
}
