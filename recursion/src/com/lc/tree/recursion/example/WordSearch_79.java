package com.lc.tree.recursion.example;

public class WordSearch_79 {
    public static void main(String[] args) {
        char[][] board = {
//                {'A','B','C','E'},
//                {'S','F','C','S'},
//                {'A','D','E','E'}
                {'a'}
        };
        String word = "a";
        System.out.println(exist(board, word));
    }

    static int[][] dirs = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };
    static boolean[][] visited;
    static boolean flag = false;


    public static boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];

        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++) {
                if(word.charAt(0) == board[i][j]) {
                    dfs(board, i, j, word, new StringBuilder());
                }
            }
        }
        return flag;
    }

    private static void dfs(char[][] board, int i, int j, String target, StringBuilder sb) {
        if(sb.length() == target.length()) {
            if(sb.toString().equals(target))
                flag = true;
            return;
        }

        if(!inArea(board, i, j) || visited[i][j]) return;

        sb.append(board[i][j]);
        visited[i][j] = true;
        //System.out.println(sb);

        for(int k=0;k<dirs.length;k++) {
            int ni = i+dirs[k][0];
            int nj = j+dirs[k][1];
            dfs(board, ni, nj, target, sb);
            if(flag) return;
        }

        visited[i][j] = false;
        sb.deleteCharAt(sb.length()-1);
    }

    private static boolean inArea(char[][] board, int i, int j) {
        return i>-1 && i<board.length && j>-1 && j< board[0].length;
    }
}
