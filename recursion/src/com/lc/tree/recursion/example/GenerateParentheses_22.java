package com.lc.tree.recursion.example;

import java.util.*;

public class GenerateParentheses_22 {
    public static void main(String[] args) {
        int n = 2;
        res = generateParenthesis(n);
        for(String s: res)
            System.out.println(s);
    }

    /**
     * 方法二：优化dfs
     * */
    static List<String> res;
    static HashSet<String> visited;
    static int remainLeft;
    static int remainRight;

    public static List<String> generateParenthesis(int n) {
        remainLeft = n;
        remainRight = n;
        res = new ArrayList<>();
        visited = new HashSet<>();

        char[] arr = new char[n*2];

        dfs(arr, 0);

        return res;
    }

    private static void dfs(char[] arr, int index) {
        if(index == arr.length) {
            String str = String.valueOf(arr);
            if(!visited.contains(str) && isValidExpression(arr, 0, arr.length-1)) {
                visited.add(str);
                res.add(str);
            }
            return;
        }

        if(remainLeft > remainRight) return;

        if(remainLeft > 0) {
            remainLeft--;
            arr[index] = '(';
            dfs(arr, index+1);

            arr[index] = ' ';
            remainLeft++;
        }

        if(remainRight > 0) {
            remainRight--;
            arr[index] = ')';

            dfs(arr, index+1);
            arr[index] = ' ';
            remainRight++;
        }
    }

    private static boolean isValidExpression(char[] arr, int start, int end) {
        if(arr == null) return true;

        Stack<Character> stack = new Stack<>();

        for(int i=start;i<=end;i++) {
            if(arr[i] == '(') {
                stack.push(arr[i]);
            } else {
                // meet )
                if(stack.isEmpty()) return false;

                char tp = stack.peek();
                if(tp != '(') return false;

                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    /**
     * 方法一：暴力法，超时
     * */

//    static List<String> res = new LinkedList<>();
//    static HashSet<String> set = new HashSet<>();  // remove duplicates
//
//    public static List<String> generateParenthesis(int n) {
//        char[] strArray = generateCharArray(n);
//        dfs(strArray, 0, new ArrayList<>());
//
//        return res;
//    }
//
//    private static void dfs(char[] arr, int index, List<Character> tmp) {
//        if(tmp.size() == arr.length) {
//            StringBuilder sb = new StringBuilder();
//            for(char ch: tmp) sb.append(ch);
//            String str = sb.toString();
//
//            if(!set.contains(str) && isValidExpression(arr, 0, arr.length-1)) {
//                //System.out.println(str);
//                set.add(str);
//                res.add(str);
//            }
//            return;
//        }
//
//        for(int i=index;i<arr.length;i++) {
//            for(int j=i;j< arr.length;j++) {
//
//                swap(arr, i, j);
//
//                tmp.add(arr[i]);
//                dfs(arr, i+1, tmp);
//                tmp.remove(tmp.size()-1);
//
//                swap(arr, i, j);
//            }
//        }
//    }
//
//    private static void swap(char[] arr, int i, int j) {
//        char tmp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = tmp;
//    }
//
//    private static char[] generateCharArray(int n) {
//        char[] array = new char[n*2];
//
//        for(int i=0;i<2*n;i++) {
//            array[i] = i%2==0? '(':')';
//        }
//        return array;
//    }
//
//    /**
//     * Check arr is valid or not.
//     * */
//    private static boolean isValidExpression(char[] arr, int start, int end) {
//        if(arr == null) return true;
//
//        Stack<Character> stack = new Stack<>();
//
//        for(int i=start;i<=end;i++) {
//            if(arr[i] == '(') {
//                stack.push(arr[i]);
//            } else {
//                // meet )
//                if(stack.isEmpty()) return false;
//
//                char tp = stack.peek();
//                if(tp != '(') return false;
//
//                stack.pop();
//            }
//        }
//        return stack.isEmpty();
//    }
}
