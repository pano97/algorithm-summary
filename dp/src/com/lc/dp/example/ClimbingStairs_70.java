package com.lc.dp.example;

public class ClimbingStairs_70 {
    public static void main(String[] args) {
        System.out.println(climbStairs_2(45));
    }

    /**
     * Method 1: Recursion
     * time out
     * */
    public static int climbStairs(int n) {
        if(n < 3) return n;

        return climbStairs(n-1) + climbStairs(n-2);
    }

    /**
     * Method 2: dp
     * */

    public static int climbStairs_2(int n) {
        if(n < 3) return n;
        int[] dp = new int[n+1];

        dp[0] = 0; dp[1] = 1; dp[2] = 2;

        for(int i=3;i<=n;i++)
            dp[i] = dp[i-1] + dp[i-2];

        return dp[n];
    }
}
