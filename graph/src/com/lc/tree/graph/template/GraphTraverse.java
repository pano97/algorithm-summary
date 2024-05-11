package com.lc.tree.graph.template;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Stack;

/**
 * Graph traverse. BFS and DFS.
 * */
public class GraphTraverse {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {1, 2, 5},
                {2, 3, 7},
                {4, 2, 8},
                {5, 4, 9},
                {2, 5, 6}
        };

        Graph g = GraphGenerator.createGraphWithEdgeFromTo(matrix);

        //BFS(g.nodes.get(1));
        //BFS(g);
        DFS(g.nodes.get((5)));
    }

    public static void BFS(Graph graph) {
        if(graph == null) return;

        Deque<Node> queue = new ArrayDeque<>();
        HashSet<Node> visited = new HashSet<>();

        for(Node node: graph.nodes.values()) {
            if(!visited.contains(node)) {
                queue.offer(node);
                visited.add(node);

                while(!queue.isEmpty()) {
                    Node p = queue.pollFirst();
                    System.out.print(p.value+" ");   // print after pop

                    for(Node neighbor: p.next) {
                        if(!visited.contains(neighbor)) {
                            queue.offer(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
        }
    }

    public static void BFS(Node node) {
        if(node == null) return;

        Deque<Node> queue = new ArrayDeque<>();
        HashSet<Node> visited = new HashSet<>();

        queue.offer(node);
        visited.add(node);

        while(!queue.isEmpty()) {
            Node p = queue.pollFirst();
            System.out.print(p.value+" ");   // print after pop

            for(Node neighbor: p.next) {
                if(!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    public static void DFS(Node node) {
        if(node == null) return;

        Stack<Node> path = new Stack<>();
        HashSet<Node> visited = new HashSet<>();

        path.add(node);
        visited.add(node);
        System.out.print(node.value+" ");

        while(!path.isEmpty()) {
            Node cur = path.pop();

            for(Node neighbor: cur.next) {
                // If there are unvisited neighbor, find one and break
                if(!visited.contains(neighbor)) {
                    path.add(cur);
                    path.add(neighbor);
                    visited.add(neighbor);
                    System.out.print(neighbor.value+" ");
                    break;
                }
            }
        }
    }
}
