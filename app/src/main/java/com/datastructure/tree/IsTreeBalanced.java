package com.datastructure.tree;

/**
 * Created by kuangcheng on 15-8-7.
 */
public class IsTreeBalanced {


    public boolean check(BinaryNode root) {
        if(root == null) {
            return true;
        } else {
            int heightDifference = getRootHeight(root.getLeft()) - getRootHeight(root.getRight());
            return Math.abs(heightDifference) <= 1;
        }
    }

    private int getRootHeight(BinaryNode tree) {
        if(tree == null) {
            return 0;
        } else {
            return 1 + Math.max( getRootHeight(tree.getLeft()), getRootHeight(tree.getRight()));
        }
    }
}
