package com.data_structure_book.hash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by kuangcheng01 on 2016/3/15.
 *
 *
 * 分离链接发
 */
public class SeparateChainingHashTable<T> {

    public SeparateChainingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainingHashTable(int size) {

        theLists = new LinkedList[ nextPrime(size)] ;
        for (int i=0 ;i<theLists.length; i++) {
            theLists[i] = new LinkedList<T>();
        }
    }

    public void insert(T t) {
        List<T> whichList = theLists[myhash(t)];
        if (!whichList.contains(t)) {
            whichList.add(t);

            //refash
            if(++currentSize > theLists.length) {
                rehash();
            }
        }
    }

    public void remove(T t) {
        List<T> whichList = theLists[myhash(t)];
        if (whichList.contains(t)) {
            whichList.remove(t);
            currentSize--;
        }
    }

    public boolean contain(T t) {
        List<T> whichList = theLists[myhash(t)];
        return whichList.contains(t);
    }

    public void makeEmpty() {
        for(int i=0; i < theLists.length; i++) {
            theLists[i].clear();
        }
        currentSize = 0;
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private List<T>[] theLists;
    private int currentSize;

    private void rehash() {

        List<T> [] oldLists = theLists;

        theLists = new List[ nextPrime( 2 * theLists.length + 1)] ;
        for(int j = 0; j < theLists.length; j++) {
            theLists[j] = new LinkedList<T>();
        }
        currentSize = 0;
        for(int i = 0; i< oldLists.length; i++) {
            for(T t : oldLists[i]) {
                insert(t);
            }
        }
    }


    private int myhash(T t) {
        int hashVal = t.hashCode();
        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
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
