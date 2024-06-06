package com.binarysearch.example;

public class CountCompleteTreeNodes_222 {
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
        TreeNode a1 = new TreeNode(1);
        TreeNode b1 = new TreeNode(2);
        TreeNode b2 = new TreeNode(3);
        TreeNode c1 = new TreeNode(4);
        TreeNode c2 = new TreeNode(5);
        TreeNode c3 = new TreeNode(6);
        TreeNode c4 = new TreeNode(7);
        a1.left = b1; a1.right = b2;
        b1.left = c1; b1.right = c2;
        b2.left = c3;
        //b2.right = c4;

        System.out.println(countNodes(a1));

    }

    /**
     * 方法一：递归 O(n)
     * */
    public static int countNodes_1(TreeNode root) {
        if(root == null) return 0;

        return countNodes_1(root.left) + countNodes_1(root.right) + 1;
    }

    /**
     * 方法二：判断当前节点为root的子树是否为满二叉树
     * 满二叉树节点计算 2^（h+1）- 1
     * 如果当前层数为h, 该层节点范围为[1, 2^h]
     * Q:如何判断满二叉树，左右子树高度相同
     * */

    public static int countNodes(TreeNode root) {
        if(root == null) return 0;

        int dep_l = 0, dep_r = 0;
        TreeNode p = root;

        while(p!=null) {
            p = p.left;
            dep_l++;
        }

        p = root;
        while(p!=null) {
            p = p.right;
            dep_r++;
        }
        System.out.println("depth="+dep_l);

        if(dep_l == dep_r) return (int)Math.pow(2, dep_l) - 1;

        else
            return countNodes(root.left) + countNodes(root.right) + 1;
    }

}
