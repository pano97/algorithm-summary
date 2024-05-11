package com.lc.tree.graph.dfs;
import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph_133 {
    public static void main(String[] args) {

    }

    /**
     * 方法一：广度优先遍历
     * clone node
     * clone neighbor
     * */
    public static Node cloneGraph_2(Node node) {
        if(node == null) return node;

        Deque<Node> queue = new ArrayDeque<>();
        Map<Node, Node> map = new HashMap<>();

        queue.offer(node);
        map.put(node, new Node(node.val, new ArrayList<>()));

        while(!queue.isEmpty()) {
            Node p = queue.pollFirst();

            for(Node neighbor: p.neighbors) {
                if(!map.containsKey(neighbor)) {
                    map.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                    queue.offer(neighbor);
                }

                map.get(p).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }

    /**
     * 方法二：dfs，递归
     * clone node
     * add neighbor
     * */
    static Map<Node, Node> visited = new HashMap<>();

    public static Node cloneGraph(Node node) {
        if(node == null) return node;

        if(visited.containsKey(node)) return visited.get(node);

        visited.put(node, new Node(node.val, new ArrayList<>()));

        for(Node neighbor: node.neighbors) {
            visited.get(node).neighbors.add(cloneGraph(neighbor));
        }
        return visited.get(node);
    }
}
