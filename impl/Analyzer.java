package de.hawhamburg.hamann.ad.trees.impl;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Klasse Analyzer
public class Analyzer {

    // Konstruktor
    public Analyzer () {

    }

    // Hauptmethode
    public static void main (String[] args) throws IOException {
        analyzer();
    }

    // Methode zur Analyse des BST
    static public void analyzer () throws IOException {
        // Liste zur Speicherung eindeutiger Zufallszahlen
        List<Integer> uniqueRandomNumbers = new ArrayList<Integer>();
        // Deklaration des binären Suchbaums
        BSTree<Integer, Integer> bsTree;
        // Liste zur Speicherung von Messungen
        List<Integer> measurements = new ArrayList<>();
        // Variable zur Berechnung des Durchschnitts
        int measureAverage = 0;

        // Pfad zur CSV-Datei
        final String csvPath = "plots/averagePathLength.csv";

        // Versuch, mit FileWriter zu arbeiten
        try (FileWriter writer = new FileWriter(csvPath)) {
            // Schreiben der Kopfzeile in die CSV-Datei
            writer.write("numberOfKeys, averagePathLength" + "\n");

            // Schleife über verschiedene Größen von n
            for (int n = 100; n <= 10_000; n += 100) {
                // Fortschrittsanzeige
                System.out.println(n/100);
                // Zurücksetzen der Messungen für jede Größe von n
                measurements.clear();
                // Wiederholen des Experiments 1000-mal
                for (int i = 0; i < 1000; i++) {
                    // Initialisierung des Baums
                    bsTree = new BSTree<Integer, Integer>();
                    // Zurücksetzen der Liste einzigartiger Zufallszahlen
                    uniqueRandomNumbers.clear();

                    // Hinzufügen von Zahlen 0 bis n-1 in die Liste
                    for (int r = 0; r < n; r++) {
                        uniqueRandomNumbers.add(r);
                    }
                    // Mischen der Liste, um Zufälligkeit zu gewährleisten
                    Collections.shuffle(uniqueRandomNumbers);
                    // Einfügen der Zufallszahlen in den Baum
                    for (int randomNumber : uniqueRandomNumbers) {
                        bsTree.insert(randomNumber, randomNumber);
                    }

                    // Hinzufügen der durchschnittlichen Pfadlänge zur Messliste
                    measurements.add(bsTree.averagePathLength());
                }

                // Berechnung des Durchschnitts der Messungen
                for (int measurement : measurements) {
                    measureAverage += measurement;
                }
                measureAverage /= measurements.size();
                // Schreiben der Ergebnisse in die CSV-Datei
                writer.write(n + "," + measureAverage + "\n");
            }
        }
    }
}
