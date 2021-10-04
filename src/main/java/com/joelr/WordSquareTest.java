package com.joelr;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class WordSquareTest {

    private WordSquare wordSquare;

    @Test
    public void shouldRemoveDuplicates() {

        String original = "aaabbbcccddd";

        wordSquare = new WordSquare();
        String noDupes = wordSquare.removeDuplicates(original);

        assertEquals("abcd", noDupes);
    }

}