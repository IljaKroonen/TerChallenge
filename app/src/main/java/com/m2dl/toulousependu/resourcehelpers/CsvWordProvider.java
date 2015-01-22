package com.m2dl.toulousependu.resourcehelpers;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Ilja on 22/01/2015.
 */
public class CsvWordProvider implements IWordProvider {
    private static final String separator = ";";

    private Random random = new Random();
    private Map<Integer, List<String>> wordsByDifficulty = new HashMap<>();

    /**
     * Loads all the words in the specified CSV file into memory to construct this CsvWordProvider.
     * @param context The context used to retrieve the resource
     * @param resource The resource id of the csv file
     */
    public CsvWordProvider(Context context, int resource) throws IOException {
        InputStream is;
        try {
            is = new FileInputStream("/sdcard/prenomsopentoulouse.csv");
        } catch (Exception e) {
            is = context.getResources().openRawResource(resource);
        }
        is = context.getResources().openRawResource(resource);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
        reader.readLine();

        List<Row> rows = new ArrayList<>();

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                String[] spl = line.split(separator);
                Row row = new Row(spl);
                rows.add(row);
            }
        } finally {
            is.close();
        }

        for (int i = 1; i <= 4; i++) {
            wordsByDifficulty.put(i, new ArrayList<String>());
        }

        Map<String, Integer> occurences = new HashMap<>();
        int maxDifficultyOccurences = 0;

        // Truly inefficient =)
        for (Row r : rows) {
            if (occurences.get(r.word) == null) {
                occurences.put(r.word, r.occurences);
            } else {
                occurences.put(r.word, occurences.get(r.word) + r.occurences);
            }
            if (occurences.get(r.word) > maxDifficultyOccurences) {
                maxDifficultyOccurences = occurences.get(r.word);
            }
        }

        for (Map.Entry<String, Integer> entry : occurences.entrySet()) {
            if (entry.getValue() < maxDifficultyOccurences / 50) {
                wordsByDifficulty.get(4).add(entry.getKey());
            } else if (entry.getValue() < maxDifficultyOccurences / 10) {
                wordsByDifficulty.get(3).add(entry.getKey());
            } else if (entry.getValue() < maxDifficultyOccurences / 5) {
                wordsByDifficulty.get(2).add(entry.getKey());
            } else {
                wordsByDifficulty.get(1).add(entry.getKey());
            }
        }
    }

    /**
     * Retrieves a random word of a certain difficulty.
     * @param difficulty The difficulty level (1-4)
     * @return
     */
    @Override
    public String getWord(int difficulty) {
        int r = random.nextInt(wordsByDifficulty.get(difficulty).size());
        return wordsByDifficulty.get(difficulty).get(r);
    }

    private class Row {
        public Row(String[] spl) {
            word = spl[1];
            occurences = Integer.parseInt(spl[2]);
        }

        public String word;
        public int occurences;
    }
}
