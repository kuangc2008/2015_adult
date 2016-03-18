package com.data_structure_book.heap;

/**
 * Created by kuangcheng01 on 2016/3/17.
 */
public class BinaryHeap<T extends Comparable<? super T> > {

    public BinaryHeap() {

    }

    public BinaryHeap(int capacity) {

    }

    public BinaryHeap(T[] items) {
        currentSize = items.length;
        array = (T[]) new Comparable[ (currentSize + 2) * 11/10 ];

        int i = 1;
        for(T item : items) {
            array[i] = item;
        }
        buildHeap();
    }

    // 插入关键在于找位置
    public void insert(T t) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }

        int hold = ++currentSize;
        for( ; hold > 1 && t.compareTo( array[hold/2]) < 0 ; hold /= 2) {
            array[hold] = array[hold/2];
        }
        array[hold] = t;
    }

    public T findMin() {
        return array[1];
    }

    public T deleteMin() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        T t = findMin();
        array[1] = array[currentSize --];
        percolateDown(1);
        return t;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public void makeEmpty() {

    }

    private static final int DEFAULT_CAPACITY = 10;

    private int currentSize;
    private T[] array;

    private void percolateDown(int hole) {

        int child;
        T tmp = array[hole];

        for (; hole * 2 < currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize &&
                    array[child + 1].compareTo(array[child]) < 0) {
                child++;
            }

            if (array[child].compareTo(tmp) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }

            array[hole] = tmp;
        }



    }

    private void buildHeap() {
        for (int i=currentSize/2 ; i> 0; i--) {
            percolateDown(i);
        }
    }

    private void enlargeArray(int newSize) {

    }








}
