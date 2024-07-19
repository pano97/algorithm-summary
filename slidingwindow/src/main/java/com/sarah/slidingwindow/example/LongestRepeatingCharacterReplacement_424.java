package com.sarah.slidingwindow.example;

public class LongestRepeatingCharacterReplacement_424 {
    public static void main(String[] args) {
        String s = "ABBB";
        int k = 2;
        System.out.println(characterReplacement(s,k));
    }

    // s= AABABBA k=1
    /**
     * 方法一：滑动窗口
     * 改变次数为k, 右边指针不断向右滑动
     * s长度[1,10^5]
     * k [0,s.length]
     * */
    public static int characterReplacement(String s, int k) {
        int n = s.length();
        int remain = k;
        int res = 0;

        // 固定左指针
        for(int left=0,right=0;left<n;left++) {
            // 右指针一直向右边移动
            right = left;
            while(right < n && !(s.charAt(left) != s.charAt(right) && remain == 0)) {
                if(s.charAt(left) == s.charAt(right)) right++;
                else {
                    --remain;
                    right++;
                }
            }
            System.out.println("left="+left+" right="+right);

            res = Math.max(res, right-left);
            remain = k;
        }

        return res;
    }
}
