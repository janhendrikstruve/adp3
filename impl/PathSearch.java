package de.hawhamburg.hamann.ad.trees.impl;

import java.util.*;

public class PathSearch<T> {

    private Graph<T, Integer> graph;

    public PathSearch(Graph<T, Integer> graph) {
        this.graph = graph;
    }

    public Map<T, Integer> dijkstra(T source) {
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<Edge<T, Integer>> queue = new PriorityQueue<>(Comparator.comparing(Edge::getPrio));

        distances.put(source, 0); // Initialize source distance to 0
        queue.add(new Edge<>(source, 0));

        while (!queue.isEmpty()) {
            Edge<T, Integer> currentEdge = queue.poll();
            T currentNode = currentEdge.getNode();

            for (Edge<T, Integer> edge : graph.getNode(currentNode)) {
                T neighbor = edge.getNode();
                Integer newDistance = sum(distances.get(currentNode), edge.getPrio());

                if (!distances.containsKey(neighbor) || distances.get(neighbor) > newDistance) {
                    distances.put(neighbor, newDistance);
                    queue.add(new Edge<>(neighbor, newDistance));
                }
            }
        }

        return distances;
    }

    public Map<T, Integer> breadthFirstSearch(T source) {
        Map<T, Integer> distances = new HashMap<>();
        Queue<T> queue = new LinkedList<>();

        distances.put(source, 0); // Initialize source distance to 0
        queue.add(source);

        while (!queue.isEmpty()) {
            T currentNode = queue.poll();

            for (Edge<T, Integer> edge : graph.getNode(currentNode)) {
                T neighbor = edge.getNode();

                if (!distances.containsKey(neighbor)) {
                    distances.put(neighbor, distances.get(currentNode) + 1);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }

    public Map<T, Integer> depthFirstSearch(T source) {
        Map<T, Integer> distances = new HashMap<>();
        Set<T> visited = new HashSet<>();
        dfsUtil(source, 0, distances, visited);
        return distances;
    }

    private void dfsUtil(T node, int depth, Map<T, Integer> distances, Set<T> visited) {
        visited.add(node);
        distances.put(node, depth);

        for (Edge<T, Integer> edge : graph.getNode(node)) {
            T neighbor = edge.getNode();
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, depth + 1, distances, visited);
            }
        }
    }
    private Integer sum(Integer a, Integer b) {
        // Null check for initial cases where the sum involves the source node
        if (a == null) a = 0;
        if (b == null) b = 0;
        return a + b;
    }
}
