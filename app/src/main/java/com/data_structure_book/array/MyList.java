package com.data_structure_book.array;

/**
 * Created by kuangcheng01 on 2016/3/13.
 */
public interface MyList<T> extends MyCollection<T> {

    T get(int idx);
    T set(int idx, T newData);
    void add(int idx, T data);
    T remove(int idx);
}
