package com.datastructure.string;

/**
 * 如果第二个string包含第一个string，则返回true
 */
public class Contains {

    /**
     * w2 contains w1
     * @param w1
     * @param w2
     * @return
     */
    public boolean evluate(String w1, String w2) {
        if( w1 == null || w2 == null) {
            throw new IllegalArgumentException("null");
        }

        boolean contains = false;

        for(int i=0; i < w2.length() -1 ; i++) {
            if(w2.charAt(i) == w1.charAt(0)) {
                for(int j=0; j < w1.length(); j++) {
                    if(w1.charAt(j) == w2.charAt(i + j) && j == w1.length() -1 ) {
                        contains = true;
                        break;
                    }
                }
            }
        }

        return contains;
    }
}
