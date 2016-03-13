package com.datastructure.string;

import com.datastructure.support.FakeFile;

/**
 *读取一个文件，删除文件中/的注释
 */
public class RemoveComments {
    private static final char ASTERISK =  '*';
    private static final char SLASH = '/';
    private static final char ANY_CHAR = 'c';

    public String remove(FakeFile file) {
        if (file == null) {
            throw new IllegalArgumentException("You can't pass a null file as argument.");
        }

        StringBuilder result = new StringBuilder();
        boolean openComment = false;
        String line = file.getLine();

        while(line != null) {
            char previson = ANY_CHAR;
            int openIndex = -1;

            char[] arr = line.toCharArray();
            for (int i=0; i<arr.length; i++) {
                char c = arr[i];
                if (openComment) {
                    if ( c == SLASH && previson == ASTERISK && openIndex < (i - 2)) {
                        openComment = false;
                    }
                } else {
                    if (c == ASTERISK && previson == SLASH) {
                        openIndex = i -1;
                        openComment = true;
                        result.deleteCharAt(result.length() - 1);
                    } else {
                        result.append(c);
                    }
                }
                previson = c;
            }
            line = file.getLine();
        }

        return result.toString();
    }


}
