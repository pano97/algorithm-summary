package com.sarah.slidingwindow.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ContainsDuplicateII_219 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1,2,3};
        System.out.println(containsNearbyDuplicate(nums, 2));
        System.out.println(containsNearbyDuplicate_2(nums,2));
    }

    /**
     * 方法一: HashMap 83% O(n)
     * nums长度[1,10^5]
     * nums的内容[-10^9, 10^9]
     *
     * Test case:
     * nums = [1,1] k=0
     * nums = [1,0,1] k=1
     * nums = [1] k=1
     * nums = [1,4,100,5,1] k=4
     *
     * Step:
     * 1. 遍历nums数组，map中不存在则直接放入该元素和下标
     * 2. 存在该元素，更新距离，将新的下标放入
     * */

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0;i<nums.length;i++) {
            if(!map.containsKey(nums[i])) map.put(nums[i], i);
            else {
                int pos = map.get(nums[i]);
                if(Math.abs(pos-i) <= k) return true;
                map.put(nums[i], i);
            }
        }
        return false;
    }

    /**
     * 方法二：滑动窗口
     * */
    public static boolean containsNearbyDuplicate_2(int[] nums, int k) {
        int i=0,j=0;
        Set<Integer> set = new HashSet<>();
        while(j<nums.length) {
            if(j-i <= k) {
                if(set.contains(nums[j])) return true;
                set.add(nums[j]);
                j++;
            }
            else {
                set.remove(nums[i]);
                i++;
                //j++;
            }
        }
        return false;
    }


}
