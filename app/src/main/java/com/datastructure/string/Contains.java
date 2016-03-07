package com.datastructure.string;

/**
 * Created by chengkuang on 15/8/8.
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
