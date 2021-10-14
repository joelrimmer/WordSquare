package com.joelr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordSquare {

    private int noOfWordsForSquare;
    private String regexForWordSearch;
    private List<String> availableWords;
    private String characterSupplied;

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

        //attempt to build the wordSquare whilst it is incomplete
        do {
            buildGrid();
        } while (wordSquareNotComplete);

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
        characterSupplied = input[1];

        //remove duplicate characters then populate regex which unique characters and wordLength
        String charactersUnique = removeDuplicates(characterSupplied);

        regexForWordSearch = "[" + charactersUnique + "]{0," + noOfWordsForSquare + "}";
    }

    public String removeDuplicates(String characters) {
        return characters.chars().distinct().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.joining());
    }

    //create list of valid words for square using CSV file of words provided and filter using regex created
    void getListOfWordsForSquare() throws IOException {
        CSVReader reader = new CSVReader();
        availableWords = reader.getListOfWords("wordsForDebug2.csv", noOfWordsForSquare);
        availableWords.removeIf(word -> !word.matches(regexForWordSearch));

        //two CSV files to show exceptions
//        availableWords = reader.getListOfWords("wordsEmpty.csv", noOfWordsForSquare);
//        availableWords = reader.getListOfWords("wordsNoFourLetterWords.csv", noOfWordsForSquare);

    }

    //create and populate word square
    boolean buildGrid() {

        String input = characterSupplied;

        boolean wordTwoNotSet = true;
        boolean word3set = false;
        boolean word4set = false;


        //set word1 to be first in list, would increment through valid words depending on other filters using ++

        word1 = availableWords.get(i++);
        if (doesInputContainWord(input, word1)) {
            input = removeWordCharacters(input, word1);
        } else {
            return false;
        }

        //if word2 not set, try and set it. Return false and exit to do while
        if (wordTwoNotSet) {
            wordTwoNotSet = setWordTwo(input, word1);
            input = removeWordCharacters(input, word2);
        }

        if (!wordTwoNotSet) {
            return false;
        }

        //if word3 not set, try and set it. Return false and exit to do while
        if (!word3set) {
            word3set = setWordThree(input, word1, word2);
            input = removeWordCharacters(input, word3);
        }
        if (!word3set) {
            return false;
        }

        //if word4 not set, try and set it. Return false and exit to do while
        if (!word4set) {
            word4set = setWordFour(input, word1, word2, word3);
        }
        if (!word4set) {
            return false;
        }

        //basic check to exit loop
        if (word4set) {
            wordSquareNotComplete = false;
        } else {
            return false;
        }

        return true;
    }

    private boolean setWordTwo(String input, String word1) {

        int j = 0;
        boolean word2set = true;
        List<String> availableWordsForWordTwo = new ArrayList<>(availableWords);
        availableWordsForWordTwo.remove(word1);

        do {
            if (j < availableWordsForWordTwo.size()) {
                String potentialWord = availableWordsForWordTwo.get(j++);
                if (potentialWord.charAt(index0) == word1.charAt(index1)) {
                    if (doesInputContainWord(input, potentialWord)) {
                        input = removeWordCharacters(input, potentialWord);
                        word2 = potentialWord;
                        return true;
                    }
                }
            }
            if (j >= availableWordsForWordTwo.size()) {
                return false;
            }
        } while (word2set);
        return false;
    }

    private boolean setWordThree(String input, String word1, String word2) {

        int j = 0;
        boolean word3set = true;
        List<String> availableWordsForWordThree = new ArrayList<>(availableWords);
        availableWordsForWordThree.remove(word1);
        availableWordsForWordThree.remove(word2);

        do {
            if (j < availableWordsForWordThree.size()) {
                String potentialWord = availableWordsForWordThree.get(j++);
                if (potentialWord.charAt(index0) == (word1.charAt(index2))) {
                    if (potentialWord.charAt(index1) == word2.charAt(index2)) {
                        if (doesInputContainWord(input, potentialWord)) {
                            input = removeWordCharacters(input, potentialWord);
                            word3 = potentialWord;
                            return true;
                        }
                    }
                }
            }
            if (j >= availableWordsForWordThree.size()) {
                return false;
            }
        } while (word3set);
        return false;
    }

    private boolean setWordFour(String input, String word1, String word2, String word3) {

        int j = 0;
        boolean word4set = true;
        List<String> availableWordsForWordFour = new ArrayList<>(availableWords);
        availableWordsForWordFour.remove(word1);
        availableWordsForWordFour.remove(word2);
        availableWordsForWordFour.remove(word3);

        do {
            if (j < availableWordsForWordFour.size()) {
                String potentialWord = availableWordsForWordFour.get(j++);
                if (potentialWord.charAt(index0) == (word1.charAt(index3))) {
                    if (potentialWord.charAt(index1) == word2.charAt(index3)) {
                        if (potentialWord.charAt(index2) == word3.charAt(index3)) {
                            if (doesInputContainWord(input, potentialWord)) {
                                input = removeWordCharacters(input, potentialWord);
                                word4 = potentialWord;
                                return true;
                            }
                        }
                    }
                }
            }
            if (j >= availableWordsForWordFour.size()) {
                return false;
            }
        } while (word4set);
        return false;
    }

    static String removeWordCharacters(String input, String word) {

        for (char letter : word.toCharArray()) {
            input = input.replaceFirst(String.valueOf(letter), "");
        }

        return input;
    }

    static boolean doesInputContainWord(String input, String word) {

        for (char letter : word.toCharArray()) {
            if (input.contains(String.valueOf(letter))) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

}


