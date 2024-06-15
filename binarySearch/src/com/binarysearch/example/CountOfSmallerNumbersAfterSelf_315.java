package com.binarysearch.example;

import java.util.*;

public class CountOfSmallerNumbersAfterSelf_315 {
    public static void main(String[] args) {
        int[] nums = {3,2,0,1,5,7,8,1,3};
        List<Integer> res = countSmaller(nums);
        printList(res);

        res = countSmaller_2(nums);
        printList(res);
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

    /**
     * 方法二：TreeMap<Integer,Integer> 超时
     * 从右往左遍历nums
     * 1. map中存在nums[i]
     * 小于nums[i]的元素个数
     * 2. map中不存在nums[i]
     * 找到map中比nums[i]大的元素
     * 如果有比nums[i]大的元素next，返回小于next的元素个数
     * 如果没有比nums[i]大的元素，返回map中所有元素的个数
     *
     * 将nums[i]加入到tree中
     * */
    public static List<Integer> countSmaller_2(int[] nums) {
        int n = nums.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] res = new int[n];

        for(int i=n-1;i>=0;--i) {
            if(map.containsKey(nums[i])) {
                // tree中含有nums[i]这个节点
                Map tmp = map.headMap(nums[i]);  // 比nums[i]小的map
                res[i] = _countMapNodes(tmp);
            } else{
                // tree中没有nums[i]元素
                Integer next = map.higherKey(nums[i]);  // 找到第一个比nums[i]大的元素
                if(next == null)
                    // 没有比nums[i]大的元素，说明全部比nums[i]小
                    res[i] = _countMapNodes(map);
                else {
                    Map tmp = map.headMap(next);
                    res[i] = _countMapNodes(tmp);
                }

            }
            map.put(nums[i], map.getOrDefault(nums[i],0) + 1);  // 记录nums[i]出现的次数
        }

        List<Integer> rr = new ArrayList<>();
        for(int i=0;i<n;i++)
            rr.add(res[i]);
        return rr;
    }

    private static int _countMapNodes(Map<Integer, Integer> map) {
        if(map == null) return 0;
        int cnt = 0;
        for(Map.Entry<Integer, Integer> entry: map.entrySet())
            cnt += entry.getValue();
        return cnt;
    }
}
