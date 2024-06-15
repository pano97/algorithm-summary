package com.binarysearch.example.boundary;

public class FindFirstAndLastPositionOfElementInSortedArray_34 {
    public static void main(String[] args) {
        int[] nums = {1};
        int[] res = searchRange(nums, 1);
        System.out.println(res[0] + " "+res[1]);
    }
    // 1,3,5,5,6,8
    public static int[] searchRange(int[] nums, int target) {
        // 数组可能为空
        if(nums==null || nums.length == 0) return new int[]{-1,-1};

        int left = 0, right = nums.length - 1;
        // 找左区间的右边界，左区间<target
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (nums[mid] <= target) left = mid;
            else right = mid - 1;
        }

        int j = nums[left] == target ? left : -1;
        if (j == -1) return new int[]{-1, -1};

        // 找右区间的左边界, 右区间 > target
        left = 0;
        right = nums.length - 1;

        while (left < right) {
            int mid = left + right >> 1;
            if (nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        int i = nums[left] == target ? left : -1;
        return new int[]{i, j};
    }



}
