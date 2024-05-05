package com.lc.graph.example;

import java.util.*;

public class WordLadder_127 {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        String[] words = {"hot","dot","dog","lot","log","cog"};
        List<String> wordList = new ArrayList<>(Arrays.asList(words));

        System.out.println(ladderLength(beginWord, endWord, wordList));
    }

    /**
     * BFS
     * */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Deque<String> queue = new LinkedList<>();
        HashMap<String, Integer> visitedMap = new HashMap<>();
        HashSet<String> wordSet = new HashSet<>();

        // construct word map
        for(String word: wordList)
            wordSet.add(word);

        queue.offer(beginWord);
        visitedMap.put(beginWord, 1);

        while(!queue.isEmpty()) {
            String curWordStr = queue.pollFirst();
            //System.out.println("curWordStr:"+curWordStr);

            char[] curWordArray = curWordStr.toCharArray();

            for(int i=0;i<curWordArray.length;i++) {
                for(int k=0;k<26;k++) {
                    char[] changedArray = Arrays.copyOf(curWordArray, curWordArray.length); // make a copy
                    changedArray[i] =(char) ('a' + k);
                    String changedStr = String.valueOf(changedArray);

                    if(!wordSet.contains(changedStr)) continue;

                    if(!visitedMap.containsKey(changedStr)) {
                        visitedMap.put(changedStr, visitedMap.get(curWordStr) + 1);
                        queue.offer(changedStr);
                    }

                    if(changedStr.equals(endWord)) return visitedMap.get(changedStr);
                }
            }
        }
        return 0;
    }
}
