package com.binarysearch.example;

import java.util.ArrayList;
import java.util.List;

public class KthSmallestElementInASortedMatrix_378 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,5,7},
                {3,5,13},
                {3,13,15}
//                {1,4},
//                {2,5}
        };
        System.out.println(kthSmallest(matrix,2));
        System.out.println(kthSmallest_2(matrix,2));
    }

    /**
     * 方法一：暴力法 O(nlogn) 5.16%
     * 申请额外数组
     * */
    public static int kthSmallest(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0) return -1;
        int m = matrix.length;
        int n = matrix[0].length;

        List<Integer> dp = new ArrayList<>();

        for(int i=0;i<m*n;i++) {
            int x = i / m;
            int y = i % n;
            int pos = getInsertPos(dp, matrix[x][y]);
            if(pos == -1) dp.add(matrix[x][y]);
            else
                dp.add(pos, matrix[x][y]);
        }

        // print dp
//        for(int i:dp)
//            System.out.print(i+" ");
//        System.out.println();
        return dp.get(k-1);
    }

    /**
     * Binary Search
     * */
    private static int getInsertPos(List<Integer> dp, int target) {
        if(dp.isEmpty()) return -1;

        int left = 0, right = dp.size()-1;

        while(left < right) {
            int mid = left + right >> 1;
            if(dp.get(mid) >= target) {
                // 右区间的左端点
                right = mid;
            } else
                left = mid + 1;
        }
        return dp.get(left) < target ? -1 : left;
    }

    /**
     * Method 2: Binary search in matrix
     * l = matrix[0][0]
     * r = matrix[n-1][n-1]
     * 从matrix[n-1][0]开始走
     * 如果 matrix[i][j] <= mid, 将(i,j)位置所在的列中 < mid 的数量累加
     * 当(i,j)超出矩阵边界，所有[l,r]中小于等于mid的数量统计完成
     * 根据cnt和k的关系继续二分
     * if cnt < k, [mid+1, r]
     * else cnt >= k [l, mid]
     * */
    public static int kthSmallest_2(int[][] matrix, int k) {
        if(matrix == null || matrix.length == 0) return 0;
        int n = matrix.length;

        int left = matrix[0][0], right = matrix[n-1][n-1];

        while(left < right) {
            int mid = left + right >> 1;
            int cnt = 0;

            // search in the matrix, always from bottom left
            for(int i=n-1,j=0;i>=0 && j<n;) {
                if(matrix[i][j] <= mid) {
                    cnt += i+1;
                    j++; // move to right
                } else
                    --i;
            }
            //System.out.println("cnt="+cnt);

            // here, I compute the cnt
            if(cnt >= k) right = mid;
            else left = mid + 1;
        }
        return left;
    }




}
