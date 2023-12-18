package de.hawhamburg.hamann.ad.trees.impl;

import de.hawhamburg.hamann.ad.trees.BinarySearchTree;

import java.io.BufferedWriter;
import java.io.File;
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

        final String csvPath = "plots/averagePathLength.csv";

        try (FileWriter writer = new FileWriter(csvPath)) {

            writer.write("numberOfKeys, averagePathLength" + "\n");

            for (int q = 0; q < 100; q++) {

                bsTree = new BSTree<Integer, Integer>();

                for (int i = 0; i < 1000; i++) {

                    uniqueRandomNumbers.add(i);
                }
                Collections.shuffle(uniqueRandomNumbers);
                for (int i = 0; i < 1000; i++) {

                    bsTree.insert(i, i);
                }

                writer.write(q*100 + "," + bsTree.averagePathLength() + "\n");
            }
        }
    }
}
