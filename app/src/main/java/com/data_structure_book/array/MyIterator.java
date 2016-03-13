package com.data_structure_book.array;

/**
 * Created by kuangcheng01 on 2016/3/13.
 */
public interface MyIterator<T> {
    boolean hasNext();
    T next();
    void remove();
}
