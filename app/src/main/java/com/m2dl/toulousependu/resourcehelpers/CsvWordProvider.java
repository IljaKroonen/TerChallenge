package com.m2dl.toulousependu.resourcehelpers;

import android.content.Context;

import java.io.BufferedReader;
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
        InputStream is = context.getResources().openRawResource(resource);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        List<Row> rows = new ArrayList<>();
        int maxDifficultyOccurences = 0;

        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] spl = line.split(separator);
                Row row = new Row(spl);
                rows.add(row);
                if (maxDifficultyOccurences < row.occurences) {
                    maxDifficultyOccurences = row.occurences;
                }
            }
        } finally {
            is.close();
        }

        for (int i = 1; i <= 4; i++) {
            wordsByDifficulty.put(i, new ArrayList<String>());
        }

        for (Row r : rows) {
            if (r.occurences < maxDifficultyOccurences / 4) {
                wordsByDifficulty.get(4).add(r.word);
            } else if (r.occurences < 2 * maxDifficultyOccurences / 4) {
                wordsByDifficulty.get(3).add(r.word);
            } else if (r.occurences < 3 * maxDifficultyOccurences / 4) {
                wordsByDifficulty.get(2).add(r.word);
            } else {
                wordsByDifficulty.get(1).add(r.word);
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
            word = spl[2];
            occurences = Integer.parseInt(spl[3]);
        }

        public String word;
        public int occurences;
    }
}
