package com.lc.dp.example;

public class LongestIncreasingSubsequence_300 {
    public static void main(String[] args) {
        int[] nums = {7};
        System.out.println(lengthOfLIS(nums));
    }


    /**
     * Method 1: Recursion
     * Timeout
     * */
    public static int lengthOfLIS(int[] nums) {
        return _maxSubLength(nums, 0, Integer.MIN_VALUE, 0);
    }


    private static int _maxSubLength(int[] nums, int index, int preVal, int len) {
        if(index == nums.length) {
            return len;
        }

        for(int i=index;i<nums.length;i++) {   // select from left to right
            if(nums[i] <= preVal) continue;

            // nums[i] > preVal
            int use = _maxSubLength(nums, i+1, nums[i], len+1); // use
            int noUse = _maxSubLength(nums, i+1, preVal, len);  // or not use
            return Math.max(use, noUse);
        }
        return len;
    }

    /**
     * Method 2: dp
     * dp[i]: nums[0...i]'s maximum subsequence length
     * */
    public static int lengthOfLIS_2(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N+1];  // dp[i]表示nums[0..i-1]的最大子序列长度
        int[] prev = new int[N+1]; // prev[i]表示nums[0..i-1]最大递增子序列的右端值

        dp[0] = 0;
        prev[0] = Integer.MIN_VALUE;

        for(int i=0;i<N;i++) {
            for(int j=0;j<=i;j++) {
                if(nums[i] <= prev[j]) continue;

                dp[i+1] = Math.max(dp[i]+1, dp[i]);
            }
        }
        return dp[N];
    }
}
