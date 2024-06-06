package com.binarysearch.example;

import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoSortedArrays_4 {
    public static void main(String[] args) {
        int[] nums1 = {3};
        int[] nums2 = {-2,-1};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    // [1,2] + [3,4] => [1,2,3,4]
    // res = 2 + 3 >> 1
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] shot = m <= n ? nums1 : nums2;
        int[] lon = m <= n ? nums2 : nums1;

        List<Integer> longlist = new ArrayList<>();
        if(nums1[m-1] < nums2[0]) {
            for(int num: nums1)
                longlist.add(num);
            for(int num:nums2)
                longlist.add(num);
        }
        else if(nums1[0] > nums2[n-1]) {
            for(int num: nums2)
                longlist.add(num);
            for(int num:nums1)
                longlist.add(num);
        } else {
            for(int num: lon)
                longlist.add(num);
        }

        // insert short array to long array
        for(int i=0;i<shot.length && longlist.size()!=m+n;i++) {
            int left = 0, right = longlist.size()-1;

            while(left < right) {
                int mid = left + right >> 1;
                if(longlist.get(mid) >= shot[i]) {
                    // 右区间
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            System.out.println("pos:"+left);
            longlist.add(left, shot[i]);
        }

        // print list
        for(int t:longlist)
            System.out.print(t+" ");
        System.out.println();

        int pos = (m+n) / 2;
        if((m+n)%2==1) return longlist.get(pos);
        return (double)(longlist.get(pos)+longlist.get(pos-1))/2;
    }

}
