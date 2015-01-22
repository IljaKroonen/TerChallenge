package com.m2dl.toulousependu.game;

import java.util.Calendar;

/**
 * Created by Ilja on 22/01/2015.
 */
public interface IGame {
    /**
     * Retrieves the word that must be displayed to the user while he plays. This array contains
     * zero characters on positions that have not been filled by the user. Can change after a call
     * to inputLetter() that returns true.
     * @return The word in the form of a char array.
     */
    char[] getCurrentWord();

    /**
     * Retrieves the word that must be displayed to the user if he loses. All positions are filled
     * with the solution letters. The array retrieved never changes.
     * @return The finished word in the form of a char array.
     */
    char[] getFinishedWord();

    /**
     * Retrieves the current score of the game. This field can change over time because the score
     * depends on time. It is recommended to refresh the view displaying this value each second and
     * each time inputLetter() returns false.
     * @return The score.
     */
    int getScore();

    /**
     * Checks if the game is finished. Must be called after each call to inputLetter().
     * @return True if the game is finished, else false.
     */
    boolean isFinished();

    /**
     * Checks if the game resulted in a victory. Calls are valid only if isFinished() previously
     * returned true.
     * @return True if the game resulted in a victory, else false.
     */
    boolean isVictory();

    /**
     * Checks in a letter into the game.
     * @param l The letter guessed by the user.
     * @return True if the letter was in the word, false if it was not.
     */
    boolean inputLetter(char l);
}
