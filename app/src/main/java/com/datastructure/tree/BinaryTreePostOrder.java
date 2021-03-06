package com.datastructure.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, can you write a method to getRecursive a List<BinaryNode> using a post
 * order traversal?
 */
public class BinaryTreePostOrder {

    public List<BinaryNode> getRecursive(BinaryNode root) {
        validateTree(root);
        return getInner(root);
    }

    private List<BinaryNode> getInner(BinaryNode root) {
        List<BinaryNode> result = new LinkedList<BinaryNode>();
        if(root == null) {
            return result;
        }
        result.addAll( getInner(root.getLeft()));
        result.addAll( getInner(root.getRight()));
        result.add(root);
        return result;
    }


    private void validateTree(BinaryNode root) {
        if (root == null) {
            throw new IllegalArgumentException("You can't pass a null BinaryNode.");
        }
    }


    /**
     * @param root
     * @return
     */
    public List<BinaryNode> getIterative(BinaryNode root) {
        validateTree(root);
        List<BinaryNode> result = new LinkedList<BinaryNode>();
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        stack.push(root);

        BinaryNode prev = null;
        while (!stack.isEmpty()) {
            BinaryNode current = stack.peek();


            //Go down the tree. check if current node is leaf, if so, process it and pop stack, otherwise,
            //keep going down
            if(prev == null || prev.getLeft() == null || prev.getRight() == null) {
                //prev == null is the situation for the root node
                if(current.getLeft() != null) {
                    stack.push(current.getLeft());
                } else if(current.getRight() != null ){
                    stack.push(current.getRight());
                } else {
                    stack.pop();
                    result.add(current);
                }
            } else  if(current.getLeft() == prev) {
                if(current.getRight() != null) {
                    stack.push(current.getRight());
                } else {
                    stack.pop();
                    result.add(current);
                }
            } else if(current.getRight() == prev) {
                stack.pop();
                result.add(current);
            }

            prev = current;
        }
        return result;
    }

}
