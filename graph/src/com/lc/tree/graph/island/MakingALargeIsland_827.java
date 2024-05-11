package com.lc.tree.graph.island;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MakingALargeIsland_827 {
    public static void main(String[] args) {
        int[][] grid = {
                {0,1,0,1,1},
                {1,1,1,0,0},
                {1,1,0,0,1},
                {0,1,0,0,1}
        };
//        int[][] grid = {
//                {1,1},
//                {1,1}
//        };
        //System.out.println(largestIsland(grid));
        System.out.println(largestIsland(grid));
    }

    /**
     * 方法二：
     * 计算每个岛屿的面积，用Map<Integer, Integer> 存储：index, area
     * 遍历每个ocean节点，计算相邻land的最大值
     * */
//    static int[][] idx;
//    static Map<Integer, Integer> map;
//    static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
//
//    public static int largestIsland(int[][] grid) {
//        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
//        int m = grid.length, n = grid[0].length;
//
//        idx = new int[m][n];
//        map = new HashMap<>();
//        boolean flag = false;
//
//        calculateIslandArea(grid);
////        System.out.println("Print map");
////        printMap(map);
//
////        System.out.println("print idx");
////        for(int i=0;i<m;i++) {
////            for(int j=0;j<n;j++)
////            {
////                System.out.print(idx[i][j]+" ");
////            }
////            System.out.println();
////        }
//
//        int res = 0;
//        Set<Integer> set;
//
//        // traverse ocean
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//
//                if(isOcean(grid, i, j)) {
//                    flag = true;
//                    //System.out.println("i="+i+" j="+j);
//                    int tmp = 0;
//                    set = new HashSet<>(5);  // store idx
//                    set.add(-1);
//
//                    for(int k=0;k<4;k++) {
//                        int id = getPositionIdx(grid, i+dir[k][0], j+dir[k][1]);
//
//                        //System.out.println("idx = "+id);
//
//                        if(!set.contains(id)) {
//                            tmp += map.get(id);
//                            set.add(id);
//                        }
//                    }
//                    res = Math.max(tmp+1, res);
//                }
//            }
//        }
//        return flag ? res: map.get(1);
//    }
//
//    /**
//     * return idx[i][j]
//     * If is not a valid position or not land, return -1
//     * */
//    private static int getPositionIdx(int[][] grid, int i, int j) {
//        if(!inArea(grid, i, j) || isOcean(grid, i,j)) return -1;
//        return idx[i][j];
//    }
//
//
//    /**
//     * Calculate each island's area. And maintain the distinct island area in a map.
//     * */
//    private static void calculateIslandArea(int[][] grid) {
//        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return;
//        int m = grid.length, n = grid[0].length;
//
//        int k = 1;
//
//        for(int i=0;i<m;i++) {
//            for(int j=0;j<n;j++) {
//                if(isIsland(grid, i,j)) {
//                    int area = dfs(grid, i, j, k);
//                    map.put(k, area);
//                    ++k;
//                }
//            }
//        }
//    }
//
//    /**
//     * return the area of each island.
//     * */
//    private static int dfs(int[][] grid, int i, int j, int k) {
//        if(!inArea(grid, i, j)) return 0;
//        if(isVisited(grid, i,j )) return 0;
//        if(isOcean(grid, i, j)) return 0;
//
//        // set to visit
//        setToVisited(grid, i, j);
//
//        // set index
//        idx[i][j] = k;
//
//        return 1 + dfs(grid, i-1, j, k) + dfs(grid, i+1,j, k) + dfs(grid, i, j-1, k) + dfs(grid, i, j+1, k);
//    }
//
//    private static boolean inArea(int[][] grid, int i, int j) {
//        return i>=0 && i<grid.length && j>=0 && j<grid[0].length;
//    }
//
//    private static boolean isIsland(int[][] grid, int i, int j) {
//        return grid[i][j] == 1;
//    }
//
//    private static boolean isOcean(int[][] grid, int i, int j) {
//        return grid[i][j] == 0;
//    }
//
//    private static boolean isVisited(int[][] grid, int i, int j) {
//        return grid[i][j] == 2;
//    }
//
//    private static void setToVisited(int[][] grid, int i, int j) {
//        grid[i][j] = 2;
//    }
//
//    private static void printMap(Map<Integer, Integer> map) {
//        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
//            int key = entry.getKey();
//            int val = entry.getValue();
//            //System.out.println("index="+key+" area="+val);
//        }
//    }

    /**
     * 方法一：暴力法，超时
     * */
    static boolean[][] visited;

    public static int largestIsland(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;

        int res = 0;
        boolean flag = false;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(isOcean(grid, i,j)) {
                    flag = true;
                    grid[i][j] = 1;
                    res = Math.max(calculateMaxIslandArea(grid), res);
                    grid[i][j]= 0;  // 还原
                }
            }
        }

        return flag ? res : calculateMaxIslandArea(grid);
    }

    /**
     * Calculate the max area of island.
     * */
    private static int calculateMaxIslandArea(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int maxArea = 0;
        visited = new boolean[m][n];

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(isIsland(grid, i,j))
                    maxArea = Math.max(maxArea, dfs(grid, i,j));
            }
        }
        return maxArea;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if(!inArea(grid, i, j)) return 0;
        if(isVisited(i,j)) return 0;
        if(isOcean(grid, i,j)) return 0;

        // set to visit
        setToVisit(i,j);

        return 1 + dfs(grid,i-1,j) + dfs(grid, i+1, j) + dfs(grid, i,j-1)+ dfs(grid, i,j+1);
    }

    private static boolean inArea(int[][] grid, int i, int j) {
        return i>=0 && i<grid.length && j>=0 && j<grid[0].length;
    }

    private static boolean isVisited(int i, int j) {
        return visited[i][j];
    }

    private static void setToVisit(int i, int j) {
        visited[i][j] = true;
    }

    private static boolean isIsland(int[][] grid, int i, int j) {
        return grid[i][j] == 1;
    }

    private static boolean isOcean(int[][] grid, int i, int j) {
        return grid[i][j] == 0;
    }

}
