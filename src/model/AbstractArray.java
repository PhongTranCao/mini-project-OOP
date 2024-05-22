package model;

import java.util.ArrayList;

public abstract class AbstractArray<E> implements Array<E> {
    @Override
    /* Return true if the tree is empty */
    public boolean isEmpty() {
        return getSize() == 0;
    }
}
