package com.binarysearch.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KDiffPairsInAnArray_532 {
    public static void main(String[] args) {
        int[] nums = {3,1,4,1,5};
        System.out.println(findPairs_3(nums, 1));
    }
    /**
     * 方法一：排序+二分 O(nlogn) 99.7%
     * 先排序
     * 对nums中的每个数遍历，固定第一个数
     * 二分查找第二个数
     * nums数组长度在[1,10^4]
     * 数据范围在[-10^7, 10^7]
     * 注意，需要unique
     * */
    public static int findPairs(int[] nums, int k) {
        int n = nums.length;
        // 排序
        Arrays.sort(nums);
        int res = 0;

        // 对数组中的每个数进行遍历
        for(int i=0;i<n-1;i++) {
            if(i!=0 && nums[i] == nums[i-1]) continue;
            // 二分查找的target
            int target = k + nums[i];

            // 二分查找的下标
            int left = i+1, right = n-1;

            while(left < right) {
                int mid = left + right >> 1;
                if(nums[mid] >= target) {
                    // 右边区间的左边端点
                    right = mid;
                } else
                    left = mid + 1;
            }

//            if(nums[left] == target) {
//                System.out.println("i="+i+" left="+left);
//            }

            res += nums[left] == target ? 1: 0;
        }

        return res;
    }

    /**
     * 方法二：哈希
     * 去重可以用一个hashset存储两个数中的其中一个数
     * 依次将nums加入hashmap中，如果map中有 nums[i]+k 或者nums[i]-k表面存在一种答案
     * */
    public static int findPairs_2(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> visit = new HashSet<>();

        for(int i=0;i<nums.length;i++) {
            if(visit.contains(nums[i]+k)) {
                set.add(nums[i]);
            }
            if(visit.contains(nums[i]-k)) {
                set.add(nums[i]-k);  // 每次将最小的值放进去
            }
            visit.add(nums[i]);
        }
        return set.size();
    }

    /**
     * 方法三: 排序+双指针
     * */
    public static int findPairs_3(int[] nums, int k) {
        // 排序
        Arrays.sort(nums);

        int n = nums.length;
        int res = 0;

        for(int i=0;i<n-1;i++) {
            if(i!=0 && nums[i] == nums[i-1]) continue;

            int j=i+1;
            // 当j到达末尾或者nums[j]-nums[i] >= k时跳出循环
            while(j<n && nums[j]-nums[i]<k)
                j++;

            if(j!=n && nums[j] == k+nums[i])
                res += 1;
        }
        return res;
    }
}
