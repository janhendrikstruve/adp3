package de.hawhamburg.hamann.ad.trees.impl;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
public class Graph<T> {
    private Map<T, List<Edge>> list;
    private boolean visited;
    public Graph(){
        list = new HashMap<>();
        visited = false;
    }

    void addNode(T node){
        list.put(node,new ArrayList<Edge>());
    }
    void addEdge(T node, Edge edge){
        if (!list.containsKey(node)) {
            throw new IllegalArgumentException("Added a edge to a node which was not created");
        }
        list.get(node).add(edge);
    }
    List<Edge> getNode(T key){
        return list.get(key);
    }
}
