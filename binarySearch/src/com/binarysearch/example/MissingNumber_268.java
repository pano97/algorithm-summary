package com.binarysearch.example;

public class MissingNumber_268 {
    public static void main(String[] args) {
        int[] nums = {9,6,4,2,3,5,7,0,1};
        System.out.println(missingNumber(nums));
    }

    public static int missingNumber(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        int n = nums.length;

        int sum = (1+n)*n/2;

        for(int num: nums)
            sum -= num;

        return sum;
    }
}
