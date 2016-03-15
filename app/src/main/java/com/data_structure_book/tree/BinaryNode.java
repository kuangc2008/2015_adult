package com.data_structure_book.tree;

/**
 * Created by kuangcheng01 on 2016/3/14.
 */
public class BinaryNode<T> {
    public T element;
    public BinaryNode<T> left;
    public BinaryNode<T> right;

    BinaryNode(T element) {
        this(element, null, null);
    }

    BinaryNode(T element, BinaryNode<T> lt, BinaryNode<T> rt) {
        this.element = element;
        left = lt;
        right = rt;
    }
}
