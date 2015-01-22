package com.m2dl.toulousependu.game;

import android.util.Log;

import java.text.Normalizer;
import java.util.Calendar;
import java.util.Locale;

/**
 * In-memory representation of a game.
 * Created by Ilja on 22/01/2015.
 */
public class Game implements IGame {
    private static final int LIVES = 6;
    private static final int MISS_SCORE_PENALTY = 10;

    private char[] word;
    private char[] wordWithoutAccents;
    private char[] found;
    private int remainingLives = LIVES;
    private Calendar startTime = Calendar.getInstance();
    private Calendar stopTime;

    public Game(String word) {
        word = word.toUpperCase(Locale.FRANCE);
        this.word = word.toCharArray();
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
        Calendar c = stopTime;
        if (c == null) {
            c = Calendar.getInstance();
        }

        return (int)(MISS_SCORE_PENALTY * (LIVES - remainingLives) +
                (c.getTimeInMillis() - startTime.getTimeInMillis()) / 1000);
    }

    public int getRemainingLives() {
        return remainingLives;
    }

    public boolean isFinishedP() {
        if (remainingLives <= 0) {
            return true;
        }

        for (int i = 0; i < found.length; i++) {
            if (found[i] == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean isFinished() {
        return stopTime != null;
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
            remainingLives--;
        }

        if (isFinishedP()) {
            stopTime = Calendar.getInstance();
        }

        return present;
    }
}
