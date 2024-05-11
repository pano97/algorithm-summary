package com.lc.tree.graph.island;

public class NumberOfIslands_200 {
    public static void main(String[] args) {
//        char[][] grid = {
//                {'1','1','1','1','0'},
//                {'1','1','0','1','0'},
//                {'1','1','0','0','0'},
//                {'0','0','0','0','0'}
//        };
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(numIslands(grid));
    }

    static int[][] dir = {{-1,0}, {0,1}, {1,0}, {0,-1}};  // define four directions
    static boolean[][] visited;
    static int cnt = 0;

    public static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        visited = new boolean[m][n];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(grid[i][j] == '1' && !isVisited(i,j)) {
                    dfs(grid, i, j);
                    ++cnt;
                }
            }
        }

        return cnt;
    }

    private static void dfs(char[][] grid, int i, int j) {
        if(!isValidPosition(grid, i, j) || !isIsland(grid, i,j) || isVisited(i,j)) return;

        visited[i][j] = true;

        for(int k=0;k<dir.length;k++) {
            int ni = i+dir[k][0];
            int nj = j+dir[k][1];
            dfs(grid, ni, nj);
        }

    }

    private static boolean isValidPosition(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        return i >=0 && i<m && j>=0 && j<n;
    }

    private static boolean isIsland(char[][] grid, int i, int j) {
        return grid[i][j] == '1';
    }

    private static boolean isVisited(int i, int j) {
        return visited[i][j];
    }
}
