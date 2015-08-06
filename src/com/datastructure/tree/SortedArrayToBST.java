package com.datastructure.tree;

/**
 * Created by kuangcheng on 15-8-6.
 */
public class SortedArrayToBST {


    /**
     * 中间的迭代
     * @param sortedArray
     * @return
     */
    public BinaryNode<Integer> transfrom(Integer[] sortedArray) {
        if(sortedArray == null || sortedArray.length == 0) {
            throw new IllegalArgumentException("Null");
        }
        return transfromToBST(sortedArray, 0, sortedArray.length - 1);
    }

    private BinaryNode<Integer> transfromToBST(Integer[] sortedArray, int bottom, int top) {
        int center = (top + bottom) /2;
        if(sortedArray.length == 1) {
            return new BinaryNode<Integer>(sortedArray[0]);
        } else if(bottom > top) {
            return null;
        }
        BinaryNode node = new BinaryNode<Integer>(sortedArray[center]);
        node.setLeft( transfromToBST(sortedArray, bottom, center - 1) );
        node.setRight( transfromToBST(sortedArray, center + 1, top));
        return node;
    }
}
