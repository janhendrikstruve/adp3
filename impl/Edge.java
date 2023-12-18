package de.hawhamburg.hamann.ad.trees.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class Edge<T,P> {
    private T node;
    private P prio;
}
