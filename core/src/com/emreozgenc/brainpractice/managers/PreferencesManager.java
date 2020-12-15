package com.emreozgenc.brainpractice.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PreferencesManager {
    private static final String EASY_RECORD = "4x3-Time";
    private static final String MEDIUM_RECORD = "4x4-Time";
    private static final String HARD_RECORD = "4x5-Time";
    private static final String RECORD_NAME = "TimeRecord";

    private static final String SOUND_NAME = "Sounds";
    private static final String THEME = "Theme";
    private static final String SOUND = "Sound";

    public static boolean getThemeSetting() {
        Preferences preferences = Gdx.app.getPreferences(SOUND_NAME);
        return preferences.getBoolean(THEME);
    }

    public static void setThemeSetting(boolean val) {
        Preferences preferences = Gdx.app.getPreferences(SOUND_NAME);
        preferences.putBoolean(THEME, val);
        preferences.flush();
    }

    public static void setSoundSetting(boolean val) {
        Preferences preferences = Gdx.app.getPreferences(SOUND_NAME);
        preferences.putBoolean(SOUND, val);
        preferences.flush();
    }

    public static boolean getSoundSetting() {
        Preferences preferences = Gdx.app.getPreferences(SOUND);
        return preferences.getBoolean(SOUND);
    }

    public static float getEasyRecord() {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        return preferences.getFloat(EASY_RECORD);
    }

    public static float getMediumRecord() {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        return preferences.getFloat(MEDIUM_RECORD);
    }

    public static float getHardRecord() {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        return preferences.getFloat(HARD_RECORD);
    }

    public static void setRecord(String key, float val) {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        preferences.putFloat(key, val);
        preferences.flush();
    }

    public static float getRecord(String key) {
        Preferences preferences = Gdx.app.getPreferences(key);
        return preferences.getFloat(key);
    }
}
