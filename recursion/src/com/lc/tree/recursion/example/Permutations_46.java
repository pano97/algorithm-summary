package com.lc.tree.recursion.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations_46 {
    public static void main(String[] args) {
        int[] nums = {1};
        res = permute(nums);

        for(List list: res) {
            for(int i=0;i<list.size();i++) {
                System.out.print(list.get(i)+" ");
            }
            System.out.println();
        }
    }

    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);

        dfs(nums, 0, new ArrayList<>());

        return res;
    }

    private static void dfs(int[] nums, int index, List<Integer> tmp) {
        if(tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int i=index;i<nums.length;i++) {
            for(int j=i;j<nums.length;j++) {
                swap(nums, i, j);

                tmp.add(nums[i]);
                dfs(nums, i+1, tmp);

                tmp.remove(tmp.size()-1);
                swap(nums, i, j);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
