package com.data_structure_book2;


import android.util.Log;

import com.data_structure_book.heap.BinaryHeap;

import org.junit.Test;

import java.util.Arrays;

public class Main {

    @Test
    public void test1() {
        Integer[][]  arrays = new Integer[4][];
        arrays[0] = new Integer[] {1, 2, 8, 9};
        arrays[1] = new Integer[] {2, 4, 9, 12};
        arrays[2] = new Integer[] {4, 7, 10, 13};
        arrays[3] = new Integer[] {6, 8, 11, 15};

        int[] pos = findElementInArrays(arrays, 7);
        System.out.println("pos->" + (pos == null ? -1 : pos[0] + " " + pos[1]));
    }

    /**
     * 1 从一个二维数组中找到某书
     */
    public static <T extends Comparable<? super T>> int[] findElementInArrays(T[][] arrays, T element) {
        if (arrays == null) {
            throw new IllegalArgumentException("array is null");
        }

        int rows =arrays.length;
        int cols = arrays[0].length;

        int[]  resultPos = null;

        for(int col = cols -1, row = 0;  row < rows &&  col >= 0; ) {
            int compareResult = arrays[row][col].compareTo(element);
            if ( compareResult > 0) {
                col--;
            } else if (compareResult < 0) {
                row++;
            } else {
                resultPos = new int[2];
                resultPos[0] = row;
                resultPos[1] = col;
                break;
            }
        }

        return resultPos;
    }

    @Test
    public void test2() {
        String s = "abcdefefes";
        String s2 = "abcdefefes";
        System.out.println(s == s2);   // true
    }


    @Test
    public void test3() {
        String s = replaceBlank("I am sturent");
        System.out.println("s is ->" + s);
    }

    /**
     * 将字符串的空格替换成%2s
     */
    public static String replaceBlank(String originStr) {
        StringBuilder sb = new StringBuilder();
        int len = originStr.length();

        for(int pos = 0; pos < len; pos++) {
            char currentChar = originStr.charAt(pos);
            if (currentChar == ' ') {
                sb.append("%20");
            } else {
                sb.append(currentChar);
            }
        }
        return sb.toString();
    }


    @Test
    public void test4() {
        int[] array1 = new int[100];
        int[] tmpArrya = {5, 7, 8, 10, 20, 30, 60, 80};
        for(int i =0 ; i< tmpArrya.length; i++) {
            array1[i] = tmpArrya[i];
        }
        int size = tmpArrya.length;

        int[] array2 = new int[] {1, 3, 5, 7, 76, 90};

        System.out.println(Arrays.toString(array1));
        size = mergeSortedArray(array1, size, array2);
        System.out.println(Arrays.toString(array1));
    }

    /**
     * 2 两个有序数组的合并，将第二个数组合并到第一个数组中
     */
    public static int mergeSortedArray(int[] array1, int size,  int[] array2) {
        int currentPos = size + array2.length - 1;
        int i = size - 1;
        int j = array2.length -1 ;
        while( i >= 0 && j >= 0 ) {
            if (array1[i] < array2[j]) {
                array1[currentPos] = array2[j];
                j --;
                currentPos--;
            } else {
                array1[currentPos] = array1[i];
                i --;
                currentPos --;
            }
        }

        if (i <= 0) {
            for(; j >= 0 ; j--) {
                array1[currentPos] = array2[j];
                currentPos--;
            }
        }
        return 0;
    }

    @Test
    public void test5() {
        SingleNode s1 = new SingleNode(10, null);
        SingleNode s2 = new SingleNode(100, null);
        SingleNode s3 = new SingleNode(80, null);
        SingleNode s4 = new SingleNode(90, null);
        SingleNode s5 = new SingleNode(30, null);
        SingleNode s6 = new SingleNode(1, null);

        s1.nextNode = s2;
        s2.nextNode = s3;
        s3.nextNode = s4;
        s4.nextNode = s5;
        s5.nextNode = s6;

        printListReverse2(s1);
    }

    public static class SingleNode<T> {
        public T data;
        public SingleNode<T> nextNode;
        public SingleNode (T data, SingleNode nextNode) {
            this.data = data;
            this.nextNode = nextNode;
        }
    }

    /**
     * 3 从后往前打印单向链表
     */
    public static void printListReverse(SingleNode head) {

        SingleNode currentNode = head;
        head = null;
        while(currentNode != null) {
            SingleNode nextNode = currentNode.nextNode;
            currentNode.nextNode = head;
            head = currentNode;
            currentNode = nextNode;
        }

        while( head != null) {
            System.out.println(head.data);
            head = head.nextNode;
        }
    }

    /**
     * 递归方式， 因为由栈就可以想到递归
     */
    public static void printListReverse2(SingleNode head) {
        if (head == null) {
            return;
        }
        printListReverse2(head.nextNode);
        System.out.println(head.data);
    }


    @Test
    public void test6() {

        int[] preList = new int[]{1, 2, 4, 7, 3, 5, 6, 8};
        int[] middleList = new int[] {4, 7, 2, 1, 5, 3, 8, 6};
        BinaryTree tree = buildTree(preList, middleList);


    }

    public static class BinaryTree {
        int data;
        BinaryTree left;
        BinaryTree right;
    }

    public static BinaryTree buildTree(int[] reList, int[] middleList) {


        return null;
    }





}
