package com.binarysearch.example;

public class SearchA2dMatrix_74 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        System.out.println(searchMatrix_2(matrix, 60));
    }

    /**
     * Method 1: Twice binary search
     * */
    /*public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int row = 0, col = 0;

        // start from upper right point
        if(matrix[0][n-1] <= target) {
            // search down
            row = _searchDown(matrix, 0, m-1, target, n-1);
            col = _searchLeft(matrix, 0, n-1, target, row);

        } else {
            // search left
            col = _searchLeft(matrix, 0, n-1, target, 0);
            row = _searchDown(matrix, 0, m-1, target, col);
        }

        return matrix[row][col] == target;
    }



    private static int _searchDown(int[][] matrix, int left, int right, int target, int col) {

        while(left < right) {
            int mid = left + right>> 1;
            if(matrix[mid][col] >= target) { // 右区间，找左边界
                right = mid;
            } else
                left = mid + 1;
        }

        return left;
    }


    private static int _searchLeft(int[][] matrix, int left, int right, int target, int row) {

        while(left < right) {
            int mid = left + right >> 1;
            if(matrix[row][mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
    */


    /**
     * 优化
     * Start from (0,0) position, find first >= target element,
     * then search by row
     * */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int row = _searchCol(matrix, 0, m-1, target);

        if(matrix[row][0] == target) return true;

        int col = _searchRow(matrix[row], 0, n-1, target);

        return matrix[row][col] == target;
    }

    private static int _searchCol(int[][] matrix, int left, int right, int target) {

        while(left < right) {
            int mid = left + right + 1 >>1;
            if(matrix[mid][0] <= target) {
                // 左区间，找右边界限
                left = mid;
            }
            else
                right = mid - 1;
        }
        return left;
    }

    private static int _searchRow(int[] nums, int left, int right, int target) {
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] >= target) {
                // 右边区间，找左边界限
                right = mid;
            } else
                left = mid + 1;
        }
        return left;
    }


    /**
     * Method 2: Mapping position
     * 想象将长方形展开成一维数组
     * 下标mapping
     * */

    public static boolean searchMatrix_2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int index = _search2(matrix, 0, m*n-1, target);

        return matrix[index/n][index%n] == target;
    }

    private static int _search2(int[][] matrix, int left, int right, int target) {
        int n = matrix[0].length;
        while(left < right) {
            int mid = left + right >> 1;
            int i = mid / n;
            int j = mid % n;
            if(matrix[i][j] >= target) {
                // 右区间，找左边界限
                right = mid;
            } else
                left = mid + 1;
        }
        return left;
    }
}
