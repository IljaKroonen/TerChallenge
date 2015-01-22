package com.m2dl.toulousependu.game;

import java.text.Normalizer;
import java.util.Locale;

/**
 * In-memory representation of a game.
 * Created by Ilja on 22/01/2015.
 */
public class Game {
    private static final int LIVES = 6;

    private char[] word;
    private char[] wordWithoutAccents;
    private char[] found;
    private int remainingLives;

    public Game(String word) {
        String upper = word.toUpperCase(Locale.FRANCE);
        this.word = upper.toCharArray();
        word = Normalizer.normalize(word, Normalizer.Form.NFD);
        word = word.replaceAll("[^\\p{ASCII}]", "");
        wordWithoutAccents = word.toCharArray();
        found = new char[this.word.length];
    }

    public char[] getCurrentWord() {
        return found;
    }

    public char[] getFinishedWord() {
        return word;
    }

    public int getScore() {
        return 0;
    }

    public boolean isFinished() {
        if (remainingLives >= LIVES) {
            return true;
        }

        for (int i = 0; i < found.length; i++) {
            if (found[i] == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isVictory() {
        for (int i = 0; i < found.length; i++) {
            if (found[i] == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean inputLetter(char l) {
        if (isFinished()) {
            throw new RuntimeException("Game was already finished");
        }
        boolean present = false;

        for (int i = 0; i < wordWithoutAccents.length; i++) {
            if (l == wordWithoutAccents[i]) {
                found[i] = word[i];
                present = true;
            }
        }

        if (!present) {
            remainingLives++;
        }

        return present;
    }
}
