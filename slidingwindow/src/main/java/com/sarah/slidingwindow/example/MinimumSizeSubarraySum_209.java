package com.sarah.slidingwindow.example;

/**
 * 滑动窗口 重点，总是有越界问题
 * 给一个nums数组，和一个target，返回子数组sum>=target的最小值
 * */
public class MinimumSizeSubarraySum_209 {
    public static void main(String[] args) {
        int[] nums = {1,1,1};
        System.out.println(minSubArrayLen(7, nums));
    }

    /**
     * 方法一：滑动窗口
     * 数组长度[1,10^5]
     * 1.开始时，设置start和end均为0
     * 2.当前的和 < target, end后移，相当于再纳入一个数字凑数
     * 3.当前的和 >= target, 在while循环中更新res, start前移动，相当于尝试抛掉一个元素
     * */
    public static int minSubArrayLen(int target, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;

        int preSum = 0;
        int start = 0, end = 0;
        int res = Integer.MAX_VALUE;

        while(end < n) {
            preSum += nums[end]; // end指向当前位置

            while(preSum >= target) {
                res = Math.min(res, end-start+1);
                preSum -= nums[start];
                ++start;
            }

            ++end;
        }

        return res == Integer.MAX_VALUE ? 0: res;
    }

}
