package com.sarah.sort;

/**
 * Bubble Sort, O(n^2)
 * */
public class BubbleSortTemplate {

    public static void main(String[] args) {
        int[] nums = {-1,3,1,6,2,0,9};
        BubbleSortTemplate.sort(nums);

        for(int i:nums) System.out.print(i+" ");
        System.out.println();
    }

    public static void sort(int[] nums) {
        if(nums == null || nums.length == 0) return;

        int n = nums.length;

        for(int i=0;i<n;i++) {
            for(int j=i;j<n;j++) {
                if(nums[i] > nums[j]) swap(nums, i, j);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
