package com.sarah.slidingwindow.example;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 滑动窗口
 * */
public class ContainsDuplicateIII_220 {
    public static void main(String[] args) {
        int[] nums = {-1,2};
        int indexDiff = 2, valueDiff = 3;
        //System.out.println(containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff));
        System.out.println(containsNearbyAlmostDuplicate_2(nums, indexDiff, valueDiff));
    }

    /**
     * 方法一：TreeMap 56.8%
     * abs(i-j) <= k
     * abs(nums[i] - nums[j]) <= t
     * 维护一个大小为k的滑动窗口，假设当前遍历到的元素是x, 检查当前窗口中是否存在 [x-t, x+t]的元素
     * 用TreeMap存储滑动窗口内的元素，可以用ceiling()获取 >= x-t的元素
     *
     * nums长度[2,10^5]
     * nums的元素范围[-10^9, 10^9]
     * t的范围10^9，x-t可能溢出
     * */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        if(nums == null || nums.length == 0) return false;
        // TreeMap存储滑动窗口元素
        int k = indexDiff, t = valueDiff, n = nums.length;

        TreeSet<Long> set = new TreeSet<>();

        for(int i=0;i<n;i++) {
            Long lowerBound = (long) nums[i] - t;  // 下界
            Long upperBound = (long) nums[i] + t;  // 上界
            Long ceiling = set.ceiling(lowerBound); // >= nums[i] - t 的元素

            if(ceiling != null && ceiling <= upperBound) return true;

            // 滑动窗口达到k，移除元素
            if(set.size() == k) set.remove((long) nums[i-k]);

            // 加入当前元素
            set.add((long) nums[i]);
        }
        return false;
    }

    /**
     * 方法二：桶排序 94.23%
     * 当遍历到x元素，检查当前滑动窗口是否含有[x-t,x+t]内的元素
     * 用HashMap存储滑动窗口桶编号，桶元素
     * indexDiff = k
     * valueDiff = t
     * 桶大小设置为t+1，这样当元素落入同一个桶中，差值一定在[0,t]，满足条件
     * 任意一个元素x落入哪个桶？ x % (t+1)
     * 因为x可能为负数，将x减去最小值
     * */
    public static boolean containsNearbyAlmostDuplicate_2(int[] nums, int indexDiff, int valueDiff) {
        if(nums == null || nums.length == 0) return false;

        int n = nums.length, k = indexDiff, t = valueDiff;

        // 桶编号，桶内元素
        Map<Long, Long> bucket = new HashMap<>();

        for(int i=0;i<n;i++) {
            // 这一步，将负数也映射到正数上
            long mappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long id = mappedNum / (t+1);

            if(bucket.containsKey(id) ||
                    (bucket.containsKey(id-1) && Math.abs(bucket.get(id-1) - mappedNum) <= (long)t) ||
                    (bucket.containsKey(id+1) && Math.abs(bucket.get(id+1) - mappedNum) <= (long)t)) return true;

            // 满了，移除元素
            if(bucket.size() >= k) {
                long removedNum = ((long) nums[i-k] - Integer.MIN_VALUE);
                long removedId = removedNum / (t+1);
                bucket.remove(removedId);
            }

            // 将新元素加入桶中
            bucket.put(id, mappedNum);
        }
        return false;
    }
}
