package com.binarysearch.example;

import java.util.*;

public class IntersectionOfTwoArraysII_350 {
    public static void main(String[] args) {
        int[] nums1 = {3,1,2,4,4,2,1};
        int[] nums2 = {3,3,1,1,2,2,4,6,7,8,4,4,4};
        nums1 = intersect(nums1, nums2);
        for(int i: nums1)
            System.out.print(i+" ");
        System.out.println();

        nums1 = intersect_3(nums1, nums2);
        for(int i: nums1)
            System.out.print(i+" ");
        System.out.println();
    }
    /**
     * Follow up:
     * 1. 如果数组已经有序
     * 2. 如果nums1的长度 < nums2的长度
     * 3. nums2在disk上，不能一次将所有的元素load
     * */

    /**
     * 方法二：排序+双指针  89.04%
     * 1. 将nums1和nums2排序
     * 比如排序完成后：
     * nums1 = 1,1,2,2,2,3,3,5
     * nums2 = 2,2,2,2,2,4,5
     * 2. 设置i,j两个指针，分别指向nums1和nums2的开头, 当i或者j超出数组，结束
     * nums1[i] == nums2[j], 加入dp数组
     * nums1[i] < nums2[j], 说明nums1[i]不在交集中, i++
     * nums1[i] > nums2[j], 说明nums2[j]不在交集中，j++
     * */
    public static int[] intersect_2(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;

        int[] dp = new int[n1+n2];
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i=0,j=0,k=0;
        while(i<n1 && j<n2) {
            if(nums1[i] == nums2[j]) {
                dp[k++] = nums1[i];
                i++;
                j++;
            }
            else if(nums1[i] < nums2[j]) i++;
            else j++;
        }

        return Arrays.copyOfRange(dp, 0, k);
    }


    /**
     * 方法一: HashMap O(n) 5.02%
     * */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();

        for(int num: nums1)
            map1.put(num, map1.getOrDefault(num, 0)+1);

        for(int num: nums2)
            map2.put(num, map2.getOrDefault(num, 0) + 1);

        map1 = getIntersectOfMap(map1, map2);
        // 遍历map
        List<Integer> res = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry: map1.entrySet()) {
            for(int i=0;i<entry.getValue();i++)
                res.add(entry.getKey());
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Map<Integer, Integer> getIntersectOfMap(Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        Map<Integer, Integer> map = new HashMap<>();

        for(Map.Entry<Integer, Integer> entry: map1.entrySet()) {
            int key  = entry.getKey();
            int val = entry.getValue();

            if(map2.containsKey(key)) {
                val = Math.min(val, map2.get(key)); // 取更小的val
                map.put(key, val);
            }
        }
        return map;
    }

    /**
     * 优化HashMap  63.68%
     * 1. 将两个map改为缩减成一个map
     * 2. 遍历长度更小的数组，将这个数组的元素加入到map中
     * 3. 在查找数组是否在map中时候，更新count的值，当count = 0, 将该key移除
     * */
    public static int[] intersect_3(int[] nums1, int[] nums2) {
        // 此处调换nums1, nums2参数位置，统一了处理逻辑，很妙
        if(nums1.length > nums2.length)
            return intersect_3(nums2, nums1);

        Map<Integer, Integer> map = new HashMap<>();
        // 遍历nums1, nums1长度小
        for(int num: nums1)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int[] res = new int[nums1.length];
        int k = 0;

        // 遍历nums2, 查询是否在nums1中
        for(int num: nums2) {
            int cnt = map.getOrDefault(num, 0);
            //System.out.println("num:"+num+" cnt:"+cnt);

            if(cnt > 0) {
                // 查询到了
                res[k++] = num;
                --cnt;
                map.put(num, cnt);  // 注意要把更新的值放回map
            }

            if(cnt == 0)
                map.remove(num);
        }
        return Arrays.copyOfRange(res, 0, k);
    }
}
