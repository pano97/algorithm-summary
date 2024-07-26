package com.sarah.sort;

/**
 * Shell sort
 * 先分组，再插入排序，三重循环
 * 第一层 gap, gap = len/2, 每次缩小一半
 * 第二层，遍历每个位置 i=gap
 * 第三层插入排序
 * */
public class ShellSortTemplate {
    public static void main(String[] args) {
        int[] nums = {3,1,2,5,0,-3};
        ShellSortTemplate.sort(nums);

        for(int n: nums)
            System.out.print(n+" ");
        System.out.println();
    }

    public static void sort(int[] nums) {
        if(nums == null || nums.length < 2) return;

        int n = nums.length;

        // 第一层循环，gap
        for(int gap = n/2; gap > 0; gap/=2) {
            // 第二层循环，每个可能的位置
            for(int i=gap;i<n;i++) {
                // 第三层，插入排序, i是待插入元素
                int target = nums[i];

                int j = i-gap;  // 起始元素指向i的前一个位置

                // 找到第一个nums[j] <= target的位置
                while(j >= 0 && nums[j] > target) {
                    nums[j+gap] = nums[j];  // 元素后移
                    j -= gap;
                }
                // 将j+gap的位置填充
                nums[j+gap] = target;
            }
        }
    }
}
