package de.hawhamburg.hamann.ad.trees.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


public class PathSearchTest {

    public static final int EDGE_MULTIPLICATOR = 40;
    private Graph<Integer, Integer> graph;
    private PathSearch<Integer> pathSearch;
    Map<Integer, Integer> expectedDistances;
    ;


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
        expectedDistances.put(1, 0);
        expectedDistances.put(2, 1);
        expectedDistances.put(3, 1);
        expectedDistances.put(4, 2);
        expectedDistances.put(5, 3);
        expectedDistances.put(6, 4);
        expectedDistances.put(7, 4);
        expectedDistances.put(8, 4);
    }

    @Test
    public void testDijkstraCorrectness() {
        Map<Integer, Integer> actualDistances = pathSearch.dijkstra(1);

        assertEquals(expectedDistances, actualDistances, "Dijkstra algorithm should return correct shortest paths.");
    }


    private Graph<Integer, Integer> createGraph(int size) {
        Graph<Integer, Integer> graph = new Graph<>();
        graph.addNode(0);
        for (int i = 1; i < size; i++) {
            graph.addNode(i);
            graph.addEdge(i - 1, i, 1);
        }
        return graph;
    }

    private void addEdges(Graph<Integer, Integer> graph, int size, int edgeMultiplicator) {
        for (int i = 2; i < size; i++) {
            for (int j = 1; j <= edgeMultiplicator && i + j < size; j++)
                graph.addEdge(i, i + j, 1);
        }
    }

    @Test
    public void testDijkstraPerformanceEdges() {
        System.out.println("numberOfNodes,nanoTime");
        for (int i = 50; i < 2000; i += 50) {
            Graph<Integer, Integer> testGraph = createGraph(i);
            PathSearch<Integer> pathSearch = new PathSearch<>(testGraph);
            long startTime = System.nanoTime();
            pathSearch.dijkstra(0);
            long endTime = System.nanoTime();
            long duration = endTime - startTime;
            System.out.printf("%d,%d\n", i, duration);
        }
    }

    @Test
    public void testDijkstraPerformanceMoreEdges() {
        System.out.println("numberOfNodes,numberOfEdges,nanoTime");
        for (int i = 50; i < 2000; i += 50) {

           for(int EDGE_MULTIPLICATOR =10; EDGE_MULTIPLICATOR <= 100; EDGE_MULTIPLICATOR+=20 ) {
               Graph<Integer, Integer> testGraph = createGraph(i);
               addEdges(testGraph, i, EDGE_MULTIPLICATOR);
               PathSearch<Integer> pathSearch = new PathSearch<>(testGraph);
               long startTime = System.nanoTime();
               pathSearch.dijkstra(0);
               long endTime = System.nanoTime();
               long duration = endTime - startTime;
               System.out.printf("%d,%d,%d%n", i, EDGE_MULTIPLICATOR/10, duration);
           }
        }
    }


    @Test
    public void testDijkstraPerformanceNodes() {
        long startTime = System.nanoTime();
        pathSearch.dijkstra(1);
        long endTime = System.nanoTime();
        //todo laufzeit dijktra
        long duration = endTime - startTime;
        System.out.println("Execution Time for Dijkstra: " + duration + " nanoseconds");
    }

    @Test
    public void testDijkstraPerformance2() {

        graph = new Graph<>();
        // Add nodes. For simplicity, let's assume nodes are integers.
        for (int i = 1; i <= 5000; i++) {
            graph.addNode(i);
            graph.addNode(i + 5000);
            if (i > 1) graph.addEdge(i, i - 1, 5);
            pathSearch = new PathSearch<>(graph);
            long startTime = System.nanoTime();
            pathSearch.dijkstra(5000);
            long endTime = System.nanoTime();
            //todo laufzeit dijktra
            long duration = endTime - startTime;
            System.out.println("ersteRunde" + duration + "," + i);
        }


        for (int i = 50; i <= 4500; i++) {
            graph.addNode(i);
            graph.addNode(i + 5000);
            if (i > 1) graph.addEdge(i + 500, i, 5);

            pathSearch = new PathSearch<>(graph);
            long startTime = System.nanoTime();
            pathSearch.dijkstra(5000);
            long endTime = System.nanoTime();
            //todo laufzeit dijktra
            long duration = endTime - startTime;
            System.out.println("zweiteRunde" + duration + "," + i);
        }
    }

    @Test
    public void testDijkstraWithInvalidInput() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            pathSearch.dijkstra(-1); // Assuming '-1' is not a valid node
        });

        String expectedMessage = "No such node in the graph";
        String actualMessage = exception.getMessage();

        //assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBreadthFirstSearchCorrectness() {


        Map<Integer, Integer> actualDistances = pathSearch.breadthSearch(1);

        assertEquals(expectedDistances, actualDistances, "BFS should return correct distances.");
    }

    @Test
    public void testDepthFirstSearchCorrectness() {

        Map<Integer, Integer> actualDepths = pathSearch.depthSearch(1);
        expectedDistances.put(3, 3);

        assertEquals(expectedDistances, actualDepths, "DFS should return correct depths.");
    }
}
