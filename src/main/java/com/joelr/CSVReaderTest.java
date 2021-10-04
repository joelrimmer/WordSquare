package com.joelr;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CSVReaderTest {

    @Test
    public void throwExceptionWhenCSVHasEmptyLines() throws IllegalArgumentException {

        IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> {
            CSVReader.getListOfWords("wordsEmpty.csv", 4);
        });

        assertEquals("Word file supplied is empty. Please check your data", exception.getMessage());

    }

    @Test
    public void throwExceptionWhenCSVHasNoMatchingWordsOfLength() throws IllegalArgumentException {

        IllegalArgumentException exception =
                assertThrows(IllegalArgumentException.class, () -> {
            CSVReader.getListOfWords("wordsNoFourLetterWords.csv", 4);
        });

        assertEquals("No valid words of length supplied", exception.getMessage());

    }

    @Test
    public void shouldReturnListOfValidWords() throws IOException {

        String word1 = "aahs";
        String word2 = "aals";
        String word3 = "abas";

        List<String> testWords = Arrays.asList(word1, word2, word3);
        List<String> words = CSVReader.getListOfWords("words.csv", 4);
        words = words.subList(0, 3);

        assertEquals(testWords, words);
    }

}