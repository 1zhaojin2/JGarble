package com.mycompany.jgarble;
import java.util.ArrayList;
import java.awt.Color;

public class Garble {
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
     * 
     * @param s
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
     * 
     * @param s
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
     * 
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     * 
     * @return
     */
    public ArrayList<Color> getColors() {
        return colorOfLetters;
    }

    /**
     * 
     * @return
     */
    public boolean end() {
        return gameEnd;
    }
}
