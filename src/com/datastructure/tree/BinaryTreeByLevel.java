package com.datastructure.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by kuangcheng on 15-8-6.
 */
public class BinaryTreeByLevel {

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


    public List<BinaryNode> getWithOutAdditionalDataStructures(BinaryNode root) {
        validateTree(root);

        List<BinaryNode> result = new LinkedList<BinaryNode>();
        int depth = getDepth(root);
        for(int i=0; i<depth; i++) {
            result.addAll(getNodesForLevel(root, i));
        }
        return result;
    }

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
