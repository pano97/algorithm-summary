package com.binarysearch.example;

public class MinimumSizeSubarraySum_209 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,1};
        System.out.println(minSubArrayLen(10,nums));
    }
    /**
     * Method 1: 双指针，O(N) 99.74%
     * */
    public static int minSubArrayLen(int target, int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // init dp
        for(int i = 1;i<nums.length;i++)
            dp[i] = dp[i-1] + nums[i];

        int res = Integer.MAX_VALUE;

        int start = 0, end = 0;
        while(start < nums.length && end < nums.length) {
            if(dp[end] - dp[start] + nums[start] >= target) {
                res = Math.min(res, end-start+1);
                ++start;
            } else
                ++end;
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

}
