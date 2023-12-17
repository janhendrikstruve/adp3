package de.hawhamburg.hamann.ad.trees.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class Node<Data> {
    private Node<Data> left;
    private Node<Data> right;
    private Data data;
    private boolean isLeaf;
}
