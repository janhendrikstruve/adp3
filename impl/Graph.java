package de.hawhamburg.hamann.ad.trees.impl;

import java.util.*;

public class Graph<T, P extends Comparable<P>> {

    private Map<T, List<Edge<T, P>>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addNode(T node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(T from, T to, P prio) {
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            throw new IllegalArgumentException("Edge added to non-existing nodes");
        }
        adjacencyList.get(from).add(new Edge<>(to, prio));
        // If the graph is undirected, add an edge in the opposite direction as well
        // adjacencyList.get(to).add(new Edge<>(from, prio));
    }

    public List<Edge<T, P>> getNode(T node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }

    // Any other methods you might need for your Graph class
}
