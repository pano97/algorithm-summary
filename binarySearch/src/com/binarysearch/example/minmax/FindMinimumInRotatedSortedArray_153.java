package com.binarysearch.example.minmax;

public class FindMinimumInRotatedSortedArray_153 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(findMin_2(nums));
    }

    // [3,4,5,1,2]
//    public static int findMin(int[] nums) {
//        if(nums == null || nums.length == 0) return -1; // not need
//        if(nums[0] < nums[nums.length-1]) return nums[0];
//
//        int left = 0, right = nums.length-1;
//        while(left < right) {
//            int mid = left + right>> 1;
//
//            // 右边区间的左端点
//            if(nums[mid] < nums[0]) {
//                right = mid;
//            } else
//                left = mid+1;
//        }
//
//        return nums[left];
//    }

    public static int findMin_2(int[] nums) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = left + right + 1>> 1;

            if(nums[mid] > nums[left]) {
                // mid落入左区间
                left = mid;
            } else
                right = mid -1;
        }
        return left + 1 < nums.length ? nums[left+1] : nums[0];
    }
}
