package com.lc.tree.recursion.example;

public class MaximumSubarray_53 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray_2(nums));
    }


    /**
     * Method 1: Maintain preSum, analyze the possible scenario
     * */
    public static int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int preSum = nums[0];
        int maxSum = nums[0];

        int start = 0, end = 1;

        while(end < nums.length) {
            if(preSum <= 0) {
                preSum = nums[end];
                start = end;
                end = start+1;
            } else {
                preSum += nums[end];
                end++;
            }
            maxSum = Math.max(preSum, maxSum);
        }
        return maxSum;
    }


    static class Info {
        public int lSum;
        public int rSum;
        public int iSum;  // the sum of array in nums[l,r]
        public int mSum;  // maximum sub array in nums[l,r]

        public Info() {
            this.lSum = 0;
            this.rSum = 0;
            this.iSum = 0;
            this.mSum = 0;
        }

        public Info(int lSum, int rSum, int iSum, int mSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.iSum = iSum;
            this.mSum = mSum;
        }
    }

    public static int maxSubArray_2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        return calculate(nums, 0, nums.length-1).mSum;
    }

    private static Info calculate(int[] nums, int l, int r) {
        if(l > r) return null;

        // base case
        if(l == r) {
            return new Info(nums[l], nums[l], nums[l], nums[l]);
        }

        int mid = l+r >> 1;
        Info left = calculate(nums, l, mid);
        Info right = calculate(nums, mid+1, r);

        if(left == null) return right;
        if(right == null) return left;

        int lSum, rSum, iSum, mSum;
        iSum = left.iSum + right.iSum;

        lSum = Math.max(left.lSum, left.iSum+ right.lSum);
        rSum = Math.max(right.rSum, right.iSum + left.rSum);

        int mSum_1 = Math.max(left.mSum, right.mSum);
        int mSum_2 = Math.max(Math.max(left.rSum, right.lSum), left.rSum + right.lSum);
        mSum = Math.max(mSum_1, mSum_2);

        return new Info(lSum, rSum, iSum, mSum);
    }


}
