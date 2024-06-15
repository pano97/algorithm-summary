package com.binarysearch.example;

/**
 * Forward declaration of guess API.
 * @param    your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */


public class GuessNumberHigherOrLower_374 {
    public static void main(String[] args) {

    }

    private static int guess(int n) {
        return -1;
    }
    /**
     * 方法一：二分法
     * num范围 [1, MAX_VALUE] 注意溢出
     * pick范围 [1, MAX_VALUE]
     * */
    public static int guessNumber(int n) {
        int left = 1, right = n;

        while(left < right) {
            int mid = left + (right - left) / 2;
            if(guess(mid) <= 0)
            {
                // 右边区间的左端点
                right = mid;
            } else
                left = mid + 1;
        }
        return left;
    }
}
