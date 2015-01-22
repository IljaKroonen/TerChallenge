package com.m2dl.toulousependu.resourcehelpers;

/**
 * Created by Ilja on 22/01/2015.
 */
public interface IWordProvider {
    /**
     * Retrieves a random word of the desired difficulty.
     * @param difficulty The difficulty of the desired word, an integer between 1 and 4. 1 is the
     *                   easiest difficulty, 4 the hardest.
     * @return A word.
     */
    String getWord(int difficulty);
}
