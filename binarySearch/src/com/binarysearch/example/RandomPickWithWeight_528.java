package com.binarysearch.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPickWithWeight_528 {
    /**
     * 前缀和+二分查找 84.52%
     * */
    class Solution {
        int[] pre;
        int total;
        public Solution(int[] w) {
            pre = new int[w.length];

            pre[0] = w[0];
            for(int i=1;i<w.length;i++)
                pre[i] = pre[i-1] + w[i];

            total = Arrays.stream(w).sum();
        }

        public int pickIndex() {
            // 生成一个[1, total]的随机数
            int num = (int) (Math.random() * total) + 1;

            // left和right代表下标
            int left = 0, right = pre.length-1;

            while(left < right) {
                int mid = left + right >> 1;
                if(pre[mid] >= num) {
                    //右区间
                    right = mid;
                } else
                    left = mid + 1;
            }
            return left;
        }
    }

    public static void main(String[] args) {

    }
}
