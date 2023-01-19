package com.mycompany.jgarble;


import java.io.*;
import java.util.*;

public class WordReader {
    public static void main(String[] args) throws Exception {
    }

    public static String randomWord() throws Exception {
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
     * 
     * 
     * @param word
     * @return
     * @throws Exception
     */
    public static boolean validWord(String word) throws Exception {
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
