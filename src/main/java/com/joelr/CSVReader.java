package com.joelr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private static List<String> availableWords = new ArrayList<>();

    //read file of words supplied and create list based on word length being equal to input value
    public static List<String> getListOfWords(String fileName, int wordLength) throws IOException {

        List<String> wordsSupplied = Files.readAllLines(Paths.get(fileName));

        if (wordsSupplied.isEmpty()) {
            throw new IllegalArgumentException("Word file supplied is empty. Please check your data");
        }

        for (String word : wordsSupplied) {
            if (word.length() == wordLength) {
                availableWords.add(word);
            }
        }

        if (availableWords.isEmpty()) {
            throw new IllegalArgumentException("No valid words of length supplied");
        }

        return availableWords;
    }

}
