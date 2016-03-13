package com.datastructure.string;

/**
 * 将int值转变为字符串
 */
public class IntToString {


    /**
     * 通过取余得到末尾，放到stringbuild中
     * @param number
     * @return
     */
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
