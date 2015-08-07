package com.datastructure.tree;

import java.util.List;

/**
 * Created by kuangcheng on 15-8-7.
 */
public class AvlTreeMedian {

    BinaryTreeInOrder binaryTreeInOrder;

    public AvlTreeMedian() {
        this.binaryTreeInOrder = new BinaryTreeInOrder();
    }

    public double find(BinaryNode<Integer> root) {
        if (root == null) {
            throw new IllegalArgumentException("null");
        }

        List<BinaryNode<Integer>> sortedElements = binaryTreeInOrder.getInterative(root);
        double median = 0;
        if (sortedElements.size() % 2 == 0) {
            median = (sortedElements.get(sortedElements.size() / 2).getData() + sortedElements.get(
                    sortedElements.size() / 2 - 1).getData()) / 2;
        } else {
            median = sortedElements.get(sortedElements.size() / 2).getData();

        }
        return median;
    }
}
