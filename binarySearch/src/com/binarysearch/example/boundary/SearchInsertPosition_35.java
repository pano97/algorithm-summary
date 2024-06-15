package com.binarysearch.example.boundary;

public class SearchInsertPosition_35 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        int target = 5;
        System.out.println(searchInsert(nums, target));
    }

    public static int searchInsert(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length-1, target);
    }

    private static int binarySearch(int[] nums, int left, int right, int target) {

        while(left < right) {
            int mid = left + right>> 1;
            if(nums[mid] >= target)
                right = mid;
            else
                left = mid + 1;
        }
        return nums[left] < target ? left+1:left;
    }
}
