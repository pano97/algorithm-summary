package com.lc.tree.graph.template;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Dijskra {
    public static void main(String[] args) {
        Integer[][] matrix = {
                {1,2,1},
                {1,3,1},
                {2,3,1},
                {2,4,1},
                {3,4,1}
        };

        Graph graph = GraphGenerator.createGraphWithEdgeFromTo(matrix);

        graph.printGraph();

        HashMap<Node, Integer> map = dijskra(graph.nodes.get(1));

        for(Map.Entry<Node, Integer> entry: map.entrySet()) {
            System.out.println("node: "+entry.getKey().value + " distance: "+entry.getValue());
        }
    }

    /**
     * Dijskra
     * */
    public static HashMap<Node, Integer> dijskra(Node from) {
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        HashSet<Node> selectedNodes = new HashSet<>();

        distanceMap.put(from, 0);

        // getMinDistanceFromUnselectedNodes
        Node minNode = getMinDistanceFromUnselectedNodes(distanceMap, selectedNodes); // 跳转点

        while(minNode!=null) {
            int distance = distanceMap.get(minNode); // 到跳转点的距离

            for(Edge edge: minNode.edge) {
                Node to = edge.to;
                if(!distanceMap.containsKey(to)) {
                    distanceMap.put(to, distance+edge.weight);
                } else {
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance+ edge.weight));  // distanceMap中已经有该记录
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceFromUnselectedNodes(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    /**
     * Find min distance node in distance map.
     * */
    public static Node getMinDistanceFromUnselectedNodes(HashMap<Node, Integer> distanceMap,
                                                          HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for(Node node: distanceMap.keySet()) {
            if(!selectedNodes.contains(node) && distanceMap.get(node) < minDistance) {
                minDistance = distanceMap.get(node);
                minNode = node;
            }
        }

        return minNode;
    }
}
