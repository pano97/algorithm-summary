package com.lc.tree.recursion.example;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum_39 {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        int target = 7;

        res = combinationSum(candidates, target);

        for(List list: res) {
            for(int i=0;i<list.size();i++) {
                System.out.print(list.get(i)+" ");
            }
            System.out.println();
        }
    }

    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);  // candidates are positive numbers

        dfs(candidates, 0, target, new ArrayList<>());

        return res;
    }

    private static void dfs(int[] candidate, int index, int target, List<Integer> tmp) {
        if(target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int i=index;i<candidate.length;i++) {
            if(candidate[i] > target) break;

            tmp.add(candidate[i]);
            dfs(candidate, i, target-candidate[i], tmp);
            tmp.remove(tmp.size()-1);
        }
    }
}
