package com.binarysearch.example.minmax;

public class SearchInRotatedSortedArrayII_81 {
    public static void main(String[] args) {
        int[] nums = {5,1,3};
        System.out.println(search(nums, 1));
    }

    /**
     * 方法一：二分法
     * 和33题类似，但有重复元素
     * [2,2,2,3,0,1,2]
     * 如果首尾出现相同的元素，会给判断左右部分造成困扰
     * 比如nums[mid] >= nums[start]，并不能表示mid出现在左部分
     * 解决方案：在二分循环中做一些处理，每次二分之前都移动left和right指针略过重复元素
     * */
    public static boolean search(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        // 只有这一步和33题不一样
        //while(left < right && nums[left] == nums[right]) --right;
        // while(left < right && nums[left] == nums[left+1]) ++left;

        while(left < right) {

            if(left < right && nums[left] == nums[right]) --right;
            // [2,3,4,0,1]
            int mid = left + right >> 1;
            //System.out.println("left="+left+" right="+right+" mid="+mid);


            if(nums[mid] >= nums[left]) {
                // 落入左半边有序部分
                if(nums[left] <= target && target <= nums[mid])
                    right = mid;
                else
                    left = mid + 1;
            } else {
                // 落入右半边
                if(nums[mid] <= target && target <= nums[right])
                    left = mid;
                else
                    right = mid - 1;
            }
        }
        return nums[left] == target;
    }

}
