package com.example.models;

import com.example.services.WordService;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Ben on 4/14/17.
 */
public class Game {
    @JsonIgnore
    private String word;
    private String currentWord;
    private int remainingGuesses;

    private Game(String word) {
        this.word = word;

        StringBuilder sb = new StringBuilder();

        for (int i = 0;i < word.length();i++) {
            sb.append("_");
        }

        currentWord = sb.toString();
        this.remainingGuesses = 7;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public void setRemainingGuesses(int remainingGuesses) {
        this.remainingGuesses = remainingGuesses;
    }

    public String guessLetter(String letter) {
        if (word.indexOf(letter) == -1) {
            // letter not found
            remainingGuesses--;
        } else {
            StringBuilder sb = new StringBuilder(currentWord);

            for (int i = 0;i < word.length();i++) {
                if (word.substring(i, i + 1).equalsIgnoreCase(letter)) {
                    sb.setCharAt(i, letter.charAt(0));
                }
            }

            currentWord = sb.toString();
        }

        return currentWord;
    }

    public static Game createGame(WordService service) {
        // lookup word
        return new Game(service.getRandomWord());
    }
}
