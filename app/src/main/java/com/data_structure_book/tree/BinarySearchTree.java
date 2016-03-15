package com.data_structure_book.tree;

/**
 * Created by kuangcheng01 on 2016/3/14.
 */
public class BinarySearchTree<T extends Comparable<? super T>>  {

    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T e) {
        return contains(e, root);
    }

    public T findMin() {
        if(isEmpty()) {
            throw new IllegalArgumentException();
        }
        return findMin(root).element;
    }

    public T findMax() {
        if(isEmpty()) {
            throw new IllegalArgumentException();
        }
        return findMax(root).element;
    }

    public void insert(T e) {
        root = insert(e, root);
    }

    public void remove(T e) {
        root = remove(e, root);
    }

    public void printTree() {

    }

    private boolean contains(T e, BinaryNode<T> t) {
        if ( t == null) {
            return false;
        }

        int compareResult = e.compareTo(t.element);
        if (compareResult < 0) {
            return contains(e, t.left);
        } else if (compareResult > 0) {
            return contains(e, t.right);
        } else {
            return true;
        }
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if ( t == null) {
            return null;
        }

        if (t.left != null) {
            return findMin(t.left);
        } else {
            return t;
        }
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t == null) {
            return null;
        }

        while(t.right != null) {
            t = t.right;
        }

        return t;
    }

    private BinaryNode<T> insert(T e, BinaryNode<T> t) {
        if ( t == null) {
            return new BinaryNode<T>(e, null, null);
        }

        int compareResult = e.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(e, t.left);
        } else if (compareResult > 0) {
            t.right = insert(e, t.right);
        } else {
            // ignore
        }
        return t;
    }

    private BinaryNode<T> remove(T e, BinaryNode<T> t) {
        if ( t == null) {
            return null;
        }

        int compareResult = e.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(e, t.left);
        } else if (compareResult > 0) {
            t.right = remove(e, t.right);
        } else {
            if( t.left == null || t.right == null) {
                t =  t.left != null ? t.left : t.right;
            } else {
                t.element = findMin(t.right).element;
                t.right = remove(t.element, t.right);
            }
        }
        return t;
    }

    public void printTree(BinaryNode<T> t) {

    }




}
