package com.sarah.sort;

import java.util.Arrays;

/**
 * 归并排序
 * */
public class MergeSortTemplate {
    public static void main(String[] args) {
//        int[] nums = {0,-1,-3,2,6,3,2};
//
//        nums = MergeSortTemplate.findReversePair(nums);
//
//        for(int num: nums)
//            System.out.print(num+" ");
//        System.out.println();

        // 归并排序，测试逆序对
        int[] nums = {1,2,3,4};
        System.out.println(findReversePair(nums));
        System.out.println(findReversePair_2(nums));
    }

    /**
     * 方法一：递归，自顶向下
     * 划分左右数组，需要建立在copy数组上，不影响原数组nums
     * */
    public static int[] sort(int[] nums) {
        if(nums == null || nums.length < 2) return nums;

        // copy 整个数组
        int[] arr = Arrays.copyOfRange(nums, 0, nums.length);
        int mid = nums.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr,mid, nums.length);

        return merge(sort(left), sort(right));
    }


    /**
     * 创建额外空间，不影响原数组
     * */
    private static int[] merge(int[] arr1, int[] arr2) {
        int n1 = arr1.length;
        int n2 = arr2.length;

        int[] arr = new int[n1+n2];
        int i=0,j=0,k=0;

        while(i<n1 && j<n2) {
            if(arr1[i] <= arr2[j]) arr[k++] = arr1[i++];
            else arr[k++] = arr2[j++];
        }

        while(i<n1) arr[k++] = arr1[i++];
        while(j<n2) arr[k++] = arr2[j++];

        return arr;
    }


    /**
     * 方法二：自底向上, 两层循环
     * 外层循环seg代表每个group的size, for(seg=1;seg<len;seg*=2)
     * 内层循环start指向每个group的起点 for(start=0;start<len;start+=seg*2)
     * */
    public static int[] sort_2(int[] nums) {
        if(nums == null || nums.length == 0) return nums;
        int n = nums.length;

        // 创建一个copy
        int[] copy = Arrays.copyOfRange(nums, 0, nums.length);

        // 外层循环代表每个group的size
        for(int seg=1;seg<n;seg+=seg) {
            // 内层循环指向每个group的起点, 下一个start要加上两个seg合并的offset
            for(int start=0;start<n;start+=seg*2) {
                // low: arr1的起始位置 mid: arr2的起始位置
                // high: arr2的终止位置
                int low = start, mid = Math.min(start+seg, n), high = Math.min(start+seg+seg, n);
                int iStart = low, iEnd = mid-1;
                int jStart = mid, jEnd = high-1;
                int k = start;

                // 两个指针均没有走到末尾
                while(iStart <= iEnd && jStart <= jEnd) {
                    if(nums[iStart] <= nums[jStart]) copy[k++] = nums[iStart++];
                    else copy[k++] = nums[jStart++];
                }

                while(iStart <= iEnd) copy[k++] = nums[iStart++];
                while(jStart <= jEnd) copy[k++] = nums[jStart++];
            }

            // 将copy复制给原数组
            nums = Arrays.copyOfRange(copy, 0, nums.length);
        }
        return copy;
    }


    /**
     * 求逆序对个数, 归并排序
     * input = [3,1,2,5,0]
     * */
    public static int findReversePair(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;

        // copy origin nums
        int[] copy = Arrays.copyOfRange(nums, 0, n);
        int cnt = 0; // 逆序对个数

        // 归并排序，两层循环
        // 第一层循环seg, 代表group大小
        for(int seg = 1; seg < n; seg+=seg) {
            // 第二层循环，代表group中的每个起点
            for(int start = 0; start < n; start += seg * 2) {
                // 定义low, mid, high
                int low = start, mid = Math.min(start + seg, n), high = Math.min(start + seg*2, n);

                int start1 = low, end1 = mid-1;
                int start2 = mid, end2 = high-1;
                int k = start;
                // 开始合并
                while(start1 <= end1 && start2 <= end2) {
                    if(nums[start1] <= nums[start2]) copy[k++] = nums[start1++];
                    else {
                        // nums[start1] > nums[start2], 逆序对出现
                        cnt += end1 - start1 + 1;
                        copy[k++] = nums[start2++];
                    }
                }

                // 有剩余
                while(start1 <= end1) copy[k++] = nums[start1++];
                while(start2 <= end2) copy[k++] = nums[start2++];
            }
            // 物归原主
            nums = Arrays.copyOfRange(copy, 0, n);
        }
        return cnt;
    }

    /**
     * 暴力法
     * */
    public static int findReversePair_2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int cnt = 0;

        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(nums[i] > nums[j]) {
                    ++cnt;
                    //System.out.println("i="+i+" j="+j);
                }
            }
        }
        return cnt;
    }
}
