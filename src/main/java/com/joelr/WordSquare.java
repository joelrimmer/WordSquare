package com.joelr;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordSquare {

    private int noOfWordsForSquare;
    private String regexForWordSearch;
    private List<String> availableWords;

    private String word1 = "";
    private String word2 = "";
    private String word3 = "";
    private String word4 = "";

    private int i = 0;

    private int index0 = 0;
    private int index1 = 1;
    private int index2 = 2;
    private int index3 = 3;

    private boolean wordSquareNotComplete = true;

    public WordSquare() {
    }

    public void runWordSquare() {

        System.out.println("Number of potential words to fill grid with: " + availableWords.size());

        //attempt to build the wordSquare whilst it is incomplete
        do {
            buildGrid();
        } while (wordSquareNotComplete);

        // TODO: 04/10/2021 count chars used per word set against those supplied to filter out valid combinations and restart to the gridBuild

        System.out.println(word1);
        System.out.println(word2);
        System.out.println(word3);
        System.out.println(word4);
    }

    //take users input
    void getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Word Square. Please input your code:  ");

        //split input into length or word/square size & list of list of characters given
        String[] input = scanner.nextLine().split(" ");
        noOfWordsForSquare = Integer.parseInt(input[0]);
        String characterSupplied = input[1];

        //remove duplicate characters then populate regex which unique characters and wordLength
        String charactersUnique = removeDuplicates(characterSupplied);

        regexForWordSearch = "[" + charactersUnique + "]{0," + noOfWordsForSquare + "}";
    }

    public String removeDuplicates(String characters) {
        return characters.chars().distinct().mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining());
    }

    //create list of valid words for square using CSV file of words provided and filter using regex created
    void getListOfWordsForSquare() throws IOException {
        CSVReader reader = new CSVReader();
        availableWords = reader.getListOfWords("words.csv", noOfWordsForSquare);
        availableWords.removeIf(word -> !word.matches(regexForWordSearch));

        //two CSV files to show exceptions
//        availableWords = reader.getListOfWords("wordsEmpty.csv", noOfWordsForSquare);
//        availableWords = reader.getListOfWords("wordsNoFourLetterWords.csv", noOfWordsForSquare);

    }

    //create and populate word square
    boolean buildGrid() {

        boolean word2set = false;
        boolean word3set = false;
        boolean word4set = false;

        //set word1 to be first in list, would increment through valid words depending on other filters using ++
        word1 = availableWords.get(i++);

        //if word2 not set, try and set it. Return false and exit to do while
        if (!word2set) {
            word2set = setWordTwo(word1);
        }
        if (!word2set) {
            return false;
        }

        //if word3 not set, try and set it. Return false and exit to do while
        if (!word3set) {
            word3set = setWordThree(word1, word2);
        }
        if (!word3set) {
            return false;
        }

        //if word4 not set, try and set it. Return false and exit to do while
        if (!word4set) {
            word4set = setWordFour(word1, word2, word3);
        }
        if (!word4set) {
            return false;
        }

        //basic check to exit loop
        if (word4set) {
            wordSquareNotComplete = false;
        }

        return true;
    }

    private boolean setWordTwo(String word1) {

        int j = 0;
        boolean word2set = true;

        do {
            if (j < availableWords.size()) {
                String potentialWord = availableWords.get(j++);
                if (potentialWord.charAt(index0) == word1.charAt(index1)) {
                    word2 = potentialWord;
                    return true;
                }
            }
            if (j >= availableWords.size()) {
                return false;
            }
        } while (word2set);
        return false;
    }

    private boolean setWordThree(String word1, String word2) {

        int j = 0;
        boolean word3set = true;

        do {
            if (j < availableWords.size()) {
                String potentialWord = availableWords.get(j++);
                if (potentialWord.charAt(index0) == (word1.charAt(index2))) {
                    if (potentialWord.charAt(index1) == word2.charAt(index2)) {
                        word3 = potentialWord;
                        return true;
                    }
                }
            }
            if (j >= availableWords.size()) {
                return false;
            }
        } while (word3set);
        return false;
    }

    private boolean setWordFour(String word1, String word2, String word3) {

        int j = 0;
        boolean word4set = true;

        do {
            if (j < availableWords.size()) {
                String potentialWord = availableWords.get(j++);
                if (potentialWord.charAt(index0) == (word1.charAt(index3))) {
                    if (potentialWord.charAt(index1) == word2.charAt(index3)) {
                        if (potentialWord.charAt(index2) == word3.charAt(index3)) {
                            word4 = potentialWord;
                            return true;
                        }
                    }
                }
            }
            if (j >= availableWords.size()) {
                return false;
            }
        } while (word4set);
        return false;
    }

}


