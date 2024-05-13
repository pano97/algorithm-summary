package com.lc.tree.example;


import com.lc.tree.template.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ConvertSortedArrayToBinarySearchTree_108 {

    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
 }

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        TreeNode root = sortedArrayToBST(nums);
        inOrder_iter(root);
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        return sortTree(nums, 0, nums.length-1);
    }

    private static TreeNode sortTree(int[] nums, int left, int right) {

        if(left > right) return null;

        int mid = (left + right) / 2;

        TreeNode ll = sortTree(nums, left, mid-1);
        TreeNode rr = sortTree(nums, mid+1, right);

        TreeNode root = new TreeNode(nums[mid]);
        root.left = ll;
        root.right = rr;

        return root;

    }

    public static void inOrder_iter(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while(p!=null || !stack.isEmpty()) {
            if(p!=null) {
                stack.push(p);
                p = p.left;
            }
            else {
                p = stack.pop();
                // do some thing...
                System.out.print(p.val+" ");
                p = p.right;
            }
        }
    }
}
