package com.m2dl.toulousependu.game;

import java.util.Locale;

/**
 * In-memory representation of a game.
 * Created by Ilja on 22/01/2015.
 */
public class Game {
    private char[] word;
    private char[] found;

    public Game(String word) {
        String upper = word.toUpperCase(Locale.FRANCE);
        this.word = upper.toCharArray();
        found = new char[this.word.length];
    }

    public char[] getCurrentWord() {
        return found;
    }

    public int getScore() {
        return 0;
    }

    public boolean inputLetter(char l) {
        return false;
    }
}
