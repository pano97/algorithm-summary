package com.binarysearch.example;

public class SearchA2dMatrixII_240 {
    public static void main(String[] args) {

    }

    /**
     * 方法一：从右上角为起点遍历，O(n) 99.82%
     * */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;

        int m = matrix.length;
        int n = matrix[0].length;

        int i=0, j=n-1;

        while(i < m && j >= 0) {
            if(matrix[i][j] == target) return true;
            else if(matrix[i][j] > target) --j;
            else ++i;
        }
        return false;
    }

    /**
     * 方法二：对每一行进行二分查找
     * O(mlogn) 99.82%
     * */
    public static boolean searchMatrix_2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;
        int m = matrix.length;

        // 对每一行进行二分
        for(int i=0;i<m;i++) {
            if(matrix[i][0] <= target) {
                int j = binarySearch(matrix[i], target);
                if(j!=-1) return true;
            }
        }
        return false;
    }

    private static int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int mid = left + right + 1>> 1;
            if(nums[mid] <= target)
                left = mid;
            else
                right = mid - 1;
        }
        return nums[left] == target ? left: -1;
    }

}
