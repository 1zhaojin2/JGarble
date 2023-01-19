package com.mycompany.jgarble;

import java.io.*;
import java.util.*;

public class WordReader {

    /**
     *  This method reads from a file called wordBank.txt and returns a random word from the file.
     *
     * @throws Exception if an error occurs while reading from the file
     * @return A random word from the wordBank.txt file
     */
    public static String randomWord() throws Exception {
        // get the inputstream of the wordBank.txt file
        InputStream in = WordReader.class.getClassLoader().getResourceAsStream("wordBank.txt");
        List<String> l = new ArrayList<>();
        try (Scanner scanner = new Scanner(in)) {
            while (scanner.hasNextLine()) {
                l.add(scanner.nextLine());
            }
        }
        Random random = new Random();
        return l.get(random.nextInt(l.size()));
    }

    /**
     * This method checks if a word is valid by reading from the wordBank.txt file and 
     * comparing the word passed as a parameter to each line in the file.
     * 
     * @param word the word that needs to be checked
     * @throws Exception if an error occurs while reading from the file
     * @return true if the word is found in the wordBank.txt file, false otherwise
     */
    public static boolean validWord(String word) throws Exception {
        // get the inputstream of the wordBank.txt file
        InputStream in = WordReader.class.getClassLoader().getResourceAsStream("wordBank.txt");
        try (Scanner scanner = new Scanner(in)) {
            while (scanner.hasNextLine()) {
                if (scanner.nextLine().equals(word)) {
                    return true;
                }
            }
        }
        return false;
    }
}