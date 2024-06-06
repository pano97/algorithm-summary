package com.binarysearch.example;

public class FindPeakElement_162 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(findPeakElement(nums));
    }
    public static int findPeakElement(int[] nums) {
        if(nums.length < 2) return nums.length - 1;
        if(nums.length == 2) return nums[0] > nums[1] ? 0: 1; // 加入边界判断

        return _search(nums, 0, nums.length-1);
    }

    private static int _search(int[] nums, int left, int right) {
        while(left < right) {
            int mid = left + right + 1>> 1;

             if(nums[mid] > nums[mid-1]) {
                 left = mid;
             }else
                 right = mid - 1;

        }
        return left;
    }

}
