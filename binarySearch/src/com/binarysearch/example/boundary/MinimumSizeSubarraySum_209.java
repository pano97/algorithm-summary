package com.binarysearch.example.boundary;

public class MinimumSizeSubarraySum_209 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,1};
        System.out.println(minSubArrayLen(10,nums));
        System.out.println(minSubArrayLen_2(10,nums));
    }
    /**
     * Method 1: 双指针，O(N) 99.74%
     * */
    public static int minSubArrayLen(int target, int[] nums) {
        if(nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        // init dp
        for(int i = 1;i<nums.length;i++)
            dp[i] = dp[i-1] + nums[i];

        int res = Integer.MAX_VALUE;

        int start = 0, end = 0;
        while(start < nums.length && end < nums.length) {
            if(dp[end] - dp[start] + nums[start] >= target) {
                res = Math.min(res, end-start+1);
                ++start;
            } else
                ++end;
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 方法二：前缀和+二分 O(nlogn) 5.88%
     * */
    public static int minSubArrayLen_2(int target, int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n];
        int res = Integer.MAX_VALUE;

        // 构造preSum
        // preSum[i]表示 nums[0...i]的和
        preSum[0] = nums[0];
        for(int i=1;i<n;i++)
            preSum[i] = preSum[i-1]+nums[i];

//        for(int s:preSum)
//            System.out.print(s+" ");
//        System.out.println();

        // 对nums中的每个位置进行遍历，作为start
        for(int i=0;i<n;i++) {
            int t = target + preSum[i] - nums[i];

            // 对i之后的位置进行二分
            int left = i, right = n-1;
            while(left < right) {
                int mid = left + right >> 1;
                if(preSum[mid] >= t) {
                    // 右区间的左边界
                    right = mid;
                } else
                    left = mid + 1;
            }

            if(preSum[left] >= t)  // 找不到元素则不更新
                res = Math.min(res, left-i+1);
        }
        return res==Integer.MAX_VALUE ? 0: res;
    }
}
