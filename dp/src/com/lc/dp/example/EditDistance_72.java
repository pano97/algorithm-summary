package com.lc.dp.example;

public class EditDistance_72 {
    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(minDistance(word1, word2));
    }
    /**
     * 动归
     * dp[i][j]: 将s1的前i个字符转为s2的前j个字符最少需要多少步操作
     * */
    public static int minDistance(String word1, String word2) {
        int M = word1.length();
        int N = word2.length();

        int[][] dp = new int[M+1][N+1];

        for(int i=0;i<=N;i++)
            dp[0][i] = i;

        for(int i=0;i<=M;i++)
            dp[i][0] = i;

        for(int i=1;i<=M;i++) {
            for(int j=1;j<=N;j++) {
                dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]) + 1;
                int tmp = word1.charAt(i-1) == word2.charAt(j-1) ? dp[i-1][j-1] : dp[i-1][j-1]+1;
                dp[i][j] = Math.min(dp[i][j], tmp);
            }
        }

//        for(int i=0;i<=M;i++) {
//            for(int j=0;j<=N;j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        return dp[M][N];
    }
}
