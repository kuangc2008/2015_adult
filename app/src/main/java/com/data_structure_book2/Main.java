package com.data_structure_book2;


import android.util.Log;

import com.data_structure_book.heap.BinaryHeap;
import com.example.demo.AppInfo;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

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
        System.out.println("");
    }

    public static class BinaryTree {
        int data;
        BinaryTree left;
        BinaryTree right;
    }

    /**
     * 4 由前序和中序数组，打印出树
     *
     * 中序： 左边还是右边
     * 前序： 第一个界面
     * 递归
     *
     * 1 2 4 7 3 5 6 8
     * 4 7 2 1 5 3 8 6
     *
     */
    public static BinaryTree buildTree(int[] preList, int[] middleList) {
        if (preList == null) {
            throw new IllegalArgumentException();
        }

        BinaryTree root = new BinaryTree();
        buildTree(root, preList, 0, middleList, 0, middleList.length - 1);
        return root;
    }

    public static BinaryTree buildTree(BinaryTree root, int[] preList, int preStart,
                                       int[] middleList, int middleStart, int middleEnd) {
        int size = middleEnd - middleStart;
        if (preList == null || size < 0 || preStart >= preList.length) {
            return null;
        }

        if (root == null) {
            root = new BinaryTree();
        }
        root.data = preList[preStart];

        int middleRootPos = middleStart;
        while( preList[preStart] != middleList[middleRootPos] && middleRootPos <= middleEnd) {
            middleRootPos++;
        }

        root.left =  buildTree(root.left, preList, preStart + 1, middleList, middleStart,   middleRootPos - 1);
        root.right =  buildTree(root.right, preList, preStart + middleRootPos - middleStart + 1 , middleList , middleRootPos + 1,  middleEnd);

        return root;
    }


     @Test
    public void test7() {
         QueeuWithStack<Integer> queue = new QueeuWithStack<Integer>();
         queue.appendTail(10);
         queue.appendTail(100);
         queue.appendTail(80);


         System.out.println("tail1->" + queue.deleteHead());
         System.out.println("tail1->" + queue.deleteHead());
         System.out.println("tail1->" + queue.deleteHead());
         queue.appendTail(50);
         System.out.println("tail1->" + queue.deleteHead());
         System.out.println("tail1->" + queue.deleteHead());

    }


    /**
     * 5 用两个栈来实现队列
     */
    public class QueeuWithStack<T> {
        Stack<T> inStack = new Stack<T>();
        Stack<T> outStack = new Stack<T>();

        public void appendTail(T element) {
            inStack.push(element);
        }

        public T deleteHead() {
            if (outStack.isEmpty()) {
                if (inStack.isEmpty()) {
                    System.out.println("no element");
                    return null;
                } else {
                    while(!inStack.isEmpty()) {
                        outStack.push( inStack.pop() );
                    }
                    return outStack.pop();
                }
            } else {
                return outStack.pop();
            }
        }

    }

    @Test
    public void test8() {
        int[] data = new int[]{1, 100 , 9, 8, 10, 10, 80, 90};
        quickSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void quickSort(int[] datas) {
        if (datas == null) {
            throw new IllegalArgumentException();
        }

        quickSortRec(datas, 0 , datas.length - 1);

    }

    /**
     * 6 快速排序1
     * 小于中间的移左边，大于中间的移右边
     *
     */
    private static void quickSortRec(int[] arr, int low, int high) {


        // pick the pivot
        int middle = low + (high - low)/2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while( i <= j) {

            while( arr[i] < pivot) {
                i ++;
            }
            while (arr[j] > pivot) {
                j --;
            }

            if ( i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j) {
            quickSortRec(arr, low, j);
        }

        if (high > i) {
            quickSortRec(arr, i, high);
        }
    }

    public static void swap(int[] datas, int i, int j) {
        int tmp = datas[i];
        datas[i] = datas[j];
        datas[j] = tmp;
    }

    @Test
    public void test9() {
        int[] data = new int[]{1, 80 ,8,  9, 8, 10, 10, 80, 90};
        sortAge(data);
        System.out.println(Arrays.toString(data));
    }



    /**
     * 7 对年龄排序
     */
    public static void sortAge(int[] ages) {
        if (ages == null || ages.length == 0) {
            return;
        }

        int[] timesOfAge = new int[100]; //1-99
        for(int age : ages) {
            if (age < 0 || age > 99) {
                throw  new IllegalArgumentException();
            }
            timesOfAge[age]++;
        }

        int pos = 0;
        for(int age = 0; age < timesOfAge.length; age++) {
            while (timesOfAge[age] > 0) {  //此处用for循环会更好
                ages[pos++] = age;
                timesOfAge[age]--;
            }
        }
    }

    @Test
    public void test10() {
//        int[] data = new int[]{10, 12 , 20, 3, 4,  6, 9};
        int[] data = new int[]{1, 0 , 1, 1, 1,  1, 1};
        int result = rotateSortedArray(data);
        System.out.println(result);
    }


    /**
     * 8 Rotation of sorted array
     *
     * 最简单就是： 从投到尾遍历即可
     */
    public static int rotateSortedArray(int[] rotatedArray) {
        if (rotatedArray[rotatedArray.length -1 ] > rotatedArray[0]) {
            return rotatedArray[0];
        }
        return  rotateSortedArray(rotatedArray, 0, rotatedArray.length - 1);
    }


    public static int rotateSortedArray(int[] rotatedArray, int start, int end) {
        if (start == end) {
            return rotatedArray[start];
        }
        if (start + 1 == end) {
            return rotatedArray[end];
        }

        int center = (start + end) / 2;
        if (rotatedArray[center] > rotatedArray[0]) {
            return rotateSortedArray(rotatedArray, center + 1, end);
        } else {
            return rotateSortedArray(rotatedArray, start, center );
        }
    }

    public static int rotateSortedArray2(int[] rotatedArray) {
        if (rotatedArray == null || rotatedArray.length == 0) {
            throw new IllegalArgumentException();
        }

        int index1 = 0;
        int index2 = rotatedArray.length - 1;
        int indexMid = index1;

        while (rotatedArray[index1] >= rotatedArray[index2]) {
            if (index2 - index1 == 1) {
                indexMid = index2;
                break;
            }
            indexMid = (index1 + index2) / 2;
            if (rotatedArray[indexMid] >= rotatedArray[index1]) {
                index1 = indexMid;
            } else if (rotatedArray[indexMid] <= rotatedArray[index2]) {
                index2 = indexMid;
            }
        }

        return rotatedArray[indexMid];
    }


    @Test
    public void test11() {
        int result = Fibonacci(10);
        System.out.println(result);
    }


    /**
     * 11 f(n) = f(n-1) + f(n-2)
     *
     */
    public static int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        int a1 = 0;
        int a2 = 1;


        for (int i=1 ; i < n; i++) {
            int tmp = a1 + a2;
            a1 = a2;
            a2 = tmp ;
        }

        return a2;
    }

    @Test
    public void test12() {
        int result = qiwaTaijie(5);
        System.out.println(result);
    }


    /**
     * 青蛙跳台阶
     *
     * 1  -  1
     * 2  -  2
     * 3  -  1 + 1 + 1 = 3
     * 4 -  1 + 1 + 3 = 5
     *
     * f(1) = 1;
     * f(2) = 2
     * f(n) = f(n-1)+f(n-2)
     * 分离：　第一次只跳１级，则后面是f(n-1)
     *  第一次只跳二级，则后面是f(n-2)
     */
    public static int qiwaTaijie(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        if (n == 1) {
            return 1;
        }

        int a1 = 1;
        int a2 = 2;
        for(int i=2; i<n ; i++) {
            int tmp = a1 + a2;
            a1 = a2;
            a2 = tmp;
        }
        return a2;
    }



    @Test
    public void test13() {
        System.out.println(numberOfOne(10));
        System.out.println(numberOfOne(9));
        System.out.println(numberOfOne(8));
        System.out.println(numberOfOne(-1));
        System.out.println(numberOfOne(-2));

        System.out.println(numberOfOne2(10));
        System.out.println(numberOfOne2(9));
        System.out.println(numberOfOne2(8));
        System.out.println(numberOfOne2(-1));
        System.out.println(numberOfOne2(-2));

        System.out.println(numboerOfOne3(10));
        System.out.println(numboerOfOne3(9));
        System.out.println(numboerOfOne3(8));
        System.out.println(numboerOfOne3(-1));
        System.out.println(numboerOfOne3(-2));
    }

    /**
     * 判断一个整数中1个个数
     *
     * 方法1 : 除法
     * 方法2 ：&1左移 （无）
     * 方法3 ：无负数的右移
     * 方法4 ：减去1
     *
     */
    public static int numberOfOne(int n) {
        int m = n >= 0 ? n : -n - 1;
        int oneNumber = 0;
        while (m != 0) {
            if (m % 2 == 1) {
                oneNumber++;
            }
            m /= 2;
        }
        if (n < 0) {
            oneNumber = 32 - oneNumber;
        }
        return oneNumber;
    }

    public static int numberOfOne2(int n) {
        int oneNumber = 0;
        while (n != 0) {
            if ((n & 0x01) == 1) {
                oneNumber++;
            }

            n  = n >>> 1;
        }
        return oneNumber;
    }

    public static int numboerOfOne3(int n) {
        int count = 0;
        while (n != 0) {
            count ++;
            n = (n - 1) & n;
        }
        return count;
    }




    @Test
    public void test14() {
        System.out.println(power(10, 1));
        System.out.println(power(10, 2));
        System.out.println(power(10, 3));
        System.out.println(power(10, 4));
    }

    /**
     * 求pow
     * 核心在于异常输入、边界输入
     */
    public static double power(double base, int expoent) {
        if (equal(base, 0.0) && expoent < 0) {
            return 0.0;
        }

        int absExpoent = expoent < 0 ? -expoent : expoent;

        double result = powWithPositiveNumber(base, absExpoent);
        if (expoent < 0) {
            result = 1.0 / result;
        }
        return result;
    }

    private static double powWithPositiveNumber(double base, int absExpoent) {
        double result = 1.0;
        for (int i=1; i <= absExpoent; i++) {
            result *= base;
        }
        return result;
    }

    private static boolean equal(double num1, double num2) {
        if ((num1 - num2 > - 0.0000001) &&
                (num1 - num2 < 0.0000001)) {
            return true;
        } else {
            return false;
        }
    }


    @Test
    public void test15() {
        int[] array = new int[] {1, 2, 3,4,5, 6,7,8,9,10,11,13,14,8};
        swapJishuOushu(array);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 偶数在前，奇数在后
     */
    public static void swapJishuOushu(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException();
        }

        int leftPos = 0;
        int rightPos = array.length - 1;

        while (leftPos < rightPos) {
            while ( array[leftPos] % 2 == 1 && leftPos < array.length) {
                leftPos++;
            }

            while (array[rightPos] % 2 == 0 && rightPos >= 0) {
                rightPos--;
            }

            if (leftPos < rightPos) {
                swap(array, leftPos, rightPos);
            }
        }
    }



    @Test
    public void test16() {
        SingleNode<Integer> header = new SingleNode(1, null);
        SingleNode<Integer>  header1 = new SingleNode(2, null);
        SingleNode<Integer>  header2 = new SingleNode(3, null);
        SingleNode<Integer>  header3 = new SingleNode(4, null);
        SingleNode<Integer>  header4 = new SingleNode(5, null);
        SingleNode<Integer>  header5= new SingleNode(6, null);
        header.nextNode = header1;
        header1.nextNode = header2;
        header2.nextNode = header3;
        header3.nextNode = header4;
        header4.nextNode = header5;

        int lastKthElement = findLastKthEle2(header, 3);
        System.out.println(lastKthElement);
    }

    /**
     * 输出链表的倒数第几个数字
     */
    public static <T> T findLastKthEle(SingleNode<T> header, int k) {
        if (header == null) {
            throw new IllegalArgumentException("header is null");
        }

        SingleNode<T> curNode = header;
        int size = 0;
        while(curNode != null) {
            curNode = curNode.nextNode;
            size++;
        }

        if (k > size) {
            throw new IllegalArgumentException("k is larger than singnode's size");
        }

        curNode = header;
        for(int i = 0 ; i < size - k ; i++) {
            curNode = curNode.nextNode;
        }

        return curNode.data;
    }

    /**
     * 第二种解法，两个指针同时遍历
     */
    public static <T> T findLastKthEle2(SingleNode<T> header, int k) {
        if (header == null) {
            throw new IllegalArgumentException("header is null");
        }

        if (k <= 0) {
            throw new IllegalArgumentException("k small than zero");
        }

        SingleNode<T> preNode = header;
        SingleNode<T> lastNode = header;
        int size  = -1;
        while(preNode != null) {
            preNode = preNode.nextNode;
            size++;
            if (size >= k) {
                lastNode = lastNode.nextNode;
            }
        }

        if (size < k) {
            throw new IllegalArgumentException("k is larger than singnode's size");
        }

        return lastNode.data;
    }






}
