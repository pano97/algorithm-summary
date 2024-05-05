package com.lc.graph.template;

public class GraphGenerator {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Integer[][] matrix = {
                {1, 2, 5},
                {2, 3, 7},
                {4, 2, 8},
                {5, 4, 9},
                {2, 5, 6}
        };
        System.out.println("============= Test Case 1 ===============");
        Graph g = createGraphWithEdgeFromTo(matrix);
        g.printGraph();

        System.out.println("============= Test Case 2 ===============");
        Integer[][] matrix2 = {
                {0,5,INF,INF,INF},
                {INF,0,7,INF,6},
                {INF,INF,0,INF,INF},
                {INF,8,INF,0,INF},
                {INF,INF,INF,9,0}
        };
        g = createGraphWithMatrix(matrix2);
        g.printGraph();
    }

    /**
     * Method 1: Create graph with matrix: [from, to, weight]
     * [
     *     [3,1,2],
     *     [0,1,3]
     *
     * ]
     * */
    public static Graph createGraphWithEdgeFromTo(Integer[][] matrix) {
        Graph graph = new Graph();

        for(int i=0;i<matrix.length;i++) {
            int from = matrix[i][0];  // from node index
            int to = matrix[i][1];    // to node index
            int w = matrix[i][2];

            if(!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }

            if(!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            // construct edge
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(fromNode, toNode, w);

            fromNode.next.add(toNode);
            fromNode.edge.add(edge);

            fromNode.out++;
            toNode.in++;

            graph.edges.add(edge);
        }
        return graph;
    }

    /**
     * Method 2: 邻接矩阵
     * */
    public static Graph createGraphWithMatrix(Integer[][] matrix) {
        Graph graph = new Graph();
        int m = matrix.length;

        for(int i=0;i<m;i++) {
            for(int j=0;j<m;j++) {
                if(i!=j && matrix[i][j] != Integer.MAX_VALUE)
                {
                    int from = i+1;
                    int to = j+1;
                    int weight = matrix[i][j];

                    if(!graph.nodes.containsKey(from)) {
                        graph.nodes.put(from, new Node(from));
                    }

                    if(!graph.nodes.containsKey(to)) {
                        graph.nodes.put(to, new Node(to));
                    }
                    // get fromNode and toNode
                    Node fromNode = graph.nodes.get(from);
                    Node toNode = graph.nodes.get(to);

                    // construct edge
                    Edge edge = new Edge(fromNode, toNode, weight);
                    fromNode.next.add(toNode);
                    fromNode.edge.add(edge);

                    fromNode.out++;
                    toNode.in++;
                    graph.edges.add(edge);
                }
            }
        }
        return graph;
    }
}
