package com.datastructure.string;

/**
 * Created by chengkuang on 15/8/8.
 */
public class SimpleRegularExpression {


    public boolean evaluate(String souce, String pattern) {

        if (souce == null || pattern == null) {
            throw new IllegalArgumentException("null");
        }

        if(pattern.length() == 0) {
            return souce.length() == 0;
        }

        if(pattern.length() == 1 || pattern.charAt(1) != '*') {
            if (souce.length() < 1 || (pattern.charAt(0) != '.' && souce.charAt(0) != pattern.charAt(
                    0))) {
                return false;
            }
            return evaluate(souce.substring(1), pattern.substring(1));
        } else {
            int len = souce.length();

            int i = -1;
            while( i<len && (i<0 || pattern.charAt(0) == '.' || pattern.charAt(0) == souce.charAt(i))) {
                if(evaluate(souce.substring( i +1), pattern.substring(2))) {
                    return true;
                }
                i ++;
            }
            return false;
        }
    }
}
