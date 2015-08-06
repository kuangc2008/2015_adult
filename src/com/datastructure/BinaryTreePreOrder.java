package com.datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by kuangcheng on 15-8-6.
 */
public class BinaryTreePreOrder {


    public List<BinaryNode> getRecursive(BinaryNode root) {
        validataBinaryNode(root);
        return getRecursiveInner(root);
    }

    public List<BinaryNode> getRecursiveInner(BinaryNode root) {
        List<BinaryNode> result = new LinkedList<BinaryNode>();
        if(root != null) {
            result.add(root);
            getRecursive(root.getLeft());
            getRecursive(root.getRight());
        }
        return result;
    }

    private void validataBinaryNode(BinaryNode root) {
        if (root == null) {
            throw new IllegalArgumentException("You can't pass a null BinaryNode.");
        }
    }


    public List<BinaryNode> getIterative(BinaryNode root) {
        validataBinaryNode(root);

        List<BinaryNode> result = new LinkedList<BinaryNode>();
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryNode node = stack.pop();
            result.add(node);
            if(node.hasRight()) {
                stack.add(node.getRight());
            }
            if(node.hasLeft()) {
                stack.add(node.getLeft());
            }
        }
        return result;
    }

}
