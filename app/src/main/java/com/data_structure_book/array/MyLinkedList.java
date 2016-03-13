package com.data_structure_book.array;


import org.w3c.dom.Node;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Created by kuangcheng01 on 2016/3/13.
 */
public class MyLinkedList<T> implements MyList<T> {
    private int theSize;
    private int modeCount = 0;
    private MyNode<T> befreMarker;
    private MyNode<T> endMarkder;


    public MyLinkedList() {
        clear();
    }

    @Override
    public T get(int idx) {
        return getNode(idx).data;
    }

    @Override
    public T set(int idx, T newData) {
        MyNode<T> p = getNode(idx);
        T oldData = p.data;
        p.data = newData;
        return oldData;
    }

    @Override
    public void add(int idx, T data) {
        addBefore(getNode(idx), data);
    }

    @Override
    public T remove(int idx) {
        return remove(getNode(idx));
    }

    @Override
    public int size() {
        return theSize;
    }

    @Override
    public boolean isEmpty() {
        return size() > 0;
    }

    @Override
    public void clear() {
        befreMarker = new MyNode<T>(null, null, null);
        endMarkder = new MyNode<T>(null, befreMarker, null);
        befreMarker.next = endMarkder;

        theSize = 0;
        modeCount++;
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

//    @Override
//    public boolean remove(T data) {
//        return false;
//    }

    @Override
    public MyIterator<T> iterator() {
        return new LinkedListIterator();
    }

    private void addBefore(MyNode<T> p, T data) {
        MyNode<T> newNode = new MyNode<T>(data, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;

        theSize++;
        modeCount++;
    }

    private T remove(MyNode<T> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;

        theSize--;
        modeCount++;

        return p.data;
    }

    private MyNode<T> getNode(int idx) {
        if (idx < 0 || idx > size()) {
            throw new IndexOutOfBoundsException();
        }

        MyNode<T> p;
        if (idx < size()/2) {
            p = befreMarker.next;
            for( int i=0; i<idx; i++) {
                p = p.next;
            }
        } else {
            p = endMarkder;
            for (int i=size(); i> idx; i--) {
                p = p.prev;
            }
        }

        return p;
    }

    private class LinkedListIterator implements MyIterator<T> {
        private MyNode<T> current = befreMarker.next;
        private int expectedModeCount = modeCount;
        private boolean okToRemove = false;

        @Override
        public boolean hasNext() {
            return current != endMarkder;
        }

        @Override
        public T next() {
            if (modeCount != expectedModeCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (modeCount != expectedModeCount) {
                throw new ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw  new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            okToRemove = false;
            expectedModeCount++;
        }
    }

}
