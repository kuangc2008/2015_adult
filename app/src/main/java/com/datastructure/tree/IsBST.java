package com.datastructure.tree;

import java.util.List;

/**
 * 如果快速判定一个树是，二叉搜索树；
 * 二叉树
 * 左小右大
 */
public class IsBST {


    public boolean checkRecursive(BinaryNode<Integer> root) {
        validateInput(root);
        return checkRecursiveInner(root);
    }

    private boolean checkRecursiveInner(BinaryNode<Integer> root) {
        if(root == null) {
            return true;
        } else {
            return (root.getLeft() == null || root.getData() >= root.getLeft().getData())
                    && (root.getRight() == null || root.getData() <= root.getRight().getData())
                    && checkRecursiveInner(root.getLeft())
                    && checkRecursiveInner(root.getRight());
        }
    }

    private void validateInput(BinaryNode<Integer> root) {
        if (root == null) {
            throw new IllegalArgumentException("You can't pass null BinaryNode elements as parameter.");
        }
    }


    private BinaryTreeInOrder binaryTreeInOrder = new BinaryTreeInOrder();
    public boolean checkIterative(BinaryNode<Integer> root) {
        validateInput(root);
        List<BinaryNode<Integer>> nodesInrder = binaryTreeInOrder.getRecursive(root);
        return isListOrderd(nodesInrder);
    }

    private boolean isListOrderd(List<BinaryNode<Integer>> nodes) {
        if(nodes.size() == 1) {
            return true;
        }

        for(int i=0; i<nodes.size() - 1; i++) {
            if(nodes.get(i).getData() > nodes.get(i + 1).getData()) {
                return false;
            }
        }
        return true;
    }

}
