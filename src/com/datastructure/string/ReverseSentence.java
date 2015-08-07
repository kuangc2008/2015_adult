package com.datastructure.string;

/**
 * Created by kuangcheng on 15-8-7.
 */
public class ReverseSentence {

    private static final String WORD_SEPARATOER = " ";

    public String reverse(String sentence) {
        if(sentence == null) {
            throw new IllegalArgumentException("null");
        }

        StringBuilder sb = new StringBuilder();
        String[] words = sentence.split(WORD_SEPARATOER);
        for(int i=words.length - 1; i >=0; i--) {
            sb.append(words[i]);
            if(i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

}
