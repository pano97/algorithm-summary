package com.lc.graph.example;

import java.util.*;

public class WordSearchII_212 {
    public static void main(String[] args) {
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        res = findWords(board, words);
        for(String r: res)
            System.out.print(r+" ");
        System.out.println();
    }

    static List<String> res;
    static HashSet<String> wordSet;
    static boolean[][] visited;
    static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};

//    private static void bfs(char[][] board, int i, int j) {
//        Deque<String> queue = new LinkedList<>();
//        String p = String.valueOf(board[i][i]);
//
//        queue.offer(p);
//        visited[i][j] = true;
//
//        while(!queue.isEmpty()) {
//            StringBuilder sb = new StringBuilder(queue.pollFirst());
//            //System.out.println(sb);
//            for(int k=0;k<dirs.length;k++) {
//                int ni = i+dirs[k][0];
//                int nj = j+dirs[k][1];
//                if(ni<0 || ni>board.length-1 || nj<0 || nj>board[0].length-1 || visited[ni][nj]) continue;
//
//                sb.append(board[ni][nj]);
//                visited[ni][nj] = true;
//                String nstr = sb.toString();
//                queue.offer(nstr);
//
//                if(wordSet.contains(nstr)) {
//                    res.add(nstr);
//                    System.out.println("nstr:"+nstr);
//                }
//            }
//        }
//    }

    private static void dfs(char[][] board, int i, int j, StringBuilder sb) {
        if(!inArea(board,i,j)) {
            return;
        }
        if(visited[i][j]) {
            return;
        }

        sb.append(board[i][j]);
        visited[i][j] = true;
        System.out.println(sb);

        if(wordSet.contains(sb.toString())) res.add(sb.toString());
        dfs(board, i-1, j, sb);
        dfs(board, i+1, j, sb);
        dfs(board, i, j-1, sb);
        dfs(board, i, j+1, sb);
    }

    private static boolean inArea(char[][] board, int i, int j) {
        return i>=0 && i<board.length && j>=0 && j<board[0].length;
    }

    public static List<String> findWords(char[][] board, String[] words) {
        res = new LinkedList<>();
        wordSet = new HashSet<>();

        //boolean[] isStartLetter = new boolean[26];

        for(String s: words) {
            wordSet.add(s);
            char ch = s.charAt(0);
            //isStartLetter[ch-'a'] = true;
        }

        // construct word set
        Collections.addAll(wordSet, words);

//        for(int i=0;i<board.length;i++) {
//            for(int j=0;j<board[0].length;j++) {
//                char ch = board[i][j];
////                if(isStartLetter[ch-'a']) {
//                    visited = new boolean[board.length][board[0].length];
//                    dfs(board, i, j, new StringBuilder());
////                }
//            }
//        }
        visited = new boolean[board.length][board[0].length];
        dfs(board,0,0,new StringBuilder());
        return res;
    }
}
