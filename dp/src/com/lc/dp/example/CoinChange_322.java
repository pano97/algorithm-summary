package com.lc.dp.example;

import java.util.Arrays;

public class CoinChange_322 {
    public static void main(String[] args) {
        int[] coins = {474,83,404,3};
        int amount = 264;
        System.out.println(coinChange_2(coins, amount));
    }

    /**
     * Method 1: Recursion, Timeout
     * */
    static int minCnt = Integer.MAX_VALUE;

    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        return _findCoin(coins, amount, 0);
    }

    private static int _findCoin(int[] coins, int rest, int cnt) {
        if(rest == 0) return cnt;

        for(int i=0;i<coins.length;i++) {
            if(rest >= coins[i]) {
                int next = _findCoin(coins, rest-coins[i], cnt+1);

                if(next != -1) {
                    minCnt = Math.min(next, minCnt);
                }
            }
        }
        return minCnt == Integer.MAX_VALUE? -1: minCnt;
    }

    /**
     * Method 2: dp[N]
     * Compose N dollars, the minimal coins number
     *
     * */
    public static int coinChange_2(int[] coins, int amount) {
        Arrays.sort(coins);

        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for(int i=1;i<=amount;i++) {  // i is the dp position
            for(int j=0;j<coins.length;j++) {
                if(coins[j] > i) break;
                dp[i] = Math.min(dp[i-coins[j]] + 1, dp[i]);
            }
        }
        return dp[amount] > amount ? -1: dp[amount];
    }
}
