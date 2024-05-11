package com.lc.tree.graph.template.itemplate;

public class Edge<V> {

    public Node<V> from;
    public Node<V> to;

    public Double weight;

    public Edge() {}

    public Edge(Node<V> from, Node<V> to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from.value +
                ", to=" + to.value +
                ", weight=" + weight +
                '}';
    }
}
