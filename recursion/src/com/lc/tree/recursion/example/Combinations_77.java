package com.lc.tree.recursion.example;

import java.util.ArrayList;
import java.util.List;

public class Combinations_77 {
    public static void main(String[] args) {

    }

    /**
     * 回溯模板
     * */
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 1, new ArrayList<Integer>());
        return res;
    }

    private static void dfs(int n, int k, int index, List<Integer> tmp) {
        if(tmp.size() == k) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for(int i=index;i<=n;i++) {
            tmp.add(i);
            dfs(n, k, i+1, tmp);
            tmp.remove(tmp.size()-1);
        }
    }

}
