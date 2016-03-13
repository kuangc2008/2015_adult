package com.data_structure_book.array;

/**
 * Created by kuangcheng01 on 2016/3/13.
 */
public class MyUtils {

    public static <T> void preint(MyCollection<T> coll) {
        MyIterator<T> itr = coll.iterator();

        StringBuilder ss = new StringBuilder();
        ss.append("[");
        while(itr.hasNext()) {
            T item = itr.next();
            ss.append(item.toString());
            ss.append(",");
        }

        int lastIndex = ss.lastIndexOf(",");
        if (lastIndex > 0) {
            ss.deleteCharAt(lastIndex);
        }

        ss.append("]");

        System.out.println(ss);
    }
}
