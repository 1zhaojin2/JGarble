package com.mycompany.jgarble;
import java.util.ArrayList;
import java.awt.Color;

public class Garble {

    //initializes all the variables
    public String word;
    private boolean gameEnd;
    private ArrayList<String> guessWords;
    public static ArrayList<String> correctLetters;
    public static ArrayList<String> misplacedLetters;
    public static ArrayList<String> excludedLetters;
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
        gameEnd = false;
        guessWords = new ArrayList<String>();
        colorOfLetters = new ArrayList<Color>();
        correctLetters = new ArrayList<String>();
        misplacedLetters = new ArrayList<String>();
        excludedLetters = new ArrayList<String>();
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

    /**
     * get the color for each letter. Green for right color, yellow for misplaced and grey for excluded letters
     *
     * @param index index of the letter of the word
     * @param c the letter being checked
     * @return
     */
    private Color getColor(int index, String c) {
        if (word.substring(index, index + 1).equals(c)) {
            if (!correctLetters.contains(c)) {
                correctLetters.add(c);
            }
            if (misplacedLetters.contains(c)) {
                misplacedLetters.remove(c);
            }
            return new Color(83,141,78);
        }
        else if (word.contains(c)) {
            if (!misplacedLetters.contains(c)) {
                misplacedLetters.add(c);
            }
            return new Color(181, 159, 59);
        }
        if (excludedLetters.contains(c)) {
            excludedLetters.add(c);
        }
        return new Color(94, 94, 98);
    }

    /**
     * Method for taking in a player's guess for the word and updating the game state accordingly
     * @param s - the player's guess
     */
    public void takeGuess(String s) {
        colorOfLetters.clear();

        if (!gameEnd) {
            if (validGuess(s)) {
                guessWords.add(s);

                for (int i = 0; i < word.length(); i++) {
                    colorOfLetters.add(getColor(i, s.substring(i, i + 1)));
                }
            }
            if (s.equals(word)) {
                gameEnd = true;
                hasGameEnded = true;
            }
        }
    }

    /**
     * 
     * @return
     */
    public ArrayList<String> getGuess() {
        return guessWords;
    }

    /**
     * get the word
     * @return word the word being returned
     */
    public String getWord() {
        return word;
    }

    /**
     * get the arraylist of color of letters
     * @return colorOfLetters the arraylist of colors
     */
    public ArrayList<Color> getColors() {
        return colorOfLetters;
    }

    /**
     * ends the game
     * @return gameEnd
     */
    public boolean end() {
        return gameEnd;
    }
}
