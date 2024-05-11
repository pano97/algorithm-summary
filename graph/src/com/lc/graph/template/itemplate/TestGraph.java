package com.lc.graph.template.itemplate;

class GraphGenerator<V> extends IGraphGenerator<V> {
//    @Override
//    Graph<V> createGraph(V[][] edges, Double[] weights) {
//        Graph<V> graph = new Graph<>();
//
//        for(int i=0;i<edges.length;i++) {
//            V from = edges[i][0];
//            V to = edges[i][1];
//            double weight = weights[i];
//
//            if(!graph.nodes.containsKey(from)) {
//                graph.nodes.put(from, new Node<>(from));
//            }
//
//            if(!graph.nodes.containsKey(to)) {
//                graph.nodes.put(to, new Node<>(to));
//            }
//
//            Node<V> fromNode = graph.nodes.get(from);
//            Node<V> toNode = graph.nodes.get(to);
//
//            Edge<V> edge = new Edge<>(fromNode, toNode, weight);
//
//            fromNode.next.add(toNode);
//            fromNode.edge.add(edge);
//
//            fromNode.out++;
//            toNode.in++;
//
//            graph.edges.add(edge);
//        }
//
//        return graph;
//    }
}

public class TestGraph {
    public static void main(String[] args) {
        //testInteger();
        testString();
    }

    public static void testInteger() {
        Integer[][] matrix = {
                {1, 2, 5},
                {2, 3, 7},
                {4, 2, 8},
                {5, 4, 9},
                {2, 5, 6}
        };
        GraphGenerator<Integer> gg = new GraphGenerator<>();
        Graph<Integer> graph = gg.createGraph(matrix);
        graph.printGraph();
    }

    public static void testString() {
        String[][] matrix = {
                {"a", "b"},
                {"b", "c"}
        };
        Double[] values = {2.0, 3.0};

        GraphGenerator<String> gg = new GraphGenerator<>();
        Graph<String> graph = gg.createGraph(matrix, values);
        //graph.printGraph();
        graph.printEdges();
    }

}
