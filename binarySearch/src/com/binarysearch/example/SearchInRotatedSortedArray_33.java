package com.binarysearch.example;

public class SearchInRotatedSortedArray_33 {
    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        System.out.println(search_2(nums, 0));
    }

    /**
     * 方法一: 穷举可能性
     * */
    /*public static int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = left + right + 1>> 1;
            if(nums[mid] == target) return mid;

            if(target >= nums[left]) {
                // target落入左边区间
                if(nums[mid] < nums[left]) {
                    // mid在右区间
                    right = mid - 1;
                } else {
                    // mid在左区间
                    if(nums[mid] > target) right = mid - 1;
                    else left = mid;
                }
            } else {
                // target 落入右边区间
                if(nums[mid] >= nums[left]) {
                    // mid落入左区间
                    left = mid + 1;
                } else {
                    // mid 落入右区间
                    if(nums[mid] > target) right = mid - 1;
                    else left = mid;
                }
            }
        }

        // 防止单调序列
        return left < nums.length && nums[left] == target ? left : -1;
    }    */

    /**
     * 方法二：二分法(使用模板)
     * 二分，将数组分为有序部分和无序部分
     * */
    public static int search_2(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = left + right +1>> 1;
            //if(nums[mid] == target) return mid; 用模板就不能在这里返回

            if(nums[mid] >= nums[left]) {
                // left ... mid有序
                if(target >= nums[left] && target < nums[mid]) right = mid-1;
                else left = mid;  // 使用了模板

            } else {
                // mid ... right有序
                if(nums[mid] <= target && target <= nums[right]) left = mid;
                else right = mid-1;
            }
        }
        return nums[left] == target ? left: -1;
    }

    /**
     * 二分，不用模板
     * */
    public static int search_3(int[] nums, int target) {
        int left = 0, right = nums.length-1;

        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] == target) return mid;

            if(nums[mid] >= nums[left]) {
                // left ... mid有序
                if(target >= nums[left] && target < nums[mid]) right = mid-1;
                else left = mid+1;

            } else {
                // mid ... right有序
                if(nums[mid] < target && target <= nums[right]) left = mid+1;
                else right = mid-1;
            }
        }
        return nums[left] == target ? left: -1;
    }
}
