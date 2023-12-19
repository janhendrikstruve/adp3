package de.hawhamburg.hamann.ad.trees.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;



public class PathSearchTest {

    private Graph<Integer, Integer> graph;
    private PathSearch<Integer> pathSearch;
    Map<Integer, Integer> expectedDistances;;



    @BeforeEach
    public void setup() {
        graph = new Graph<>();
        // Add nodes. For simplicity, let's assume nodes are integers.
        for (int i = 1; i <= 8; i++) {
            graph.addNode(i);
        }

        // Add edges with weights
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 1);

        graph.addEdge(2, 1, 1);
        graph.addEdge(2, 4, 1);

        graph.addEdge(3, 1, 1);
        graph.addEdge(3, 2, 1);
        graph.addEdge(3, 4, 1);

        graph.addEdge(4, 2, 1);
        graph.addEdge(4, 3, 1);
        graph.addEdge(4, 5, 1);

        graph.addEdge(5, 4, 1);
        graph.addEdge(5, 6, 1);
        graph.addEdge(5, 7, 1);
        graph.addEdge(5, 8, 1);

        graph.addEdge(6, 5, 1);

        graph.addEdge(7, 5, 1);

        graph.addEdge(8, 5, 1);


        pathSearch = new PathSearch<>(graph);

        expectedDistances = new HashMap<>();
        expectedDistances.put(1,0);
        expectedDistances.put(2,1);
        expectedDistances.put(3,1);
        expectedDistances.put(4,2);
        expectedDistances.put(5,3);
        expectedDistances.put(6,4);
        expectedDistances.put(7,4);
        expectedDistances.put(8,4);
    }

    @Test
    public void testDijkstraCorrectness() {
        Map<Integer, Integer> actualDistances = pathSearch.dijkstra(1);

        assertEquals(expectedDistances, actualDistances, "Dijkstra algorithm should return correct shortest paths.");
    }

    @Test
    public void testDijkstraPerformance() {
        long startTime = System.nanoTime();
        pathSearch.dijkstra(1);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;
        System.out.println("Execution Time for Dijkstra: " + duration + " nanoseconds");


    }

    @Test
    public void testDijkstraWithInvalidInput() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            pathSearch.dijkstra(-1); // Assuming '-1' is not a valid node
        });

        String expectedMessage = "No such node in the graph";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBreadthFirstSearchCorrectness() {


        Map<Integer, Integer> actualDistances = pathSearch.breadthSearch(1);

        assertEquals(expectedDistances, actualDistances, "BFS should return correct distances.");
    }

    @Test
    public void testDepthFirstSearchCorrectness() {

        Map<Integer, Integer> actualDepths = pathSearch.depthSearch(1);

        assertEquals(expectedDistances, actualDepths, "DFS should return correct depths.");
    }
}
