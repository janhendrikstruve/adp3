package de.hawhamburg.hamann.ad.trees.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;



public class PathSearchTest {

    private Graph<Integer, Integer> graph;
    private PathSearch<Integer> pathSearch;
    Map<Pair, Integer> expectedDistances;;



    @BeforeEach
    public void setup() {
        graph = new Graph<>();
        // Add nodes. For simplicity, let's assume nodes are integers.
        for (int i = 1; i <= 5; i++) {
            graph.addNode(i);
        }

        // Add edges with weights
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 2, 3);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 2);
        graph.addEdge(4, 5, 4);
        graph.addEdge(5, 1, 7);
        graph.addEdge(5, 4, 6);

        pathSearch = new PathSearch<>(graph);

        expectedDistances = new HashMap<>();
        expectedDistances.put(new Pair(1, 2), 10);
        expectedDistances.put(new Pair(1, 3), 5);
        expectedDistances.put(new Pair(2, 3), 2);
        expectedDistances.put(new Pair(2, 4), 1);
        expectedDistances.put(new Pair(3, 2), 3);
        expectedDistances.put(new Pair(3, 4), 9);
        expectedDistances.put(new Pair(3, 5), 2);
        expectedDistances.put(new Pair(4, 5), 4);
        expectedDistances.put(new Pair(5, 1), 7);
        expectedDistances.put(new Pair(5, 4), 6);
    }

    @Test
    public void testDijkstraCorrectness() {
        Map<Integer, Integer> expectedDistances = new HashMap<>();
        // Populate with expected shortest paths from node 1
        expectedDistances.put(1, 0);
        expectedDistances.put(2, 7);  // Example path: 1 -> 3 -> 2
        expectedDistances.put(3, 5);  // Example path: 1 -> 3
        expectedDistances.put(4, 8);  // Example path: 1 -> 3 -> 2 -> 4
        expectedDistances.put(5, 7);  // Example path: 1 -> 3 -> 5

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

        // Optional: Assert on the duration if you have a performance requirement
        // assertTrue(duration < MAX_ACCEPTABLE_DURATION);
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
