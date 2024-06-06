package com.binarysearch.example;

import java.util.ArrayList;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf_315 {
    public static void main(String[] args) {
        int[] nums = {3,2,0,1,5,7,8,1,3};
        List<Integer> res = countSmaller(nums);
        printList(res);

//        res = countSmaller_2(nums);
//        printList(res);
    }

    private static void printList(List<Integer> res) {
        for(int i:res)
            System.out.print(i+" ");
        System.out.println();
    }


    /**
     * 方法一：暴力法 O(n^2)
     * */
    public static List<Integer> countSmaller(int[] nums) {
        if(nums == null || nums.length == 0) return new ArrayList<>();
        int n = nums.length;
        List<Integer> res = new ArrayList<>();

        for(int i=0;i<nums.length;i++) {
            int cnt = findLessThanTargetCount(nums, i+1, n-1, nums[i]);
            res.add(cnt);
        }
        return res;
    }

    /**
     * O(n)
     * 在nums[start...end]中找nums[i] < target的元素数量
     * */
    private static int findLessThanTargetCount(int[] nums, int start, int end, int target) {
        int cnt = 0;
        for(int i=start;i<=end;i++) {
            if(nums[i] < target) ++cnt;
        }
        return cnt;
    }
}
