package com.data_structure_book.array;

/**
 * Created by kuangcheng01 on 2016/3/13.
 */
public class MyNode<T> {

    public T data;
    public MyNode<T> prev;
    public MyNode<T> next;

    public MyNode(T data, MyNode<T> p, MyNode<T> n) {
        this.data = data;
        prev = p;
        next = n;
    }


}
