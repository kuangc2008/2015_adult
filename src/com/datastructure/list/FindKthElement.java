package com.datastructure.list;

/**
 * Created by chengkuang on 15/8/9.
 */
public class FindKthElement {

    private ReverseLinkedList reverseLinkedList;

    public FindKthElement() {
        this.reverseLinkedList = new ReverseLinkedList();
    }

    public ListNode find(ListNode listNode, int k) {
        validateInput(listNode, k);

        ListNode reversedList = reverseLinkedList.reverseIterative(listNode);
        ListNode result = reversedList;

        while( k > 0) {
            result = result.getNext();
            if(result == null) {
                throw new IndexOutOfBoundsException();
            }
            k--;
        }
        return result;
    }

    private void validateInput(ListNode listNode, int k) {
        if (listNode == null) {
            throw new IllegalArgumentException("You can't pass a null ListNode as argument.");
        }
        if (k < 0) {
            throw new IllegalArgumentException("You can't pass a negative position.");
        }
    }



    public ListNode find2(ListNode listNode, int k) {
        validateInput(listNode, k);

        int size = calculateListSize(listNode);
        if( k > (size -1)) {
            throw new IndexOutOfBoundsException();
        }
        ListNode result = listNode;
        int n = size - 1 - k;
        while(n > 0) {
            result = result.getNext();
            n --;
        }
        return result;
    }

    private int calculateListSize(ListNode listNode) {
        int size = 0;
        while(listNode != null) {
            size++;
            listNode = listNode.getNext();
        }
        return size;
    }


    /**
     * 先k个；  再遍历n，则到n-k
     * @param listNode
     * @param k
     * @return
     */
    public ListNode find3(ListNode listNode, int k) {
        validateInput(listNode, k);

        ListNode pointer2 = listNode;
        for(int i = 0; i <= k; i++) {
            if(pointer2 == null) {
                throw new IndexOutOfBoundsException();
            }
            pointer2 = pointer2.getNext();
        }

        ListNode result = listNode;
        while(pointer2 != null) {
            result  = result.getNext();
            pointer2 = pointer2.getNext();
        }
        return result;
    }
}


