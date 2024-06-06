package com.binarysearch.example;

public class LongestIncreasingSubsequence_300 {
    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,4};
        System.out.println(lengthOfLIS(nums));
        System.out.println(lengthOfLIS_2(nums));
    }
    /**
     * 方法一：动态规划 O(n^2) 19.59%
     * */
    // [0,1,0,3,2,3]
    public static int lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];

        int res = 1;

        dp[0] = 1;
        for(int i= 1;i<n;i++) {
            int pos = findPos(nums, dp, i);
            //System.out.println("i="+i+" nums[i]="+nums[i]+" pos:"+pos);
            dp[i] = pos != -1 ? dp[pos]+1:1;
            res = Math.max(res,dp[i]);
        }
//        for(int d:dp)
//            System.out.print(d+" ");
//        System.out.println();
        return res;
    }

    /**
     * 在nums[0..index-1]中找一个位置，使得在满足nums[k] < nums[index]条件下dp[k]最大
     * */
    private static int findPos(int[] nums, int[] dp, int index) {
        int pos = -1; int maxLen = -1;

        int target = nums[index];
        for(int i=0;i<index;i++) {
            if(nums[i] < target && dp[i] > maxLen) {
                pos = i;
                maxLen = dp[i];
            }
        }
        return pos;
    }


    /**
     * 方法二：LIS Problem, O(nlogn) 99.7%
     * dp[l]: 长度l的子序列结尾最小值
     * */

    public static int lengthOfLIS_2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n+1];

        dp[0] = Integer.MIN_VALUE;
        int len = 0;

        for(int i=0;i<n;i++) {
            if(nums[i] > dp[len]) {
                // 当前遍历到的元素大于dp[len], 直接在末尾追加
                // len的长度+1
                dp[++len] = nums[i];
            } else {
                // 在0...len中寻找插入位置
                // 为了统一，这里也考虑dp[len] == nums[i]的情况
                // 注意这里，得从1开始找，因为0是dummy位置
                //System.out.println("len="+len);
                int left = 1, right = len;
                while(left < right) {
                    int mid = left + right >> 1;
                    if(dp[mid] >= nums[i]) {  // 这里又误写成了nums[mid] >= nums[i]
                        // 右区间的左端点
                        right = mid;
                    } else
                        left = mid + 1;
                }
                // 此时的left一定指向待替换位置, len不变
                // 这里误写成了nums[left] = nums[i]
                dp[left] = nums[i];
            }

            // print dp
//            for(int j=0;j<dp.length;j++)
//                System.out.print(dp[j]+" ");
//            System.out.println();
        }
        return len;
    }

}
