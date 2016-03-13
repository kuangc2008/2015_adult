package com.datastructure.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Given a binary tree, can you write a method to return a list of nodes by level?
 * And without any additional data structure?
 */
public class BinaryTreeByLevel {

    /**
     * 按照顺序，把树的节点，加入到一个queue中（动态的）
     * root  1-left  1-right  1-2-left 2-right，不停的删除掉前面节点，增加后面节点
     */
    public List<BinaryNode> getUsingQueue(BinaryNode root) {
        validateTree(root);

        List<BinaryNode> result = new LinkedList<BinaryNode>();
        Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryNode binaryNode = queue.remove();
            result.add(binaryNode);
            if(binaryNode.getLeft() != null) {
                queue.add(binaryNode.getLeft());
            }
            if(binaryNode.getRight() != null) {
                queue.add(binaryNode.getRight());
            }
        }
        return result;
    }


    private void validateTree(BinaryNode root) {
        if (root == null) {
            throw new IllegalArgumentException("You can't pass a null BinaryNode.");
        }
    }


    /**
     * 获取到高度
     * 按高度进行循环。  每次都对树做递归操作
     * @param root
     * @return
     */
    public List<BinaryNode> getWithOutAdditionalDataStructures(BinaryNode root) {
        validateTree(root);

        List<BinaryNode> result = new LinkedList<BinaryNode>();
        int depth = getDepth(root);
        for(int i=0; i<depth; i++) {
            result.addAll(getNodesForLevel(root, i));
        }
        return result;
    }

    /**
     * 获取深度
     * @param root
     * @return
     */
    private int getDepth(BinaryNode root) {
        if(root == null) {
            return 0;
        } else {
            return 1 + Math.max(getDepth(root.getLeft()), getDepth(root.getRight()));
        }
    }

    private List<? extends BinaryNode> getNodesForLevel(BinaryNode root, int level) {
        if(root == null) {
            return Collections.EMPTY_LIST;
        } else {
            List<BinaryNode> result = new LinkedList<BinaryNode>();
            if(level == 1) {
                result.add(root);
            } else {
                result.addAll( getNodesForLevel(root.getLeft(), level - 1));
                result.addAll( getNodesForLevel( root.getRight(), level - 1));
            }
            return result;
        }
    }

}
