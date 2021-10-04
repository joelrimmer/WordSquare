package com.joelr;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

//        4 eeeeddoonnnsssrv
//        4 aaccdeeeemmnnnoo
//        5 aaaeeeefhhmoonssrrrrttttw
//        5 aabbeeeeeeeehmosrrrruttvv
//        7 aaaaaaaaabbeeeeeeedddddggmmlloooonnssssrrrruvvyyy

        try {
            WordSquare wordSquare = new WordSquare();
            wordSquare.getInput();
            wordSquare.getListOfWordsForSquare();
            wordSquare.runWordSquare();
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}
