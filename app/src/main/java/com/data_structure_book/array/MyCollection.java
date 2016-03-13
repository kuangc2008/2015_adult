package com.data_structure_book.array;

/**
 * Created by kuangcheng01 on 2016/3/13.
 */
public interface MyCollection<T> {

    int size();
    boolean isEmpty();
    void clear();
    boolean contains(T data);
    boolean add (T data);
  //  boolean remove(T data);
    MyIterator<T> iterator();
}
