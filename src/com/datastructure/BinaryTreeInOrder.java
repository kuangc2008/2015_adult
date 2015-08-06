package com.datastructure;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by kuangcheng on 15-8-6.
 */
public class BinaryTreeInOrder {

    /**
     * 方法1，地柜
     * @param root
     * @return
     */
    public List<BinaryNode<Integer>> getRecursive(BinaryNode root) {
        validataBinaryNode(root);
        return getInner(root);
    }

    private List<BinaryNode<Integer>> getInner(BinaryNode<Integer> root) {
        List<BinaryNode<Integer>> result = new LinkedList<BinaryNode<Integer>>();
        if(root != null) {
            result.addAll( getInner(root.getLeft()));
            result.add(root);
            result.addAll( getInner( root.getRight()));
        }
        return result;
    }

    private void validataBinaryNode(BinaryNode root) {
        if (root == null) {
            throw new IllegalArgumentException("You can't pass a null BinaryNode.");
        }
    }

    /**
     * 方法二
     */
    public List<BinaryNode<Integer>> getInterative(BinaryNode<Integer> root) {
        validataBinaryNode(root);

        List<BinaryNode<Integer>> result = new LinkedList<BinaryNode<Integer>>();
        Stack<BinaryNode> stack = new Stack<BinaryNode>();
        //define a pointer to track nodes
        BinaryNode current = root;
        while( !stack.empty()  || current != null) {
            if(current != null) {
                stack.push(current);
                current = current.getLeft();    
            } else {
                BinaryNode node = stack.pop();
                result.add(node);
                current = node.getRight();
            }
        }
        return result;
    }
}
