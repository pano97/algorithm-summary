package com.sarah.sort;

public class QuickSortTemplate {
    public static void main(String[] args) {
        int[] nums = {-1,3,1,6,2,0,9};
        QuickSortTemplate.sort(nums);

        for(int i:nums) System.out.print(i+" ");
        System.out.println();
    }

    /**
     * Quick Sort
     * */
    public static void sort(int[] nums) {
        if(nums == null || nums.length == 0) return;

        int n = nums.length;
        for(int k=0;k<n;k++) {
            int pivot = nums[k];

            int i = 0, j = n-1;
            while(i < j) {
                while(i< j && nums[j] >= pivot) --j;  // 这里的位置很重要，确保i<j
                while(i< j && nums[i] <= pivot) ++i;

                if(nums[i] > nums[j]) swap(nums, i, j);

                ++i; --j;
            }
            // set pivot position
            swap(nums, i, k);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
