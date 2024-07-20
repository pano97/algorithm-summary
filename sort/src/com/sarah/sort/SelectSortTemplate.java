package com.sarah.sort;

/**
 * Select Sort，O(n^2)
 * */
public class SelectSortTemplate {
    public static void main(String[] args) {
        int[] nums = {-1,3,1,6,2,0,9};
        SelectSortTemplate.sort(nums);

        for(int i:nums) System.out.print(i+" ");
        System.out.println();
    }

    public static void sort(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int n = nums.length;

        for(int i=0;i<n;i++) {
            int minIndex = i;
            int min = nums[i];

            for(int j=i;j<n;j++) {
                if(nums[j] < min) {
                    min = nums[j];
                    minIndex = j;
                }
            }

            // swap i and min
            swap(nums, i, minIndex);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
