package com.mycompany.jgarble;

import java.io.*;
import java.util.*;

public class StatsUpdater {
    public static void main(String[] args) throws Exception {

    }

    /**
     * This method writes the string "win" to the file passed in as a parameter.
     * 
     * @param fileName The name of the file to write to.
     * @throws Exception if an error occurs while writing to the file.
     */
    public static void win() throws Exception {
        FileWriter writer = new FileWriter(new File("stats.txt"), true);
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
    public static void lose() throws Exception {
        FileWriter writer = new FileWriter(new File("stats.txt"), true);
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
    public static int gamesPlayed() {
        File file = new File("stats.txt");

        int lines = 0;

        try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

            while (lnr.readLine() != null)
                ;

            lines = lnr.getLineNumber();

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
    private static ArrayList<String> fileToArrayList() throws Exception {
        ArrayList<String> listOfStrings = new ArrayList<String>();
        BufferedReader bf = new BufferedReader(new FileReader("stats.txt"));
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
     * @return An array containing stats.
     * @throws Exception if an error occurs while reading from the file.
     */
    public static int[] getStats() throws Exception {
        ArrayList<String> arr = fileToArrayList();
        int longestStreak = 0;
        int curStreak = 0;
        int totalWins = 0;
        int winPercentage = 0;

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
        
        if (arr.size() > 0) {
            winPercentage = (int)(((double)totalWins / (double)arr.size()) * 100);
        }

        int[] streaks = { arr.size(), totalWins, longestStreak, curStreak, winPercentage};

        return streaks;
    }
    
    public static void deleteBrowserHistory() throws FileNotFoundException, IOException {
        FileOutputStream writer = new FileOutputStream("stats.txt");
        writer.write(("").getBytes());
        writer.close();
    }
}
