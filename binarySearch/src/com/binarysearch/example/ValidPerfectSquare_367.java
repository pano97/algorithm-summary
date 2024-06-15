package com.binarysearch.example;

public class ValidPerfectSquare_367 {
    public static void main(String[] args) {
        System.out.println(isPerfectSquare(5));
        System.out.println(isPerfectSquare(Integer.MAX_VALUE));
    }
    /**
     * 判断一个数是否是完全平方数，不能使用内置的sqrt函数
     * num 范围 [1, MAX_VALUE]
     * 方法一: 二分法
     * */
    public static boolean isPerfectSquare(int num) {
        int left = 1, right = num;

        while(left < right) {
            int mid = left + ((right - left) >> 1);
            if(mid  >= num / mid) {
                // 右区间的左端点
                right = mid;
            } else
                left = mid + 1;
        }

        return (long) left * left == (long) num;
    }
}
