package com.datastructure.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kuangcheng on 15-8-6.
 */
public class LowestCommonAncestor {

    public BinaryNode getRecursive(BinaryNode root, BinaryNode n1, BinaryNode n2) {
        validateInput(root, n1, n2);
        return getRecursiveInner(root, n1, n2);
    }

    private BinaryNode getRecursiveInner(BinaryNode root, BinaryNode n1, BinaryNode n2) {
        if(root == null) {
            return null;
        } else {
            if(root == n1 || root == n2) {
                return root;
            }

            BinaryNode leftBrash = getRecursiveInner(root.getLeft(), n1, n2);
            BinaryNode rightBranch = getRecursiveInner(root.getRight(), n1, n2);
            if(leftBrash != null && rightBranch != null) {
                return root;
            }
            return leftBrash != null ? leftBrash : rightBranch;
        }

    }

    private void validateInput(BinaryNode root, BinaryNode n1, BinaryNode n2) {
        if (root == null || n1 == null || n2 == null) {
            throw new IllegalArgumentException("You can't pass null elements as input.");
        }
    }



    public BinaryNode getIterative(BinaryNode root, BinaryNode n1, BinaryNode n2) {
        validateInput(root, n1, n2);

        List<BinaryNode> pathToA = getPathTo(root, n1);
        List<BinaryNode> pathToB = getPathTo(root, n2);

        BinaryNode result = null;
        int size = Math.min(pathToA.size(), pathToB.size());
        for(int i=0; i<size; i++) {
            if(pathToA.get(i) != pathToB.get(i)) {
                result = pathToA.get(i-1);
            }
        }
        return result;
    }


    private List<BinaryNode> getPathTo(BinaryNode root, BinaryNode target) {
        List<BinaryNode> path = new LinkedList<BinaryNode>();
        if(root == target) {
            path.add(target);
        } else {
            if(root.getLeft() == null && root.getRight() == null) {
                return null;
            }

            if(root.getLeft() != null) {
                List<BinaryNode> pathTo = getPathTo( root.getLeft(), target);
                if(pathTo != null) {
                    path.add(root);
                    path.addAll( getPathTo( root.getLeft(), target));
                }
            }

            if(root.getRight() != null) {
                List<BinaryNode> pathTo = getPathTo(root.getRight(), target);
                if(pathTo != null) {
                    path.add(root);
                    path.addAll(pathTo);
                }
            }
        }

        return path;

    }


}
