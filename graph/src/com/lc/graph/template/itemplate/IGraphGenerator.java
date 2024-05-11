package com.lc.graph.template.itemplate;

abstract class IGraphGenerator<V> {

    Graph<V> createGraph(V[][] matrix) {
        Graph<V> graph = new Graph<>();

        for(int i=0;i<matrix.length;i++) {
            V from = matrix[i][0];
            V to = matrix[i][1];
            V weight = matrix[i][2];

            if(!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node<>(from));
            }

            if(!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node<>(to));
            }

            Node<V> fromNode = graph.nodes.get(from);
            Node<V> toNode = graph.nodes.get(to);

            double dw = Double.parseDouble(weight.toString());
            Edge<V> edge = new Edge<>(fromNode, toNode, dw);

            fromNode.next.add(toNode);
            fromNode.edge.add(edge);

            fromNode.out++;
            toNode.in++;

            graph.edges.add(edge);
        }
        return graph;
    }

    Graph<V> createGraph(V[][] edges, Double[] weights) {
        Graph<V> graph = new Graph<>();

        for(int i=0;i<edges.length;i++) {
            V from = edges[i][0];
            V to = edges[i][1];
            double weight = weights[i];

            if(!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node<>(from));
            }

            if(!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node<>(to));
            }

            Node<V> fromNode = graph.nodes.get(from);
            Node<V> toNode = graph.nodes.get(to);

            Edge<V> edge = new Edge<>(fromNode, toNode, weight);

            fromNode.next.add(toNode);
            fromNode.edge.add(edge);

            fromNode.out++;
            toNode.in++;

            graph.edges.add(edge);
        }

        return graph;
    }
}
