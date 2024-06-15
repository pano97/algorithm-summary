package com.binarysearch.example;

public class SingleElementInASortedArray_540 {
    public static void main(String[] args) {
        int[] nums = {3,3,7,7,10,11,11};
        System.out.println(singleNonDuplicate_2(nums));
    }

    /**
     * 方法一：位运算
     * 数组已经有序，数组长度在[1,10^5]
     * 数组内容在[0, 10^5]
     * */
    public static int singleNonDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return -1;

        int eor = nums[0];
        for(int i=1;i<nums.length;i++)
            eor ^= nums[i];

        return eor;
    }

    /**
     * 方法二：奇偶性质
     * */
    public static int singleNonDuplicate_2(int[] nums) {
        int n = nums.length;
        int left = 0, right = n-1;
        while(left < right) {
           int mid = left + right >> 1;
           int ls = mid - left;
           int rs = right - mid;


        }
        return nums[left];
    }
}
