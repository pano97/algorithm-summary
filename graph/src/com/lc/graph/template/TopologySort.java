package com.lc.graph.template;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TopologySort {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {1,2,0},
                {3,2,0},
                {2,5,0},
                {5,4,0},
                {6,5,0}
        };
        Graph g = GraphGenerator.createGraphWithEdgeFromTo(matrix);

        List<Node> res = sortedTopology(g);
        for(Node nn: res)
            System.out.print(nn.value+" ");
    }

    /**
     * Topology sort.
     * Maintain a zero node queue, which contains all in degree = 0 nodes
     * */
    public static List<Node> sortedTopology(Graph graph) {

        HashMap<Node, Integer> inMap = new HashMap<>();  // node - in degree
        Deque<Node> zeroNodes = new LinkedList<>();  // zero in degree nodes
        List<Node> result = new LinkedList<>();

        // add all nodes in map
        for(Node node: graph.nodes.values()) {
            inMap.put(node, node.in);
            if(inMap.get(node) == 0) {
                zeroNodes.offer(node);  // if in degree = 0, add to zero nodes queue
            }
        }

        while(!zeroNodes.isEmpty()) {
            Node cur = zeroNodes.pollFirst();
            result.add(cur);
            for(Node neighbor: cur.next) {
                inMap.put(neighbor, inMap.get(neighbor) - 1);
                if (inMap.get(neighbor) == 0) {
                    zeroNodes.offer(neighbor);
                }
            }
        }
        return result;
    }
}
