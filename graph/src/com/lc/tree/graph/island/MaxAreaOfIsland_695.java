package com.lc.tree.graph.island;

public class MaxAreaOfIsland_695 {
    public static void main(String[] args) {
        int[][] grid = {
                {0,0,1,0,0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,1,1,0,1,0,0,0,0,0,0,0,0},
                {0,1,0,0,1,1,0,0,1,0,1,0,0},
                {0,1,0,0,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        System.out.println(maxAreaOfIsland(grid));
    }

    static int maxArea = 0;

    public static int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(isIsland(grid,i,j)) {
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }
        return maxArea;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if(!inArea(grid, i,j)) return 0;
        if(isVisited(grid,i,j)) return 0;
        if(isOcean(grid, i,j)) return 0;

        //if(isIsland(grid,i,j)) return 1;

        // set to visit
        setToVisited(grid, i, j);

        return 1+ dfs(grid,i-1,j)+dfs(grid,i+1,j) + dfs(grid,i,j-1)+dfs(grid,i,j+1);
    }

    private static boolean isIsland(int[][] grid, int i, int j) {
        return grid[i][j] == 1;
    }

    private static boolean isOcean(int[][] grid, int i, int j) {
        return grid[i][j] == 0;
    }

    private static boolean isVisited(int[][] grid, int i, int j) {
        return grid[i][j] == 2;
    }

    private static void setToVisited(int[][] grid, int i, int j) {
        grid[i][j] = 2;
    }

    private static boolean inArea(int[][] grid, int i, int j) {
        return i>=0 && i<grid.length && j >=0 && j<grid[0].length;
    }
}
