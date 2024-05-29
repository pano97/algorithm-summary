package com.lc.dp.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class WordBreak_139 {
    public static void main(String[] args) {
        String s = "catsandogcat";
        String[] words = {
                "cats","dog","sand","and","cat","an"
        };
        List<String> wordDict = new ArrayList<>(Arrays.asList(words));

        System.out.println(wordBreak_2(s, wordDict));
    }

    /**
     * Method 1: Recursion
     * Time out.
     * */
    public static boolean wordBreak(String s, List<String> wordDict) {
        String[] dict = new String[wordDict.size()];
        for(int i=0;i<dict.length;i++)
            dict[i] = wordDict.get(i);

        return _wordBreak(s, dict, 0);
    }


    private static boolean _wordBreak(String s, String[] dict, int index) {
        if(index >= s.length()) return true;

        for(int i=0;i<dict.length;i++) {
            String word = dict[i];

            if(!s.startsWith(word, index)) continue;

            boolean next = _wordBreak(s, dict, index+word.length());
            if(next) return true;
        }
        return false;
    }

    /**
     * Method 2: dp
     * dp[i]: s[0...i] can be composed or not using wordDict
     * */
    public static boolean wordBreak_2(String s, List<String> wordDict) {
        int N = s.length();
        boolean[] dp = new boolean[N+1];
        dp[0] = true;

        HashSet<String> set = new HashSet<>();
        set.addAll(wordDict);

        for(int i=1;i<=N;i++) {
            for(int j=0;j<i;j++) {
                if(dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;  // update the i position and break
                }

            }
        }

        return dp[N];
    }


}
