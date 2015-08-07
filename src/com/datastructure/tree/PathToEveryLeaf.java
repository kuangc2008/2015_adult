package com.datastructure.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by kuangcheng on 15-8-7.
 */
public class PathToEveryLeaf {

    public List<List<BinaryNode>> calcucalte(BinaryNode<Integer> root) {
        if(root == null) {
            throw new IllegalArgumentException("null");
        }

        return calcucatePathToLeafs(root, new LinkedList<BinaryNode>);
    }

    private List<List<BinaryNode>> calcucatePathToLeafs(BinaryNode<Integer> root, LinkedList<BinaryNode> path) {
        List<List<BinaryNode>> paths = new LinkedList<List<BinaryNode>>();

        if(root == null) {
            return paths;
        } else if(root.getLeft() == null && root.getRight() == null) {
            path.add(root);
            paths.add(path);
            return paths;
        } else {
            path.add(root);
            paths.addAll(calcucatePathToLeafs(root.getLeft(), new LinkedList<BinaryNode>(path)));
            paths.addAll(calcucatePathToLeafs(root.getRight(), new LinkedList<BinaryNode>(path)));
            return paths;
        }
    }
}
