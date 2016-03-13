package com.data_structure_book.array;

/**
 * Created by kuangcheng01 on 2016/3/13.
 */
public class Main {
    public static void main(String[] args) {


        MyArrayList<Integer> list = new MyArrayList<Integer>();
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);

        list.add(0, 1);
        list.add(3, 10);

        MyUtils.preint(list);

        list.remove(1);

        MyUtils.preint(list);

    }
}
