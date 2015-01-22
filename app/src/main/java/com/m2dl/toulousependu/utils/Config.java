package com.m2dl.toulousependu.utils;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Ilja on 22/01/2015.
 */
public class Config {
    private final String PREFS_NAME = "ToulousePenduConfig";

    private final String SILENT_MODE_SETTING = "silentMode";
    private final String EASY_HIGH_SCORE_SETTING = "easyHighScoreSetting";
    private final String MEDIUM_HIGH_SCORE_SETTING = "mediumHighScoreSetting";
    private final String HARD_HIGH_SCORE_SETTING = "hardHighScoreSetting";
    private final String EXTREME_HIGH_SCORE_SETTING = "extremeHighScoreSetting";

    private SharedPreferences settings;

    public Config(Activity activity) {
        settings = activity.getSharedPreferences(PREFS_NAME, 0);
    }

    public boolean setSilent(boolean silent) {
        settings.edit().putBoolean(SILENT_MODE_SETTING, silent).commit();
        return getSoundSetting();
    }

    public boolean getSoundSetting() {
        return settings.getBoolean(SILENT_MODE_SETTING, false);
    }

    public void setEasyHighScore(int score) {
        settings.edit().putInt(EASY_HIGH_SCORE_SETTING, score).commit();
    }

    public void setMediumHighScore(int score) {
        settings.edit().putInt(MEDIUM_HIGH_SCORE_SETTING, score).commit();
    }

    public void setHardHighScore(int score) {
        settings.edit().putInt(HARD_HIGH_SCORE_SETTING, score).commit();
    }

    public void setExtremeHighScore(int score) {
        settings.edit().putInt(EXTREME_HIGH_SCORE_SETTING, score).commit();
    }

    public int getEasyHighScore() {
        return settings.getInt(EASY_HIGH_SCORE_SETTING, -1);
    }

    public int getMediumHighScore() {
        return settings.getInt(MEDIUM_HIGH_SCORE_SETTING, -1);
    }

    public int getHardHighScore() {
        return settings.getInt(HARD_HIGH_SCORE_SETTING, -1);
    }

    public int getExtremeHighScore() {
        return settings.getInt(EXTREME_HIGH_SCORE_SETTING, -1);
    }
}
