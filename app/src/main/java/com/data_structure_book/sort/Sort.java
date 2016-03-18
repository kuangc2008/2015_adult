package com.data_structure_book.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by kuangcheng01 on 2016/3/18.
 */
public class Sort {

    public static void main(String[] args) {
        Integer[] arrays = new Integer[] {
                10, 121, 12, 2121, 343, 545454,5, 45
        };
//        insertionSort(arrays);
//        heapSort(arrays);

        System.out.println(Arrays.toString(arrays));
    }

//
//    public static <T extends Comparable<? super T>> void quickSort(T[] a) {
//        quickSort(a, 0, a.length - 1);
//    }
//
//    private static <T extends Comparable<? super T>> T median3(T[] a,  int left, int right) {
//        int center = (left + right) /2;
//        if (a[center].compareTo(a[left]) < 0) {
//            swap(a, left, center);
//        }
//        if (a[right].compareTo(a[left]) < 0) {
//            swap(a, left, right);
//        }
//        if (a[right].compareTo(a[center]) < 0) {
//            swap(a, center, right);
//        }
//
//        swap(a, center, right-1);
//        return a[right - 1];
//    }
//
//    private static <T extends Comparable<? super T>> void quickSort(
//            T[] a, int left, int right) {
//
//        if (right - left < 100) {
//            insertionSort(a);
//        } else {
//            T pivot = median3(a, left, right);
//            // begin
//            int i = left, j = right - 1;
//            for(;;) {
//                while (a[++i].compareTo(pivot) < 0) {
//                }
//                while (a[--j].compareTo(pivot) > 0) {
//                }
//                if (i < j) {
//                    swap(a, i, j);
//                } else {
//                    break
//                }
//            }
//
//            swap(a, i, right - 1);
//
//        }
//    }














    /**
     * 并归排序
     * 画成很小的块，然后做merge
     */
    public static <T extends Comparable<? super T>> void mergeSort(T[] a) {
        T[] tmpArray = (T[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }

    private static <T extends Comparable<? super T>> void mergeSort(
            T[] a, T[] tmpArray, int left, int right){
        if (left < right) {
            int center = (left + right) /2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center+1 , right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    public static <T extends Comparable<? super T>> void merge(
            T[] a, T[] tmpA, int leftPos, int rightPos, int rightEnd ) {

        int leftEnd = rightEnd - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while ( leftPos <= leftEnd && rightPos <= rightEnd) {
            if (a[leftPos].compareTo(a[rightPos]) <= 0)  {
                tmpA[tmpPos ++] = a[leftPos++];
            } else {
                tmpA[tmpPos ++] = a[rightPos++];
            }
        }

        while(leftPos <= leftEnd) {
            tmpA[tmpPos++]  = a[leftPos++];
        }

        while(rightPos <= rightEnd) {
            tmpA[tmpPos++] = a[rightPos++];
        }

        //copy tmpArray back
        for(int i=0; i<numElements; i++, rightEnd--) {
            a[rightEnd] = tmpA[rightEnd];
        }
    }



    /**
     * 2 堆排序
     */
    public static <T extends Comparable<? super T>> void heapSort(T[] a) {
        for (int i= a.length/2; i >= 0; i--) {
            percDown(a, i, a.length);
        }
        for (int i=a.length-1; i> 0; i--) {
            swap(a, 0, i);
            percDown(a, 0, i);
        }
    }

    /**
     * 下绿最大的；  先比较两个子节点，再笔记自己与子界面；   循环到结束.
     */
    private static <T extends Comparable<? super T>> void percDown(T[] a, int i, int n) {

        int child;
        T tmp;

        for( tmp = a[i] ; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n-1 && a[child].compareTo(a[child + 1]) < 0) {
                child++;
            }
            if (tmp.compareTo(a[child]) < 0) {
                a[i] = a[child];
            } else {
                break;
            }
        }
        a[i] = tmp;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }










    /**
     * 1 从0到p是排序状态，最后直到n
     * 核心就是：其实I就是j与j-1的对比
     */
    public static <T extends Comparable<? super T>>  void insertionSort(T[] a) {

        for(int i=1; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                int comResult = a[j-1].compareTo(a[j]);
                if(comResult > 0) {
                    swap(a, j-1, j);
                } else {
                    break;
                }
            }
        }
    }

    public static<T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }







}
