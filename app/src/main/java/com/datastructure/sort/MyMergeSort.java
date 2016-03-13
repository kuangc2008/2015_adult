package com.datastructure.sort;

import java.util.Arrays;

/**
 * Created by kuangcheng01 on 2016/3/7.
 */
public class MyMergeSort {

    public static void main(String[] args) {
        Integer[] x = {9, 2, 4, 7, 3, 7, 10, 12, 1 ,8};
        System.out.println(Arrays.toString(x));

        mergeSort(x);
        System.out.println(Arrays.toString(x));
    }

    public static void mergeSort(Comparable[] a) {
        Comparable[] tmp = new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    private static void mergeSort(Comparable[] a, Comparable[] tmp, int left, int right) {
        if (left < right) {
            int center = (left + right)/2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center+1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    private static void merge(Comparable[] a, Comparable[] tmp, int left, int right, int rightEnd) {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {
            if (a[left].compareTo(a[right]) < 0) {
                tmp[k++] = a[left++];
            } else {
                tmp[k++] = a[right++];
            }
        }

        while( left <= leftEnd) { //copy rest of first half;
            tmp[k++] = a[left++];
        }
        while(right <= rightEnd) { //copy rest of right half
            tmp[k++] = a[right++];
        }

        for (int i=0; i<num; i++, rightEnd--) {
            a[rightEnd] = tmp[rightEnd];
        }
    }
}
