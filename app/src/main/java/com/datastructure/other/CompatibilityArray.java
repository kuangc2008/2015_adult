package com.datastructure.other;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kuangcheng01 on 2016/3/10.
 */
public class CompatibilityArray {
    static class Person {

    }

    static class Student extends Person {

    }

    static class Teacher extends Person {

    }


    public static void main(String[] args) {

        //斜边数组类型
        Person[] perons = new Student[10];   //可以兼容
        perons[0] = new Teacher();   //不会异常，但会报ArrayStoreException


        //无法编译，泛型不是协变得
      //  Collection<Person> persons= new ArrayList<Student>();
        //需要通过extends或super属性来支持鞋变形
        Collection<? extends Person> persons = new ArrayList<Student>();



    }



}
