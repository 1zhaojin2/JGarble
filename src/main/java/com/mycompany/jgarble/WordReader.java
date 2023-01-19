package com.mycompany.jgarble;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class WordReader {
    public static void main(String[] args) throws Exception {
    }

    public static String randomWord(String route) throws Exception {
        List<String> l;
        try {
            l = Files.readAllLines(Paths.get(route));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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
    public static boolean validWord(String word, String fileName) throws Exception {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        String Currentline;
        while (scanner.hasNextLine()) {
            Currentline = scanner.nextLine();
            if (Currentline.equals(word)) {
                scanner.close();
                return true;
            }
        }
        scanner.close();
        return false;
    }
}