package com.lc.dp.example;

public class MaximalSquare_221 {
    public static void main(String[] args) {
        char[][] matrix = {
                {'0','1'},
                {'1','0'}
        };
        System.out.println(maximalSquare(matrix));
    }

    /**
     * dp[i][j]: 以(i,j)为右下角最大正方形边长
     * */
    public static int maximalSquare(char[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] dp = new int[M][N];

        int res = 0;

        // init dp
        for(int i=0;i<M;i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1: 0;
        }

        for(int j=0;j<N;j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1: 0;
        }

        for(int i=1;i<M;i++) {
            for(int j=1;j<N;j++) {
                if(matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    res = Math.max(dp[i][j], res);
                }
            }
        }

        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                res = Math.max(dp[i][j], res);
                //System.out.print(dp[i][j]+" ");
            }
            //System.out.println();
        }

        return res * res;
    }



}
