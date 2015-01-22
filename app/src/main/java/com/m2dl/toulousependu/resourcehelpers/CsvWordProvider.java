package com.m2dl.toulousependu.resourcehelpers;

import android.content.Context;

import java.io.InputStream;

/**
 * Created by Ilja on 22/01/2015.
 */
public class CsvWordProvider implements IWordProvider {
    public static final String separator = ";";

    /**
     * Loads all the words in the specified CSV file into memory to construct this CsvWordProvider.
     * @param context The context used to retrieve the resource
     * @param resource The resource id of the csv file
     */
    public CsvWordProvider(Context context, int resource) {
        InputStream is = context.getResources().openRawResource(resource);


    }

    @Override
    public String getWord(Difficulty difficulty) {
        return null;
    }
}
