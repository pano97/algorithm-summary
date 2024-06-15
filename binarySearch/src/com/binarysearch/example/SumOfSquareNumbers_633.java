package com.binarysearch.example;

public class SumOfSquareNumbers_633 {
    public static void main(String[] args) {
        System.out.println(judgeSquareSum_2(2147483600));
    }
    /**
     * 方法一: 数学开方 13.6% O(c^0.5)
     * */
    public static boolean judgeSquareSum(int c) {

        for(int i=0;i<=Math.sqrt(c);i++) {
            double b = Math.sqrt(c-i*i);
            // 判断整除 一个double取int等于double本身
            if((int)b == b) return true;
        }
        return false;
    }

    /**
     * 方法二：双指针
     * 假设a <= b
     * a从0开始，b从Math.sqrt(c)开始
     * 如果 a*a + b*b == c 返回
     * 如果 a*a + b*b < c  a++
     * 如果 a*a + b*b > c --b
     * */
    public static boolean judgeSquareSum_2(int c) {
        long left = 0, right = (long)Math.sqrt(c);
        while(left <= right) {
            long sum = left*left+right*right; // 可能会溢出
            //System.out.println("left="+left+" right="+right+" sum="+sum);
            if(sum == c) return true;
            else if(sum < c) ++left;
            else --right;
        }
        return false;
    }

}
