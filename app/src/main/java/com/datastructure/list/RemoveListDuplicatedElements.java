package com.datastructure.list;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chengkuang on 15/8/9.
 */
public class RemoveListDuplicatedElements {


    /**
     * 使用set
     * @param head
     */
    public void remove(ListNode<Integer> head) {
        validataInput(head);

        Set<Integer> elements = new HashSet<Integer>();
        ListNode<Integer> currentNode = head;
        ListNode<Integer> prevNode = null;

        //若包含，则跳过当前节点，将上个界面的next设置为下个节点
        while(currentNode != null) {
            if(elements.contains(currentNode.getData())) {
                prevNode.setNext(currentNode.getNext());
            } else {
                elements.add(currentNode.getData());
            }

            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }
    }

    private void validataInput(ListNode head) {
        if(head == null) {
            throw new IllegalArgumentException("null");
        }
    }

    /**
     * 一个个遍历
     * @param head
     */
    public void remove2(ListNode<Integer> head) {
        validataInput(head);

        ListNode<Integer> node = head;
        while(node != null) {
            removeNextNOdeWiathValue(node);
            node = node.getNext();
        }
    }

    private void removeNextNOdeWiathValue(ListNode<Integer> srcNode) {
        ListNode<Integer> preNode = srcNode;
        ListNode<Integer> currentNode = srcNode.getNext();

        while(currentNode != null) {
            if(currentNode.getData().equals(srcNode.getData())) {
                preNode.setNext(currentNode.getNext());
            }
            preNode = currentNode;
            currentNode = currentNode.getNext();
        }
    }

}
