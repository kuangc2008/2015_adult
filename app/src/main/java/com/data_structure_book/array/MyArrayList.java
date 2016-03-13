package com.data_structure_book.array;

import com.android.volley.Request;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by kuangcheng01 on 2016/3/13.
 */
public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int theSize;
    private T[] theItems;

    public MyArrayList() {
        clear();
    }

    public void clear() {
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    @Override
    public boolean contains(T data) {
        return false;
    }

    @Override
    public boolean add(T data) {
        add(size(), data);
        return true;
    }

    @Override
    public boolean remove(T data) {
        return false;
    }


    public void trimToSize() {
        ensureCapacity(size());
    }

    private void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }

        T[] old  = theItems;
        theItems = (T[]) new Object[newCapacity];
        for(int i=0; i<size(); i++) {
            theItems[i]  = old[i];
        }
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public T get(int idx) {
        if (idx <  0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return theItems[idx];
    }

    @Override
    public T set(int idx, T newData) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T old = theItems[idx];
        theItems[idx] = newData;
        return old;
    }

    @Override
    public void add(int idx, T data) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }

        for(int i = theSize; i> idx; i--) {
            theItems[i] = theItems[i - 1];
        }

        theItems[idx]  = data;

        theSize++;
    }

    //先把该节点拿出来，再从当前到后面，非赋值，最后将index-1
    @Override
    public T remove(int idx) {
        if (idx < 0 || idx >= size()) {
            throw new ArrayIndexOutOfBoundsException();
        }

        T removed = theItems[idx];

        for(int i=idx + 1; i < theSize; i++) {
            theItems[i - 1] = theItems[i];
        }

        theSize--;

        return removed;
    }


    @Override
    public MyIterator<T> iterator() {
        return new MyArrayListIterator();
    }

    public class MyArrayListIterator implements MyIterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }




}
