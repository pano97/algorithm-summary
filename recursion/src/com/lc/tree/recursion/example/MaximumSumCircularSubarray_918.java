package com.lc.tree.recursion.example;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumSumCircularSubarray_918 {
    public static void main(String[] args) {
        int[] nums = {-3,-2,-3};
        //System.out.println(_maxSubarraySumCircularFromRange(nums, 0, nums.length-1));
        System.out.println(maxSubarraySumCircular_2(nums));
    }


    /**
     * Method 1: preprocessing leftMax[]
     * Fixed the j position
     * Find the max sub array in left side and max sub array in right side
     * */
    public static int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];  // 统计从0开始连续子序列的最大和

        int preSum = nums[0], maxSum = nums[0];

        leftMax[0] = nums[0];
        int leftSum = nums[0];

        for(int i=1;i<n;i++) {
            preSum = Math.max(preSum+nums[i], nums[i]);
            maxSum = Math.max(preSum, maxSum);

            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i-1], leftSum);
        }

        int rightSum = 0;
        for(int j=n-1;j>0;--j) {
            rightSum += nums[j];
            leftSum = leftMax[j-1];
            maxSum = Math.max(leftSum+rightSum, maxSum);
        }
        return maxSum;
    }


    /**
     * Method: 2
     * Find the max sub array in nums[0...j-1] and max sub array in nums[j..n] can be transferred into
     * find the min sub array of nums[i..j]
     * */
    public static int maxSubarraySumCircular_2(int[] nums) {
        int n = nums.length;

        int iSum = nums[0];
        int preMinSum = nums[0], preMaxSum=nums[0], maxSum = nums[0], minSum = nums[0];

        // calculate the nums[i...j] min and max sub array
        for(int i=1;i<n;i++) {
            iSum += nums[i];

            // case 1
            preMaxSum = Math.max(preMaxSum+nums[i], nums[i]);
            maxSum = Math.max(preMaxSum, maxSum);

            preMinSum = Math.min(preMinSum+nums[i], nums[i]);
            minSum = Math.min(preMinSum, minSum);
        }

        if(maxSum < 0) return maxSum;  // if array's elements are all negative
        return Math.max(maxSum, iSum - minSum);
    }


    /**
     * Method 3: TBD
     * Maintain a queue, which size is n.
     * Expand the nums array to 2*n
     * */
    public static int maxSubarraySumCircular_3(int[] nums) {
        return 0;
    }

    private static int _maxSubarraySumCircularFromRange(int[] nums, int l, int r) {
        if(l > r || nums == null || nums.length == 0) return 0;

        int preSum = nums[0], maxSum = Integer.MIN_VALUE;
        int start, end = l+1;

        while(end <= r) {
            if(preSum <= 0) {
                preSum = nums[end];
                start = end;
                end = start + 1;
            } else {
                preSum += nums[end];
                end++;
            }
            maxSum = Math.max(preSum, maxSum);
        }
        return maxSum;
    }

}
