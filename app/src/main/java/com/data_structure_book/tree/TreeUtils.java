package com.data_structure_book.tree;

import java.io.File;

/**
 * Created by kuangcheng01 on 2016/3/14.
 */
public class TreeUtils {


    /**
     * 打印目录
     * 先打印出根目录，再有两个tab，再打印其他目录
     *
     * 其实是前序遍历
     */
    public static void listAll() {
        listDepth(new File("D:\\worksapce2\\mysrc\\2015_adult2\\2015_adult\\app\\src"), 0);
    }


    public static void listDepth(File filePath, int depath) {
        for(int i =0; i<depath; i++) {
            System.out.print("  ");
        }
        System.out.println(filePath.getName());
        if (filePath.isDirectory()) {
            ++depath;
            for (File subFIilePath : filePath.listFiles()) {
                listDepth(subFIilePath,depath);
            }
        }
    }


    /**
     * 后续遍历，打印路径，以及大小
     * @param filePath
     * @param depath
     * @return
     */
    public static long printSize(File filePath, int depath) {

        if(filePath.isDirectory()) {
            File[] subFiles = filePath.listFiles();
            int nextDepath = depath + 1;
            long totalSize = 0;
            for(File subFile : subFiles) {
                totalSize += printSize(subFile, nextDepath);
            }
            for(int i =0; i<depath; i++) {
                System.out.print("  ");
            }
            totalSize += filePath.length();
            System.out.println(filePath.getName() + ", " + totalSize);
            return totalSize;
        } else {
            long size = filePath.length();
            for(int i =0; i<depath; i++) {
                System.out.print("  ");
            }
            System.out.println(filePath.getName() + ", " + size);
            return size;
        }


    }

}
