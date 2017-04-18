package com.example.models;

import com.example.services.WordService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ben on 4/14/17.
 */
public class Game {
    private String word;
    private Set<Character> guesses = new HashSet<>();
    private int remainingGuesses;

    private Game(String word) {
        this.word = word;

        StringBuilder sb = new StringBuilder();

        for (int i = 0;i < word.length();i++) {
            sb.append("_");
        }

        this.remainingGuesses = 7;
    }

    public String getWord() {
        if (remainingGuesses == 0) {
            return word;
        }

        StringBuilder sb = new StringBuilder(word);

        for (int i = 0;i < word.length();i++) {
            if (!guesses.contains(sb.charAt(i))) {
                sb.setCharAt(i, '_');
            }
        }
        return sb.toString();
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Set<Character> getGuesses() {
        return guesses;
    }

    public void setGuesses(Set<Character> guesses) {
        this.guesses = guesses;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public void setRemainingGuesses(int remainingGuesses) {
        this.remainingGuesses = remainingGuesses;
    }

    public void guessLetter(String letter) {
        if (word.indexOf(letter) == -1 && !guesses.contains(letter.charAt(0))) {
            // letter not found
            remainingGuesses--;
        }

        guesses.add(letter.charAt(0));
    }

    public static Game createGame(WordService service) {
        // lookup word
        return new Game(service.getRandomWord());
    }
}
