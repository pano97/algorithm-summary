package com.binarysearch.example.boundary;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FindRightInterval_436 {
    public static void main(String[] args) {
        int[][] intervals = {
                {3,4},
                {2,3},
                {1,2}
        };
        int[] res = findRightInterval(intervals);
        for(int r: res)
            System.out.print(r+" ");
        System.out.println();

        int[][] intervals_2 = {
                {3,4},
                {2,3},
                {1,2}
        };
        res = findRightInterval_2(intervals_2);
        for(int r: res)
            System.out.print(r+" ");
        System.out.println();

    }
    // intervals = [[3,4],[2,3],[1,2]]

    /**
     * 方法二：暴力法 O(n^n) 8.43%
     * 自己的右区间也可以是自身
     * */
    public static int[] findRightInterval_2(int[][] intervals) {
        int n = intervals.length;
        int[] res = new int[n];

        for(int i=0;i<n;i++) {
            int pos = -1, min = Integer.MAX_VALUE;
            int end_i = intervals[i][1];

            for(int j=0;j<n;j++) {
                //if(i==j) continue;
                int start_j = intervals[j][0];
                if(start_j >= end_i && start_j < min) {
                    min = start_j;
                    pos = j;
                }
            }

            res[i] = pos;
        }
        return res;
    }


    /**
     * 方法一：二分法 O(nlogn) 85%
     * 由于start_i是唯一的，可以用HashMap记录 (start, position)
     * 然后将intervals按照start升序排序
     * 对intervals二分，intervals[mid][0] >= start_i
     * ps:
     * 由于是对排序过后的数组进行遍历，改变了相对位置
     * 填充res[i]的时候，要去map中查询原来的位置进行填充
     * */
    public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length; // intervals长度不会为0

        // init map, record original index
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++)
            map.put(intervals[i][0], i);

        // sort interval
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 根据start升序排序
                return o1[0] - o2[0];
            }
        });

        int[] res = new int[n];

        // binary search
       for(int i=0;i<n;i++) {
           // 对每个interval都要查找right interval
           int target = intervals[i][1];

           int left = 0, right = n-1;
           while(left < right) {
               int mid = left + right >> 1;
               if(intervals[mid][0] >= target) {
                   // 右边区间的左端点
                   right = mid;
               } else
                   left = mid + 1;
           }

           int pos_i = map.get(intervals[i][0]);
           int pos_left = map.get(intervals[left][0]);
           res[pos_i] = intervals[left][0] >= target ? pos_left : -1;
       }
        return res;
    }
}

//TODO: 双指针怎么做

