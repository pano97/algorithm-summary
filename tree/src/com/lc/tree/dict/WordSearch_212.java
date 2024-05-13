package com.lc.tree.dict;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordSearch_212 {
    public static void main(String[] args) {
        char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
//        char[][] board = {
//                {'a','b'},
//                {'c','d'}
//        };
        String[] words = {"oath","pea","eat","rain","oathi","oathk",
                "oathf","oate","oathii","oathfi","oathfii"};
        List<String> res = findWords(board, words);

        for(String s: res)
            System.out.println(s);
        //System.out.println(res.size());

    }

    //static List<String> res = new ArrayList<>();
    static HashSet<String> set = new HashSet<>();

    public static List<String> findWords(char[][] board, String[] words) {
        Tier root = new Tier();

        for (String word : words) {
            root.insert(word);
        }

        int m = board.length, n = board[0].length;
        boolean[][] visited;

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                // search from every board position
                visited = new boolean[m][n];
                dfs(board, i, j, root, visited);
            }
        }

        return new ArrayList<>(set);
    }

    private static void dfs(char[][] board, int i, int j, Tier root, boolean[][] visited) {
        if(!inArea(board, i, j) || visited[i][j]) return;
        if(root.next[board[i][j] - 'a'] == null) return;

        root = root.next[board[i][j]-'a'];

        if(root.isEnd) {
            set.add(root.word);
            root.isEnd = false; // delete the word
            //return;  找到不能返回，继续往下找
        }

        visited[i][j] = true;

        dfs(board, i-1, j, root, visited);
        dfs(board, i+1, j, root, visited);
        dfs(board, i, j-1, root, visited);
        dfs(board, i, j+1, root, visited);

        visited[i][j] = false;
    }

    private static boolean inArea(char[][] board, int i, int j) {
        return i>=0 && i<board.length && j>=0 && j<board[0].length;
    }
}
