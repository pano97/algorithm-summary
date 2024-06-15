package com.binarysearch.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomPointInNonOverlappingRectangles_497 {
    /**
     * 42.86%
     * 有点坑的注意点：
     * 1. 包括边界上的点
     * 2. 所有的点必须等概率返回
     * */
    class Solution {
        // Rectangle类
        class Rec {
            int x1;
            int x2;
            int y1;
            int y2;
            int offset;

            public Rec() {}

            public Rec(int[] point) {
                x1 = point[0];
                x2 = point[2];
                y1 = point[1];
                y2 = point[3];
            }

        }

        List<Rec> list;
        int sum; // list中所有的rec一共有多少个点

        public Solution(int[][] rects) {
            list = new ArrayList<>(rects.length);

            for(int i=0;i<rects.length;i++) {
                // 判断这个rec是否符合要求
                //if(rects[i][2] - rects[i][0] > 1 && rects[i][3] - rects[i][1] > 1) {
                    Rec r = new Rec(rects[i]);
                    int count = (r.x2-r.x1+ 1) * (r.y2-r.y1+ 1); // 有多少个点
                    sum += count;

                    r.offset = sum - 1;
                    list.add(r);
                //}
            }
        }



        public int[] pick() {
            //随机生成一个点的index
            int index = ThreadLocalRandom.current().nextInt(0, sum);

            // 找到index这个点在哪个rec里，对list进行二分查找
            int left = 0, right = list.size() - 1;  // 表示list的index

            while(left < right) {
                int mid = left + right >> 1;
                if(list.get(mid).offset >= index) {
                    // 右区间的左端点
                    right = mid;
                } else
                    left = mid + 1;
            }
            Rec r = list.get(left);

            int x = ThreadLocalRandom.current().nextInt(r.x1, r.x2+1);
            int y = ThreadLocalRandom.current().nextInt(r.y1, r.y2+1);
            return new int[]{x,y};
        }
    }

    public static void main(String[] args) {


    }
}
