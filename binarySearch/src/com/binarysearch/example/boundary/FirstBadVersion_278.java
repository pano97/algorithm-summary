package com.binarysearch.example.boundary;

public class FirstBadVersion_278 {
    public static void main(String[] args) {

    }
    // [T, T, T, F, F, F]
    /**
     * 方法一：二分
     * 找右边区间的左端点问题
     * */
    public static int firstBadVersion(int n) {
        int left = 1, right = n;
        while(left < right) {
            int mid = left + right >> 1;
            if(isBadVersion(mid)) {
                // 右区间
                right = mid;
            } else
                left = mid + 1;
        }
        return left;
    }
    public static boolean isBadVersion(int version) {
        return true;
    }

}
