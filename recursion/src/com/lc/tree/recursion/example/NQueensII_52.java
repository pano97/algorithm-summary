package com.lc.tree.recursion.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueensII_52 {
    public static void main(String[] args) {
        totalNQueens(4);
        System.out.println(res.size());

        for(int[] r: res) {
            for(int i=0;i<r.length;i++) {
                System.out.print(r[i]+" ");
            }
            System.out.println();
        }
    }

    static List<int[]> res = new ArrayList<>();

    public static int totalNQueens(int n) {
        int[] pos = new int[n];
        Arrays.fill(pos, -1);

        dfs(n, 0, pos);

        return res.size();
    }

    private static void dfs(int n, int i, int[] pos) {
        if(pos[n-1] != -1) {
            res.add(pos);
            return;
        }

        for(int j=0;j<n;j++) {
            boolean flag = true;

            for(int k=0;k<i;k++) {
                if(j == pos[k] || i+j == k+pos[k] || Math.abs(i-j) == Math.abs(k-pos[k])) {
                    flag = false;
                    break;
                }
            }

            // is a valid position
            if(flag) {
                pos[i] = j;
                dfs(n, i+1, pos);
                pos[i] = -1;
            }
        }
    }


}
