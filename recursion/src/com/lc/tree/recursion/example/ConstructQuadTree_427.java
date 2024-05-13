package com.lc.tree.recursion.example;

import java.util.ArrayDeque;
import java.util.Deque;

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;


    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}

public class ConstructQuadTree_427 {
    public static void main(String[] args) {
        int[][] grid = {
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0},
                {1,1,1,1,0,0,0,0}
//                {0,1},
//                {1,0}
        };

        Node root = construct(grid);
        printQuadTree(root);
    }

    public static Node construct(int[][] grid) {
        return _construct(grid, 0, grid.length-1, 0, grid[0].length-1);
    }

    /**
     * Construct Node in the range,
     * topLeftPoint [leftTopI, leftTopJ]
     * rightDownPoint [rightDownI, rightDownJ]
     * */
    private static Node _construct(int[][] grid, int l, int r, int low, int high) {
        if(allSame(grid, l, r, low, high)) return new Node(grid[low][l] == 1, true);

        Node root = new Node();
        int mid_1 = r + l >> 1;
        int mid_2 = low + high >> 1;
        root.topLeft = _construct(grid, l, mid_1, low, mid_2);
        root.topRight = _construct(grid, mid_1+1, r, low, mid_2);
        root.bottomLeft = _construct(grid, l, mid_1, mid_2+1, high);
        root.bottomRight = _construct(grid, mid_1+1, r, mid_2+1, high);
        root.isLeaf = false;
        return root;
    }

    /**
     * in j in [l, r]
     * i in [low, high] area, is all element the same
     * */
    private static boolean allSame(int[][] grid, int l, int r, int low, int high) {
        for(int i=low;i<=high;i++) {
            for(int j=l;j<=r;j++) {
                if(grid[i][j] != grid[low][l]) return false;
            }
        }
        return true;
    }

    private static void printQuadTree(Node root) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            Node cur = queue.pollFirst();
            System.out.println("isLeaf: "+cur.isLeaf + " cur val: "+cur.val);

            if(!cur.isLeaf) {
                //if(cur.topLeft != null) {
                    queue.offer(cur.topLeft);
                    System.out.println("isLeaf:"+cur.topLeft.isLeaf + " val: "+cur.topLeft.val);
                //}

                //if(cur.topRight!=null) {
                    queue.offer(cur.topRight);
                    System.out.println("isLeaf:"+cur.topRight.isLeaf + " val: "+cur.topRight.val);
                //}

                //if(cur.bottomLeft!=null) {
                    queue.offer(cur.bottomLeft);
                    System.out.println("isLeaf:"+cur.bottomLeft.isLeaf + " val: "+cur.bottomLeft.val);
                //}

                //if(cur.bottomRight!=null) {
                    queue.offer(cur.bottomRight);
                    System.out.println("isLeaf:"+cur.bottomRight.isLeaf + " val: "+cur.bottomRight.val);
                //}
            }
        }
    }
}
