package com.lc.graph.template;

public class Edge {
    public Node from;
    public Node to;
    public int weight;

    public Edge() {}

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}
