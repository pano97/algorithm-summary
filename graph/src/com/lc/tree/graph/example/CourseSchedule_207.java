package com.lc.tree.graph.example;

import java.util.*;

class Node {
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

class Edge {
    Node from;
    Node to;
    int weight;

    public Edge() {}

    public Edge(Node from, Node to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Graph {
    public HashMap<Integer, Node> nodes;  // 点集
    public HashSet<Edge> edges;  // 边集

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
        for(Map.Entry<Integer, Node> entry: nodes.entrySet()) {
            in += entry.getValue().in;
        }
        return in;
    }

    public int getOutDegree() {
        int out = 0;
        for(Map.Entry<Integer, Node> entry: nodes.entrySet()) {
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
        for(Map.Entry<Integer, Node> entry: nodes.entrySet()) {
            Node node = entry.getValue();

            // print node itself
            System.out.print(node.value+": ");

            // print node's neighbors
            for(Node neighbor: node.next) {
                System.out.print(neighbor.value+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}


class GraphGenerator {
    public static Graph createGraph(int num, int[][] edges) {
        Graph graph = new Graph();

        // add nodes
        for(int i=0;i<num;i++) {
            if(!graph.nodes.containsKey(i)) {
                graph.nodes.put(i, new Node(i));
            }
        }

        for(int i=0;i<edges.length;i++) {
            int from = edges[i][1];
            int to = edges[i][0];

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            Edge edge = new Edge(fromNode, toNode, 0);

            fromNode.next.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edge.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

}

public class CourseSchedule_207 {
    public static void main(String[] args) {
        int[][] matrix = {
                {3,0},
                {0,1}

        };
        int num = 4;
//        Graph graph = GraphGenerator.createGraph(matrix);
//        graph.printGraph();

        // System.out.println(canFinish(5, matrix));

//        Graph graph = GraphGenerator.createGraph(num, matrix);
//        graph.printGraph();

        int[] res = findOrder(num, matrix);
        for(int i:res)
            System.out.print(i+" ");
        System.out.println();


    }

    /**
     * Topology
     * */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites == null || prerequisites.length == 0 || prerequisites[0] == null ||
        prerequisites[0].length == 0) return true;

        Graph graph = GraphGenerator.createGraph(numCourses, prerequisites);

        HashMap<Node, Integer> inMap = new HashMap<>();
        Deque<Node> zeroNodes = new LinkedList<>();

        int cnt = 0;
        List<Node> result = new LinkedList<>();

        // add all nodes to in Map
        for(Node node: graph.nodes.values()) {
            inMap.put(node, node.in);
            if(inMap.get(node) == 0) {
                zeroNodes.offer(node);
            }
        }


        while(!zeroNodes.isEmpty()) {
            Node cur = zeroNodes.pollFirst();
            result.add(cur);
            System.out.print(cur.value+" ");

            ++cnt;

            for(Node neighbor: cur.next) {
                if(inMap.containsKey(neighbor)) {
                    inMap.put(neighbor, inMap.get(neighbor)-1);
                    if(inMap.get(neighbor) == 0) {
                        zeroNodes.offer(neighbor);
                    }
                }
            }
        }


        return cnt == graph.getNodeNumber();
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph graph = GraphGenerator.createGraph(numCourses, prerequisites);

        HashMap<Node, Integer> inMap = new HashMap<>();  // node - in degree
        Deque<Node> zeroNodes = new LinkedList<>();  // zero in degree nodes
        List<Integer> result = new LinkedList<>();

        // add all nodes in map
        for(Node node: graph.nodes.values()) {
            inMap.put(node, node.in);
            if(inMap.get(node) == 0) {
                zeroNodes.offer(node);  // if in degree = 0, add to zero nodes queue
            }
        }

        while(!zeroNodes.isEmpty()) {
            Node cur = zeroNodes.pollFirst();
            result.add(cur.value);
            for(Node neighbor: cur.next) {
                inMap.put(neighbor, inMap.get(neighbor) - 1);
                if (inMap.get(neighbor) == 0) {
                    zeroNodes.offer(neighbor);
                }
            }
        }

        return result.size() == numCourses ? result.stream().mapToInt(i -> i).toArray(): new int[]{};
    }

}
