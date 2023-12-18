package de.hawhamburg.hamann.ad.trees.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Analyzer {

    public Analyzer () {

    }
    public static void main (String[] args) throws IOException{
        analyzer();
    }
    static public void analyzer () throws IOException {
        List<Integer> uniqueRandomNumbers = new ArrayList<Integer>();
        BSTree<Integer, Integer> bsTree; // Anstatt BinarySearchTree<Integer, Integer> bsTree;
        List<Integer> measurements = new ArrayList<>();
        int measureAverage = 0;

        final String csvPath = "plots/averagePathLength.csv";

        try (FileWriter writer = new FileWriter(csvPath)) {

            writer.write("numberOfKeys, averagePathLength" + "\n");


            for (int n = 100; n <= 10_000; n += 100) {

                System.out.println(n/100);
                measurements.clear();
                for (int i = 0; i < 1000; i++) {
                    bsTree = new BSTree<Integer, Integer>();
                    uniqueRandomNumbers.clear();


                    for (int r = 0; r < n; r++) {

                        uniqueRandomNumbers.add(r);
                    }
                    Collections.shuffle(uniqueRandomNumbers);
                    for (int randomNumber : uniqueRandomNumbers) {

                        bsTree.insert(randomNumber, randomNumber);
                    }

                    measurements.add(bsTree.averagePathLength());
                }

                for (int measurement : measurements) {
                    measureAverage += measurement;
                }
                measureAverage /= measurements.size();
                writer.write(n + "," + measureAverage + "\n");
            }
        }
    }
}
