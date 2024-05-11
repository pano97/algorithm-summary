package com.lc.graph.example;

import com.lc.graph.template.itemplate.Edge;
import com.lc.graph.template.itemplate.IGraphGenerator;
import com.lc.graph.template.itemplate.Graph;
import com.lc.graph.template.itemplate.Node;

import java.util.*;


class GraphGenerator_399 extends IGraphGenerator<String> {
    Graph<String> createGraph(List<List<String>> equations, double[] values) {
        Graph<String> graph = new Graph<>();

        for(int i=0;i<equations.size();i++) {
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            double weight = values[i];

            if(!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node<>(from));
            }

            if(!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node<>(to));
            }

            Node<String> fromNode = graph.nodes.get(from);
            Node<String> toNode = graph.nodes.get(to);

            Edge<String> edge = new Edge<>(fromNode, toNode, weight);
            Edge<String> edge_2 = new Edge<>(toNode, fromNode, 1.0/weight);

            fromNode.next.add(toNode);
            fromNode.edge.add(edge);
            fromNode.out++;
            toNode.in++;

            toNode.next.add(fromNode);
            toNode.edge.add(edge_2);
            toNode.out++;
            fromNode.in++;

            graph.edges.add(edge);
            graph.edges.add(edge_2);
        }
        return graph;
    }
}

public class EvaluateDivision_399 {

    public static void main(String[] args) {
        String[][] eqs = {
                {"x1","x2"},
                {"x2","x3"},
                {"x3","x4"},
                {"x4","x5"}
        };
        String[][] qs = {
                {"x1","x5"},
                {"x5","x2"},
                {"x2","x4"},
                {"x2","x2"},
                {"x2","x9"}
        };
        double[] values = {3.0, 4.0, 5.0, 6.0};

        List<List<String>> equations = new ArrayList<>();
        List<List<String>> querys = new ArrayList<>();

        for(int i=0;i<eqs.length;i++) {
            equations.add(Arrays.asList(eqs[i]));
        }

        for(int i=0;i<qs.length;i++) {
            querys.add(Arrays.asList(qs[i]));
        }

        double[] res = calcEquation(equations, values, querys);

        for(double t: res)
            System.out.print(t+" ");
        System.out.println();

    }


    /**
     * Method 1：dfs
     * */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Graph<String> graph = new GraphGenerator_399().createGraph(equations, values);

        graph.printEdges();

        double[] res = new double[queries.size()];
        Arrays.fill(res, -1.0);

        HashSet<Node<String>> visited = new HashSet<>();

        for(int i=0;i<queries.size();i++) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);

            // search start: from
            if(graph.nodes.containsKey(from) && graph.nodes.containsKey(to)) {
                dfs(graph.nodes.get(from), graph.nodes.get(to), i, res, 1.0, visited);
            }
        }
        return res;
    }

    private static void dfs(Node<String> from, Node<String> to, int index,
                            double[] res, double val, HashSet<Node<String>> visited) {
        if(visited.contains(from)) return;

        // find the query
        if(from == to) {
            res[index] = val;
            return;
        }

        visited.add(from);  // set to visit

        for(Edge<String> edge: from.edge) {
            dfs(edge.to, to, index, res, val*edge.weight, visited);
        }

        visited.remove(from);
    }




    /**
     * Method 2：bfs, NA
     * */
//    public static double[] calcEquation(List<List<String>> equations,
//                                        double[] values, List<List<String>> queries) {
//        Graph<String> graph = new GraphGenerator_399().createGraph(equations, values);
//        double[] res = new double[queries.size()];
//        Arrays.fill(res, -1.0);
//
//        // visited set
//        HashSet<Node<String>> visited = new HashSet<>();
//
//        // node - [node, distance]
//        HashMap<Node<String>, HashMap<Node<String>, Double>> disMap = new HashMap<>();
//
//        for(Node<String> from: graph.nodes.values()) {
//            if(!visited.contains(from)) {
//
//                HashMap<Node<String>, Double> tmp = new HashMap<>();
//                tmp.put(from, 1.0);
//                disMap.put(from, tmp);
//
//                bfs(from, visited, disMap);
//            }
//        }
//
//        // check disMap
//        for(Map.Entry<Node<String>, HashMap<Node<String>, Double>> entry: disMap.entrySet()) {
//            Node<String> source = entry.getKey();
//            HashMap<Node<String>, Double> sourceMap = entry.getValue();
//
//            System.out.print("from "+source.value+": ");
//
//            for(Map.Entry<Node<String>, Double> entry1: sourceMap.entrySet()) {
//                System.out.println(entry1.getKey().value +" - "+ entry1.getValue());
//            }
//        }
//
//        // fill in res
//        for(int i=0;i<queries.size();i++) {
//            String from = queries.get(i).get(0);
//            String to = queries.get(i).get(1);
//
//            if(graph.nodes.containsKey(from) && graph.nodes.containsKey(to)) {
//                Node<String> fn = graph.nodes.get(from);
//                Node<String> tn = graph.nodes.get(to);
//
//                System.out.println("from node: "+fn.value + " to node: "+tn.value);
//                HashMap<Node<String>, Double> tmp = disMap.get(fn);
//                res[i] = tmp.get(tn);
//                //res[i] = tmp.getOrDefault(tn, -1.0);
//            }
//        }
//
//        return res;
//    }
//
//    private static void bfs(Node<String> from, HashSet<Node<String>> visited,
//                            HashMap<Node<String>, HashMap<Node<String>, Double>> disMap) {
//
//        if(visited.contains(from)) return;
//
//        Deque<Node<String>> queue = new ArrayDeque<>();
//        Deque<Double> valQue = new ArrayDeque<>();
//
//        queue.offer(from);
//        valQue.offer(1.0);
//        visited.add(from);
//
//        while(!queue.isEmpty()) {
//            from = queue.pollFirst();
//            double val = valQue.pollFirst();
//
//            HashMap<Node<String>, Double> fromMap = disMap.getOrDefault(from, new HashMap<>());
//            HashMap<Node<String>, Double> toMap;
//
//            for(Edge<String> edge: from.edge) {
//                Node<String> to = edge.to;
//
//                if(!visited.contains(to)) {
//                    visited.add(to);
//                    queue.offer(to);
//                    valQue.offer(val * edge.weight);
//
//                    // update path for map1
//                    fromMap.put(to, valQue.peek());
//                    disMap.put(from, fromMap);
//
////                    toMap = disMap.getOrDefault(to, new HashMap<>());
////                    toMap.put(from, val / edge.weight);
////                    disMap.put(to, toMap);
//
//                    toMap = disMap.getOrDefault(to, new HashMap<>());
//                    toMap.put(from, toMap.getOrDefault(to, 1.0) / edge.weight);
//                    disMap.put(to, toMap);
//                }
//            }
//
//        }
//    }
}
