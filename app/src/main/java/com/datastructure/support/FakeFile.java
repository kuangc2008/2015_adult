package com.datastructure.support;

/**
 * Created by kuangcheng01 on 2016/3/7.
 */
public class FakeFile {
    private final String[] lines;
    private int index;

    FakeFile(String ... lines) {
        this.lines = lines != null ? lines : new String[0];
    }

    public String getLine() {
        return index <= lines.length -1 ? lines[index++] : null;
    }
}
