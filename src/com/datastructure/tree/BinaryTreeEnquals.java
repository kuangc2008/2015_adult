package com.datastructure.tree;

import java.util.Stack;

/**
 * Created by kuangcheng on 15-8-6.
 */
public class BinaryTreeEnquals {

    /**
     * 方法一
     * @param tree1
     * @param tree2
     * @return
     */
    public boolean areEquanlsRecursive(BinaryNode tree1, BinaryNode tree2) {
        validataInput(tree1, tree2);
        return areEqualsInner(tree1, tree2);
    }

    private boolean areEqualsInner(BinaryNode tree1, BinaryNode tree2) {
        if(tree1 == null && tree2 != null || tree1!=null && tree2 == null) {
            return false;
        } else {
            return tree1.equals(tree2)
                    && areEqualsInner(tree1.getLeft(), tree2.getRight())
                    && areEqualsInner(tree1.getRight(), tree2.getRight());
        }
    }


    private void validataInput(BinaryNode tree1, BinaryNode tree2) {
        if(tree1 == null && tree2 == null) {
            throw new IllegalArgumentException("two null trees");
        }
    }


    /**
     * 方法2
     */
    public boolean areEqualsIterative(BinaryNode tree1, BinaryNode tree2) {
        validataInput(tree1, tree2);
        Stack<BinaryNode> stack1 = new Stack<BinaryNode>();
        Stack<BinaryNode> stack2 = new Stack<BinaryNode>();

        stack1.push(tree1);
        stack2.push(tree2);
        boolean equals = true;

        while(!stack1.empty()) {
            BinaryNode node1 = stack1.pop();
            BinaryNode node2 = stack2.pop();
            if( !node1.equals(node2)) {
                equals = false;
                break;
            }
            addNodeToStack(stack1, node1.getLeft());
            addNodeToStack(stack1, node1.getRight());
            addNodeToStack(stack2, node2.getLeft());
            addNodeToStack(stack2, node2.getRight());
        }

        return equals;
    }

    private void addNodeToStack(Stack stack1, BinaryNode node) {
        if(node != null) {
            stack1.add(node);
        }
    }
}
