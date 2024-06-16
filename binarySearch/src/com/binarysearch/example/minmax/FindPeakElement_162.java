package com.binarysearch.example.minmax;

public class FindPeakElement_162 {
    public static void main(String[] args) {
        int[] nums = {2,3,4,3,2,1};
        System.out.println(findPeakElement_2(nums));
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


    // [2,3,4,3,2,1] 100%
    public static int findPeakElement_2(int[] nums) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = left + right + 1>> 1;  // >=1

            if(nums[mid] > nums[mid-1])
                left = mid;
            else
                right = mid - 1;
        }
       return left;
    }

}
