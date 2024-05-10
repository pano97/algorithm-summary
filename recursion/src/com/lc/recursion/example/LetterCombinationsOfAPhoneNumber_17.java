package com.lc.recursion.example;

import java.util.*;

public class LetterCombinationsOfAPhoneNumber_17 {
    public static void main(String[] args) {
        String digits = "";

        List<String> res = letterCombinations(digits);
        //System.out.println(res.size());
        for(String s: res)
            System.out.println(s);
    }

    static HashMap<Character, char[]> map;
    static HashSet<String> resSet;

    /**
     * BFS
     * */
    public static List<String> letterCombinations(String digits) {
        if(digits == null || digits.length() == 0) return new ArrayList<>();

        initializeMap();
        resSet = new HashSet<>();
        dfs(digits.toCharArray(), 0, new StringBuilder());

        List<String> res =new ArrayList<>(); //Creation of ArrayList
        res.addAll(resSet); //HashSet to ArrayList

        return res;
    }

    private static void initializeMap() {
        map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    private static void dfs(char[] digitsArray, int index, StringBuilder sb) {
        if(index >= digitsArray.length) {
            resSet.add(sb.toString());
            return;
        }

        char[] next = map.get(digitsArray[index]);

       for(int i=0;i<next.length;i++) {
           dfs(digitsArray, index+1, sb.append(next[i]));
           sb.deleteCharAt(sb.length()-1);
       }
    }
}
