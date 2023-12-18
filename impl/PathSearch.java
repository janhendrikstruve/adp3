package de.hawhamburg.hamann.ad.trees.impl;

import java.util.List;
import java.util.Map;

public class PathSearch<T> {

    void pathSearch(Graph graph, T start, T end){
        T s = start;
        while (!graph.getNode(s).isEmpty()){
            if(graph.getNode(s).contains(end)){

            }
        }
    }
}
