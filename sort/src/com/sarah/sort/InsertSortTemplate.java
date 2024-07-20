package com.sarah.sort;

/**
 * Insert Sort.
 * 插入排序需要挪动数组, 设置两个指针，i和j.
 * i指向待排序位置，j的范围是[0..i-1]
 * */
public class InsertSortTemplate {
    public static void main(String[] args) {
        int[] nums = {-1};
        InsertSortTemplate.sort(nums);

        for(int i:nums) System.out.print(i+" ");
        System.out.println();
    }

    public static void sort(int[] nums) {
        if(nums == null || nums.length == 0) return;

        int n = nums.length;

        // i指向待排序位置, 从位置1开始查找，0位置只有一个元素，默认有序
        for(int i=1;i<n;i++) {
            // 从后往前找，找到第一个 <= nums[i]的元素
            int j = i-1;
            int target = nums[i];

            while(j>=0 && nums[j] > target) {
                nums[j+1] = nums[j];
                --j;
            }

            nums[j+1] = target;
        }
    }
}
