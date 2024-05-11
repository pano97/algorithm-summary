package com.lc.binary;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * TreeNode template, contains:
 * - pre order traverse (recursion / non-recursion)
 * - in order traverse (recursion / non-recursion)
 * - post order traverse (recursion / non-recursion)
 * - level order traverse
 * */
public class TreeTemplate {
    public static void main(String[] args) {
        TreeNode a1 = new TreeNode(3);
        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(2);
        TreeNode c1 = new TreeNode(5);
        TreeNode c2 = new TreeNode(4);
        TreeNode c3 = new TreeNode(7);
        a1.left = b1; a1.right = b2;
        b1.left = c1; b2.left = c2; b2.right = c3;

        // test pre order: 3 1 5 2 4 7
        System.out.println("========TEST START=======");
        preOrder_iter(a1);
        System.out.println();
        preOrder_recur(a1);
        System.out.println();
        System.out.println("=========================");

        // test in order: 5 1 3 4 2 7
        inOrder_recur(a1);
        System.out.println();
        inOrder_iter(a1);
        System.out.println();
        System.out.println("=========================");

        // test post order: 5 1 4 7 2 3
        postOrder_iter(a1);
        System.out.println();
        postOrder_recur(a1);
        System.out.println();
        System.out.println("=========================");

        // test level order: 3 1 2 5 4 7
        levelOrder(a1);
        System.out.println();
        System.out.println("========TEST END=========");
    }

    public static void preOrder_recur(TreeNode root) {
        if(root == null) return;
        System.out.print(root.val+" ");
        preOrder_recur(root.left);
        preOrder_recur(root.right);
    }

    public static void inOrder_recur(TreeNode root) {
        if(root == null) return;
        inOrder_recur(root.left);
        System.out.print(root.val + " ");
        inOrder_recur(root.right);
    }

    public static void postOrder_recur(TreeNode root) {
        if(root == null) return;
        postOrder_recur(root.left);
        postOrder_recur(root.right);
        System.out.print(root.val+" ");
    }

    /**
     * Pre order for iteration method one.
     * Add the head first.
     * while stack not empty, do the loop:
     * add the right child
     * add the left child.
     * */
    public static void preOrder_iter(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        TreeNode p;

        while(!stack.isEmpty()) {
            p = stack.pop();
            // do some thing...
            System.out.print(p.val+" ");
            if(p.right != null) stack.push(p.right);
            if(p.left != null) stack.push(p.left);
        }
    }


    /**
     * In order for iteration.
     * The while condition is when p not null or stack not empty.
     * Always go ahead along the left side.
     * if can't go ahead, pop the node, and change to right.
     * */
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

    /**
     * Prepare two stack.
     * Add root node first.
     * Pop the node from stack one, and add the popped node to stack two.
     * Add left node.
     * Add right node.
     * Pop the stack two, the order is post order traverse.
     * */
    public static void postOrder_iter(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> aux = new Stack<>();

        stack.push(root);
        TreeNode p;

        while(!stack.isEmpty()) {
            p = stack.pop();
            // add to the aux stack, do nothing
            aux.push(p);

            if(p.left != null) stack.push(p.left);
            if(p.right != null) stack.push(p.right);
        }

        while(!aux.isEmpty()) {
            p = aux.pop();
            // do something here...
            System.out.print(p.val+" ");
        }
    }

    public static void levelOrder(TreeNode root) {
        if(root == null) return;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            TreeNode cur;
            for(int i=0;i<size;i++) {
                cur = queue.pollFirst();
                // do something ...
                System.out.print(cur.val+" ");

                if(cur.left != null) queue.offer(cur.left);
                if(cur.right != null) queue.offer(cur.right);
            }
        }
    }
}
