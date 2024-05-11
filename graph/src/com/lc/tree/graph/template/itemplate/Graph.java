package com.lc.tree.graph.template.itemplate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Graph<V> {
    public HashMap<V, Node<V>> nodes;  // 点集
    public HashSet<Edge<V>> edges;  // 边集

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public int getNodeNumber() {
        return nodes.size();
    }

    public int getEdgeNumber() {
        return edges.size();
    }

    public int getInDegree() {
        int in = 0;
        for(Map.Entry<V, Node<V>> entry: nodes.entrySet()) {
            in += entry.getValue().in;
        }
        return in;
    }

    public int getOutDegree() {
        int out = 0;
        for(Map.Entry<V, Node<V>> entry: nodes.entrySet()) {
            out += entry.getValue().out;
        }
        return out;
    }

    public void printGraph() {
        System.out.println("============== Graph Summary ===============");
        System.out.println("Node num: " + getNodeNumber());
        System.out.println("Edge num: " + getEdgeNumber());
        System.out.println("In degree: " + getInDegree());
        System.out.println("Out degree: " + getOutDegree());
        System.out.println("============== Graph Structure ===============");
        for(Map.Entry<V, Node<V>> entry: nodes.entrySet()) {
            Node<V> node = entry.getValue();

            // print node itself
            System.out.print(node.value+": ");

            // print node's neighbors
            for(Node<V> neighbor: node.next) {
                System.out.print(neighbor.value+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printEdges() {
        for (Edge<V> edge : edges) {
            System.out.println(edge);
        }
    }
}
