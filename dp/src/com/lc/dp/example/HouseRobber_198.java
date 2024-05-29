package com.lc.dp.example;

public class HouseRobber_198 {
    public static void main(String[] args) {
        int[] nums = {3,12,7};
        System.out.println(rob_2(nums));
    }

    /**
     * Method 1: Recursion
     * */
    public static int rob(int[] nums) {
        return _rob(nums, 0, 0, false);  // rob from 0 house
    }

    /**
     * When rob to index-th house, how much the money is.
     * nums: the money array
     * index: the robber comes to index-th hose
     * money: cur money
     * pre: the previous house was robbed
     * */
    private static int _rob(int[] nums, int index, int money, boolean pre) {
        if(index == nums.length) return money;

        // if previous house be robbed
        if(pre) return _rob(nums, index+1, money, false);
        else {
            int yes = _rob(nums, index+1, money+nums[index], true);
            int no =  _rob(nums, index+1, money, false);
            return Math.max(yes, no);
        }
    }


    /**
     * Method 2: dp
     * */
    public static int rob_2(int[] nums) {
        int N = nums.length;
        int[][] dp = new int[N][2];  // 0: no rob 1: rob

        dp[0][0] = 0;
        dp[0][1] = nums[0];

        for(int i=1;i<N;i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }

        return Math.max(dp[N-1][0],dp[N-1][1]);
    }


}
