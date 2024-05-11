package com.lc.graph.template.itemplate;

import java.util.ArrayList;
import java.util.List;

public class Node<V> {
    public V value;
    public int in;
    public int out;
    public List<Node<V>> next;
    public List<Edge<V>> edge;

    public Node(V value) {
        this.value = value;
        in = 0;
        out = 0;
        next = new ArrayList<>();
        edge = new ArrayList<>();
    }
}
