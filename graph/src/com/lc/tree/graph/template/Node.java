package com.lc.tree.graph.template;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value;
    public int in;
    public int out;
    public List<Node> next;
    public List<Edge> edge;

    public Node() {}

    public Node(int value) {
        this.value = value;
        in = 0;
        out = 0;
        next = new ArrayList<>();
        edge = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "value: " + value + "in: "+ in + "out: "+ out;
    }
}
