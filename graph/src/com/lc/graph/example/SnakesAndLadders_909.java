package com.lc.graph.example;

import com.lc.graph.template.Node;
import com.lc.graph.template.Graph;
import com.lc.graph.template.Edge;

import java.util.*;

public class SnakesAndLadders_909 {
    public static void main(String[] args) {
        int[][] board = {
                {-1,-1,30,14,15,-1},
                {23,9,-1,-1,-1,9},
                {12,5,7,24,-1,30},
                {10,-1,-1,-1,25,17},
                {32,-1,28,-1,-1,32},
                {-1,-1,23,-1,13,19}
        };
        System.out.println(snakesAndLadders(board));
    }

    public static int snakesAndLadders(int[][] board) {
        int m = board.length;
        Deque<int[]> queue = new LinkedList<>();  // 记录bfs遍历状态  node id - distance
        boolean[] visited = new boolean[m*m+1];

        // add start point to queue
        queue.offer(new int[]{1,0});

        while(!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            // 6 possible positions
            for(int i=1;i<=6;i++) {
                int nxt = cur[0] + i;
                if(nxt > m*m) break;
                System.out.println("nxt="+nxt);
                int[] rc = invertToRowAndColumn(nxt, m);
                if(board[rc[0]][rc[1]] != -1) {
                    nxt = board[rc[0]][rc[1]];
                }

                if(nxt == m*m) return cur[1] + 1;

                if(!visited[nxt]) {
                    queue.offer(new int[]{nxt, cur[1]+1});
                    visited[nxt] = true;
                }
            }
        }
        return -1;
    }

    /**
     * Invert idx to i and j.
     * */
    private static int[] invertToRowAndColumn(int id, int n) {
        int r = (id - 1) / n, c = (id - 1) % n;
        if (r % 2 == 1) {
            c = n - 1 - c;
        }
        return new int[]{n - 1 - r, c};
    }
}

/**
 * Archive
 * */
//public class SnakesAndLadders_909 {
//    public static void main(String[] args) {
////        int[][] board = {
////                {-1,-1,-1,-1,-1,-1},
////                {-1,-1,-1,-1,-1,-1},
////                {-1,-1,-1,-1,-1,-1},
////                {-1,35,-1,-1,13,-1},
////                {-1,-1,-1,-1,-1,-1},
////                {-1,15,-1,-1,-1,-1}
////        };
//
////        int[][] board = {
////                {1,1,-1},
////                {1,1,1},
////                {-1,1,1}
////        };
//
//        int[][] board = {
//                {-1,-1,30,14,15,-1},
//                {23,9,-1,-1,-1,9},
//                {12,5,7,24,-1,30},
//                {10,-1,-1,-1,25,17},
//                {32,-1,28,-1,-1,32},
//                {-1,-1,23,-1,13,19}
//        };
////        Graph graph = GraphGenerator.createGraph(board);
////        graph.printGraph();
//        System.out.println(snakesAndLadders(board));
//    }
//
//    static class GraphGenerator {
//        public static Graph createGraph(int[][] board) {
//            Graph graph = new Graph();
//            int m = board.length, n = board[0].length;
//
//            HashMap<Integer, Integer> specialMap = new HashMap<>(); // idx - target
//            boolean left = false;
//            int idx = 0;
//
//            // construct special map to maintain lador and snake
//            for(int i=m-1;i>=0;--i) {
//                if(left) {
//                    for(int j=n-1;j>=0;--j) {
//                        idx++; // idx start from 1
//                        if(board[i][j] != -1) {
////                            System.out.println("idx="+idx+" i="+i+" j="+j);
//                            specialMap.put(idx, board[i][j]);
//                        }
//                    }
//                } else {
//                    for(int j=0;j<n;j++) {
//                        idx++;
//                        if(board[i][j] != -1) {
////                            System.out.println("idx="+idx+" i="+i+" j="+j);
//                            specialMap.put(idx, board[i][j]);
//                        }
//                    }
//                }
//                left = !left;
//            }
//
//
//            for(int i=1;i<=m*m;i++) {
//                // add from node
//                int from = i;
//
//                if(!graph.nodes.containsKey(from)) {
//                    graph.nodes.put(from, new Node(from));
//                }
//
//                Node fromNode = graph.nodes.get(from);
//
//                // add to node
//                int to;
//                if(specialMap.containsKey(from)) {
//                    // is a special node
//                    to = specialMap.get(from);
//                    if(!graph.nodes.containsKey(to)) {
//                        graph.nodes.put(to, new Node(to));
//                    }
//
//                    // construct edge
//                    Node toNode = graph.nodes.get(to);
//                    Edge edge = new Edge(fromNode, toNode, 0);
//
//                    fromNode.edge.add(edge);
//                    fromNode.next.add(toNode);
//                    fromNode.out++;
//                    toNode.in++;
//                    graph.edges.add(edge);
//
//                } else {
//                    for(int j=i+1;j<=Math.min(i+6,m*m);j++) {
//                        to = j;
//                        if(!graph.nodes.containsKey(to)) {
//                            graph.nodes.put(to, new Node(to));
//                        }
//
//                        Node toNode = graph.nodes.get(to);
//                        Edge edge = new Edge(fromNode, toNode, 1);
//
//                        fromNode.edge.add(edge);
//                        fromNode.next.add(toNode);
//                        fromNode.out++;
//                        toNode.in++;
//                        graph.edges.add(edge);
//                    }
//                }
//            }
//
//            return graph;
//        }
//    }
//
//
//    /**
//     * Dijskra
//     * */
//    public static HashMap<Node, Integer> dijskra(Node from) {
//        HashMap<Node, Integer> distanceMap = new HashMap<>();
//        HashSet<Node> selectedNodes = new HashSet<>();
//
//        distanceMap.put(from, 0);
//
//        // getMinDistanceFromUnselectedNodes
//        Node minNode = getMinDistanceFromUnselectedNodes(distanceMap, selectedNodes); // 跳转点
//
//        while(minNode!=null) {
//            int distance = distanceMap.get(minNode); // 到跳转点的距离
//
//            for(Edge edge: minNode.edge) {
//                Node to = edge.to;
//                if(!distanceMap.containsKey(to)) {
//                    distanceMap.put(to, distance+edge.weight);
//                } else {
//                    distanceMap.put(to, Math.min(distanceMap.get(to), distance+ edge.weight));  // distanceMap中已经有该记录
//                }
//            }
//            System.out.println("minNode: "+minNode.value);
//            printMap(distanceMap);
//
//            selectedNodes.add(minNode);
//            minNode = getMinDistanceFromUnselectedNodes(distanceMap, selectedNodes);
//        }
//        return distanceMap;
//    }
//
//    /**
//     * Find min distance node in distance map.
//     * */
//    public static Node getMinDistanceFromUnselectedNodes(HashMap<Node, Integer> distanceMap,
//                                                         HashSet<Node> selectedNodes) {
//        Node minNode = null;
//        int minDistance = Integer.MAX_VALUE;
//
//        for(Node node: distanceMap.keySet()) {
//            if(!selectedNodes.contains(node) && distanceMap.get(node) < minDistance) {
//                minDistance = distanceMap.get(node);
//                minNode = node;
//            }
//        }
//
//        return minNode;
//    }
//
//    private static void printMap(HashMap<Node, Integer> map) {
//        System.out.println("--------------------------------");
//        for(Map.Entry<Node, Integer> entry: map.entrySet()) {
//            System.out.println("node: "+ entry.getKey().value + " value: "+entry.getValue());
//        }
//    }
//
//    public static int snakesAndLadders(int[][] board) {
//        Graph graph = GraphGenerator.createGraph(board);
//
//        HashMap<Node, Integer> res = dijskra(graph.nodes.get(1));
//
////        for(Map.Entry<Node, Integer> entry: res.entrySet()) {
////            System.out.println("node: "+entry.getKey().value + " distance: "+entry.getValue());
////        }
//
//        Node target = graph.nodes.get(board.length * board.length);
//        return res.getOrDefault(target, -1);
//    }
//}
