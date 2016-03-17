package com.data_structure_book.hash;

import java.util.List;

/**
 * Created by kuangcheng01 on 2016/3/15.
 */
public class SeparateChainingHashTable<T> {

    public SeparateChainingHashTable() {

    }

    public SeparateChainingHashTable(int size) {

    }

    public void insert(T t) {

    }

    public void remove(T t) {

    }

    public boolean contain(T t) {
        return false;
    }

    public void makeEmpty() {

    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<T>[] theLists;
    private int currentSize;

    private void rehash() {


    }


    private int myhash(T t) {

        int hashVal = t.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
    }

//    private static final int nextPrime(int n) {
//
//    }
//
//    private static boolean isPrime(int n) {
//
//    }



}
