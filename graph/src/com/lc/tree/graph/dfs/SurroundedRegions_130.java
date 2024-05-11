package com.lc.tree.graph.dfs;

import java.util.ArrayDeque;
import java.util.Deque;

class SurroundedRegions_130 {
    public static void main(String[] args) {
        char[][] board = {
//                {'X', 'X', 'X', 'X'},
//                {'X', 'O', 'O', 'X'},
//                {'X', 'X', 'O', 'X'},
//                {'X', 'O', 'X', 'X'}
                {'O', 'O', 'O'},
                {'O', 'O', 'O'},
                {'O', 'O', 'O'}
        };

        solve_2(board);

        for(int i=0;i< board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

    }

    /**
     * 方法一：从边界开始dfs
     * */
//    public static void solve(char[][] board) {
//        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
//        int m = board.length, n = board[0].length;
//
//        // dfs on four borders
//        for(int j=0;j<n;j++) {
//            if(board[0][j] == 'O') dfs(board, 0, j);
//            if(board[m-1][j] == 'O') dfs(board, m-1, j);
//        }
//
//        for(int i=0;i<m;i++) {
//            if(board[i][0] == 'O') dfs(board, i, 0);
//            if(board[i][n-1] == 'O') dfs(board, i, n-1);
//        }
//
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//                if(board[i][j] == '.') board[i][j] = 'O';
//                else if(board[i][j] == 'O') board[i][j] = 'X';
//            }
//        }
//    }
//
//    private static void dfs(char[][] board, int i, int j) {
//        if(!inArea(board, i, j)) return;
//        if(board[i][j] != 'O') return;
//
//        // set to visit
//        board[i][j] = '.';
//
//        dfs(board, i-1, j);
//        dfs(board, i+1, j);
//        dfs(board, i, j-1);
//        dfs(board, i, j+1);
//    }
//
//    private static boolean inArea(char[][] board, int i, int j) {
//        return i>=0 && i < board.length && j>=0 && j<board[0].length;
//    }

    /**
     * 方法二：bfs
     * */
    public static void solve_2(char[][] board) {
        if(board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;

        int m = board.length, n = board[0].length;

        Deque<int[]> queue = new ArrayDeque<>();

        // add boarder
        for(int j=0;j<n;j++) {
            if(board[0][j] == 'O') {
                queue.add(new int[]{0,j});
                board[0][j] = '.';  // set to visit
            }

            if(board[m-1][j] == 'O') {
                queue.add(new int[]{m-1,j});
                board[m-1][j] = '.';
            }
        }

        for(int i=1;i<m-1;i++) {
            if(board[i][0] == 'O') {
                queue.add(new int[]{i, 0});
                board[i][0] = '.';
            }
            if(board[i][n-1] == 'O') {
                queue.add(new int[]{i,n-1});
                board[i][n-1] = '.';
            }
        }

        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        while(!queue.isEmpty()) {
            int[] cell = queue.poll();
            for(int k=0;k<dirs.length;k++) {
                int ni = cell[0] + dirs[k][0];
                int nj = cell[1] + dirs[k][1];

                if(ni < 0 || ni > m-1 || nj <0 || nj > n-1 || board[ni][nj] != 'O') continue;

                board[ni][nj] = '.';
                queue.add(new int[]{ni,nj});
            }
        }

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(board[i][j] == '.') board[i][j] = 'O';
                else if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }
}
