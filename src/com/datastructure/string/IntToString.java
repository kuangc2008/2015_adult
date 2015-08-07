package com.datastructure.string;

/**
 * Created by kuangcheng on 15-8-7.
 */
public class IntToString {

    public String transForm(int number) {
        boolean isNegative = false;
        StringBuilder sb = new StringBuilder();
        if(number == 0) {
            return "0";
        } else if(number < 0) {
            isNegative = true;
            number = -number;
        }

        while(number > 0) {
            sb.append(number%10);
            number/= 10;
        }
        if(isNegative) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }
}
