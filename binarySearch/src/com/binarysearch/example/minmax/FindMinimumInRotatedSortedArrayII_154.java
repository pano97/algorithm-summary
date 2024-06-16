package com.binarysearch.example.minmax;

public class FindMinimumInRotatedSortedArrayII_154 {
    public static void main(String[] args) {
        int[] nums = {1,3,3};  // [2,2,2,1,2]  // [0,1,1,0]
        System.out.println(findMin(nums));

    }
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            while(left < right && nums[left] == nums[right]) --right;

            int mid = left + right + 1>> 1;

            if(nums[left] <= nums[mid]) {
                // 落入左边有序部分
                left = mid;
            } else
                right = mid - 1;
        }

        // 还原最小值位置
        while(left+1 < nums.length && nums[left] == nums[left+1]) ++left;

        return left + 1< nums.length ? nums[left+1] : nums[0];
    }
}
