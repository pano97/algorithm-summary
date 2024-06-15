package com.binarysearch.example;

import java.util.Arrays;

public class IntersectionOfTwoArrays_349 {
    public static void main(String[] args) {
        int[] nums1 = {1,11,1,2,2,3};
        int[] nums2 = {1,2,3,11};
        int[] res = intersection(nums1,nums2);
        for(int i: res)
            System.out.print(i+" ");
        System.out.println();
    }
    /**
     * 分析：
     * nums1, nums2长度都在1000以内
     * nums1和nums2的内容都在1000以内
     * 数据规模很小，可以用数组，也可以用哈希表存储
     * 98.17%
     * */
    public static int[] intersection(int[] nums1, int[] nums2) {
        int[] dp = new int[1005];
        Arrays.fill(dp, 0);

        for(int i=0;i<nums1.length;i++) {
            if(dp[nums1[i]] == 0) dp[nums1[i]]++;
        }


        for(int i=0;i<nums2.length;i++) {
            if(dp[nums2[i]] == 1) dp[nums2[i]]++;
        }

        int[] res = new int[1005];
        int k = 0;
        for(int i=0;i<dp.length;i++) {
            if(dp[i] > 1) {
                res[k++] = i;
            }
        }
        return Arrays.copyOfRange(res, 0, k);
    }
}
