/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jgarble;

import java.io.*;
import java.util.*;

public class StatsUpdater {
    public static void main(String[] args) throws Exception {

        int[] streaks = getStats("stats.txt");
        for (int i : streaks) {
            System.out.println(i);
        }
    }

    /**
     * This method writes the string "win" to the file passed in as a parameter.
     * 
     * @param fileName The name of the file to write to.
     * @throws Exception if an error occurs while writing to the file.
     */
    public static void win(String fileName) throws Exception {
        FileWriter writer = new FileWriter(new File(fileName), true);
        writer.append("win");
        writer.append("\n");
        writer.close();
    }

    /**
     * This method writes the string "lose" to the file passed in as a parameter.
     * 
     * @param fileName The name of the file to write to.
     * @throws Exception if an error occurs while writing to the file.
     */
    public static void lose(String fileName) throws Exception {
        FileWriter writer = new FileWriter(new File(fileName), true);
        writer.append("lose");
        writer.append("\n");
        writer.close();
    }

    /**
     * This method calculates the number of lines in the file passed in as a
     * parameter.
     * 
     * @param fileName The name of the file to read from.
     * @return The number of lines in the file.
     */
    public static int gamesPlayed(String fileName) {
        File file = new File(fileName);

        int lines = 0;

        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

            while (lnr.readLine() != null) {
                lines = lnr.getLineNumber();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * This method reads the given file and returns an ArrayList of strings.
     * 
     * @param fileName The name of the file to read from.
     * @return An Array
     */
    private static ArrayList<String> fileToArrayList(String fileName) throws Exception {
        ArrayList<String> listOfStrings = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        String line = bf.readLine();
        while (line != null) {
            listOfStrings.add(line);
            line = bf.readLine();
        }

        bf.close();
        return listOfStrings;
    }

    /**
     * This method reads the given file and returns an array containing stats for a
     * game such as the number of games played, total wins, longest streak, and
     * current streak.
     * 
     * @param fileName The name of the file to read from.
     * @return An array containing stats.
     * @throws Exception if an error occurs while reading from the file.
     */
    public static int[] getStats(String fileName) throws Exception {
        ArrayList<String> arr = fileToArrayList(fileName);
        int longestStreak = 0;
        int curStreak = 0;
        int totalWins = 0;

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).equals("win")) {
                curStreak++;
                totalWins++;
            } else if (arr.get(i).equals("lose")) {
                curStreak = 0;
            }
            if (curStreak > longestStreak) {
                longestStreak = curStreak;
            }
        }

        int[] streaks = { arr.size(), totalWins, longestStreak, curStreak };

        return streaks;
    }
}
