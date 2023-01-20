package com.mycompany.jgarble;

import java.util.*;
import java.awt.Color;

public class Garble {

    // initializes all the variables
    public String word;
    private int numOfGuesses;
    private boolean gameEnd;
    private ArrayList<String> guessedLetters;
    private ArrayList<Color> colorOfLetters;

    public static String staticWord = "";

    public static boolean hasGameEnded = false;

    /**
     * Initialize a newly created wordle object
     * 
     * @param word the word player tries to guess
     */
    public Garble(String word) {
        this.word = word;
        guessedLetters = new ArrayList<String>();
        colorOfLetters = new ArrayList<Color>();
        gameEnd = true;
        numOfGuesses = 0;
        staticWord = word;
    }

    public void setWord(String s) {
        word = s;
        staticWord = s;
    }

    /**
     * checks if the guess word is valid
     *
     * @param s the word entered by the player
     * @return
     */
    private boolean validGuess(String s) {
        if (s.length() != word.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetter(s.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    private Color[] getColor(String guess) {

        Color[] color = new Color[word.length()];

        for (int i = 0; i < guess.length(); i++) {
            if (guess.substring(i, i + 1).equals(word.substring(i, i + 1))) {
                color[i] = new Color(83, 141, 78);
                guessedLetters.add(guess.substring(i, i + 1));
            }
        }

        for (int j = 0; j < guess.length(); j++) {
            String c = guess.substring(j, j + 1);
            char cChecker = guess.charAt(j);
            if (word.contains(guess.substring(j, j + 1)) && color[j] == null) {
                if (guessedLetters.stream().filter(ch -> ch.equals(c)).count() < word.chars()
                        .filter(ch -> ch == cChecker).count()) {
                    color[j] = new Color(181, 159, 59);
                    guessedLetters.add(guess.substring(j, j + 1));
                } else {
                    color[j] = new Color(94, 94, 98);
                }
                guessedLetters.add(guess.substring(j, j + 1));
            }
        }

        for (int k = 0; k < guess.length(); k++) {
            if (!word.contains(guess.substring(k, k + 1))) {
                color[k] = new Color(94, 94, 98);
            }
        }

        return color;
    }

    /**
     * Method for taking in a player's guess for the word and updating the game
     * state accordingly
     * 
     * @param s - the player's guess
     */
    public void takeGuess(String s) {
        if (validGuess(s)) {
            colorOfLetters = new ArrayList<Color>(Arrays.asList(getColor(s)));
            numOfGuesses++;

            if (numOfGuesses == 6 || s.equals(word)) {
                gameEnd = true;
                hasGameEnded = true;
            }
        }
    }

    /**
     * get the arraylist of color of letters
     * 
     * @return colorOfLetters the arraylist of colors
     */
    public ArrayList<Color> getColors() {
        return colorOfLetters;
    }

    /**
     * 
     * @return
     */
    public boolean hasGameEnded() {
        return gameEnd;
    }
}