package com.binarysearch.example;

public class HIndexII_275 {
    public static void main(String[] args) {
        int[] nums = {2};
        System.out.println(hIndex_4(nums));
    }
    /**
     * 方法一：暴力法 O(nlogn) 3.54%
     * */
    /*public static int hIndex(int[] citations) {
       int n = citations.length;

       for(int h=n;h>=0;--h) {
           // 找到>=target的位置
           int pos = findNoLessThanTarget(citations, h);
           if(pos!=-1) {
               int cnt = n-pos;
               if(cnt >= h) return h;
           }
       }
       return -1;
    }

    // 不小于target的位置
    private static int findNoLessThanTarget(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(nums[mid] >= target) {
                // 右区间
                right = mid;
            } else
                left = mid + 1;
        }
        return nums[left] >= target ? left: -1;
    }*/

    /**
     * 方法二：O(n) 17.09%
     * */
    public static int hIndex_2(int[] citations) {
        int n = citations.length;

        int[] dp = new int[n];

        for(int i=0;i<n;i++)
            dp[i] = n-i;

        // 二分查找
        int left = 0, right = n-1;
        while(left < right) {
            int mid = left + right >> 1;
            if(citations[mid] >=dp[mid]) {
                // 右区间的左端点
                right = mid;
            } else
                left = mid + 1;
        }

        return Math.min(dp[left], citations[left]);
    }

    /**
     * 方法三：优化，从后向前遍历，更新可能的h
     * O(n) 100%
     * */
    public static int hIndex_3(int[] citations) {
        int n = citations.length;
        int res = 0; // corner case [0]

        for(int i=n-1;i>=0;--i) {
            if(citations[i] >= n-i) {
                res = n-i; // update h
            } else
                break;
        }

        return res;
    }

    /**
     * 方法四：改二分 O(logn) 100%
     * 搜索区间 [0, n]
     * */
    public static int hIndex_4(int[] citations) {
        int n = citations.length;

        int left = 0, right = n;

        while(left< right) {
            int mid = left + right >> 1;
            if(citations[mid] >= n-mid) {
                // 右边区间
                right = mid;
            } else
                left = mid + 1;
        }

        return n-left;
    }

}
