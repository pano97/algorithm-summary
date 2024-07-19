package com.sarah.slidingwindow.example;

import java.util.*;

/**
 * 滑动窗口子序列问题
 * 给一个字符串s, 返回子序列长度为10的所有可能，不重复
 * */
public class RepeatedDNASequences_187 {
    public static void main(String[] args) {
        String s = "AAAAAAAAAAAAA";
        List<String> res = findRepeatedDnaSequences(s);
        for(String t:res)
            System.out.println(t);
    }

    /**
     * 方法一：滑动窗口 20.09%
     * 时间复杂度O(NL)
     * */
    public static List<String> findRepeatedDnaSequences(String s) {
        // s长度小于10直接返回空
        if(s == null || s.length() < 10) return new ArrayList<>();

        int start = 0, end = 9;

        Map<String, Integer> map = new HashMap<>();

        while(end < s.length()) {
            String cur = s.substring(start,end+1);
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            start++;
            end++;
        }

        List<String> res = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            if(entry.getValue() > 1)
                res.add(entry.getKey());
        }

        return res;
    }
}
