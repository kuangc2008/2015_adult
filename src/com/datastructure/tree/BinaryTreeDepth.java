package com.datastructure.tree;

/**
 * Created by kuangcheng on 15-8-6.
 */
public class BinaryTreeDepth {

    public int get(BinaryNode root) {
        if(root == null) {
            throw new IllegalArgumentException("null");
        }
        return getInner(root);
    }

    private int getInner(BinaryNode root) {
        if(root == null) {
            return 0;
        } else {
            return 1 + Math.max( getInner(root.getLeft()) , getInner(root.getRight()));
        }
    }
}
