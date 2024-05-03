package com.lc.graph.island;

class IslandPerimeter_463 {
    public static void main(String[] args) {
        int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        System.out.println(islandPerimeter(grid));
    }

    public static int islandPerimeter(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(isIsland(grid, i,j))
                    return dfs(grid, i, j);
            }
        }
        return 0;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if(!inArea(grid, i,j)) return 1;  // 超过边界
        if(isOcean(grid,i,j)) return 1;  // 是海洋格子
        if(isVisited(grid,i,j)) return 0; // 已经visit过

        setToVisited(grid, i, j);

        return dfs(grid, i-1, j) + dfs(grid, i+1, j)
                + dfs(grid, i,j-1) + dfs(grid, i, j+1);
    }

    private static boolean inArea(int[][] grid, int i, int j) {
        return i>=0 && i< grid.length && j>=0 && j< grid[0].length;
    }

    private static boolean isVisited(int[][] grid, int i, int j) {
        return grid[i][j] == 2;
    }

    private static void setToVisited(int[][] grid, int i, int j) {
        grid[i][j] = 2;
    }

    private static boolean isIsland(int[][] grid, int i, int j) {
        return grid[i][j] == 1;
    }

    private static boolean isOcean(int[][] grid, int i, int j) {
        return grid[i][j] == 0;
    }
}
