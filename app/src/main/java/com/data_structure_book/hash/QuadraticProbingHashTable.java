package com.data_structure_book.hash;

import java.util.zip.CRC32;

/**
 * Created by kuangcheng01 on 2016/3/17.
 *
 * 平方散列发
 */
public class QuadraticProbingHashTable<T> {

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    public void makeEmpty() {
        currentSize = 0;
        for( int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    public boolean contains(T t) {
        int currentPos = findPos(t);
        return isActive( currentPos );
    }

    public void insert(T t) {

        int currentPos = findPos(t);
        if (isActive(currentPos)) {
            return;
        }

        array[currentPos] = new HashEntry<T>(t, true);

        if ( ++ currentPos > array.length/2) {
            rebash();
        }


    }

    public void remove(T t) {
        int ccurrentPos = findPos(t);
        if (isActive(currentSize)) {
            array[ccurrentPos].isActive = false;
        }
    }


    private static class HashEntry<T> {
        public T element;
        public boolean isActive;

        public HashEntry(T e) {
            this(e, true);
        }

        public HashEntry(T e, boolean i) {
            element = e;
            isActive = i;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 11;

    private HashEntry<T> [] array;
    private int currentSize;

    private void allocateArray(int arraySize) {
        array = new HashEntry[arraySize];
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }


    private int findPos(T t) {
        int offset = 1;
        int currentPos = myhash(t);

        while( array[currentPos] != null &&
                ! array[currentPos].element.equals(t)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }

        return currentPos;
    }

    private void rebash() {

    }

    private int myhash(T t) {
        int hashVal = t.hashCode();
        hashVal %= array.length;
        if (hashVal < 0) {
            hashVal += array.length;
        }
        return hashVal;
    }

    private static final int nextPrime(int n) {

        return 0;
    }

    private static boolean isPrime(int n) {
        return  false;
    }




}
