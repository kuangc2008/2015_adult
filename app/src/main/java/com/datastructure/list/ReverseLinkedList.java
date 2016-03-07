package com.datastructure.list;

import java.util.List;

/**
 * Created by chengkuang on 15/8/9.
 */
public class ReverseLinkedList {

    public ListNode reverseIterative(ListNode list) {
        validateInput(list);

        if(list.getNext() == null) {
            return list;
        }

        ListNode head = null;
        ListNode current = list;
        while(current != null) {
            ListNode save = current;  //1
            current = current.getNext(); //2
            save.setNext(head);
            head = save;
            //1. head = save [1, null]  current = 2
            //2  head = save [2, 1, null]  current=3
            //3  head = save [3, 2, 1, null]  current = 4
        }
        return head;
    }

    private void validateInput(ListNode list) {
        if(list == null) {
            throw new IllegalArgumentException("null");
        }
    }


    /**
     * 难懂
     * @param list
     * @return
     */
    public ListNode<Integer> reverseRecursive(ListNode list) {
        validateInput(list);
        return reverseRecursiveInner(list);
    }

    public ListNode<Integer>  reverseRecursiveInner(ListNode head) {
        if(head == null || head.getNext() == null) {
            return head;
        } else {
            ListNode reverseList = reverseRecursiveInner(head.getNext());
            head.getNext().setNext(head);
            head.setNext(null);
            // 1 2 3 4 5
            return reverseList;
        }

    }
}
