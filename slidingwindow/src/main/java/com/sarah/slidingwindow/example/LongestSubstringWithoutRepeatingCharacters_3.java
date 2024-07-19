package com.sarah.slidingwindow.example;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口
 * 给一个字符串s，返回最长勿重复字串长度
 * s包括字母，数字还有空格等
 * */
public class LongestSubstringWithoutRepeatingCharacters_3 {
    public static void main(String[] args) {
        String s = "tmmzuxt";
        System.out.println(lengthOfLongestSubstring(s));
    }

    /**
     * 方法一: 滑动窗口 78.72% O(n)
     * 1.start和end设置成0
     * 2.当end指向的位置map中没有，将这个元素放入map, end后移，动态维护res
     * 3.当end指向位置map中有，start变为map.get(end)+1位置，将end这个元素
     * 重新放入map,更新最远位置，end后移
     * 4.循环条件end < n
     *
     * */
    public static int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();

        Map<Character, Integer> map = new HashMap<>();

        // 将开始位置和末尾位置设置成一样
        int start = 0, end = 0;
        int res = 0;

        while(end < s.length()) {
            // end在map中存在
            // 这一句很重要，如果说map中包含该元素，还要是这个元素的位置在窗口内
            if(map.getOrDefault(s.charAt(end), -1) >= start) {
            //if(map.containsKey(s.charAt(end)) && map.get(s.charAt(end)) >= start) {
                // start指向下一个位置
                start = map.get(s.charAt(end)) + 1;
                // end新位置放入map
                map.put(s.charAt(end), end);
                end++;
            } else {
                // end在map中不存在, start位置不变时更新长度
                map.put(s.charAt(end), end);
                res = Math.max(res, end-start+1);
                //System.out.println("start="+start+" end="+end+" res="+res);
                end++;
            }
        }
        return res;
    }
}
