package com.emreozgenc.brainpractice.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Preference {
    private static final String EASY_RECORD = "4x3-Time";
    private static final String MEDIUM_RECORD = "4x4-Time";
    private static final String HARD_RECORD = "4x5-Time";
    private static final String RECORD_NAME = "TimeRecord";

    private static final String SOUND_NAME = "Sounds";
    private static final String THEME = "Theme";
    private static final String SOUND = "Sound";

    public static Preference manager;

    public Preference() {
        manager = this;
    }

    public boolean getThemeSetting() {
        Preferences preferences = Gdx.app.getPreferences(SOUND_NAME);
        return preferences.getBoolean(THEME);
    }

    public void setThemeSetting(boolean val) {
        Preferences preferences = Gdx.app.getPreferences(SOUND_NAME);
        preferences.putBoolean(THEME, val);
        preferences.flush();
    }

    public void setSoundSetting(boolean val) {
        Preferences preferences = Gdx.app.getPreferences(SOUND_NAME);
        preferences.putBoolean(SOUND, val);
        preferences.flush();
    }

    public boolean getSoundSetting() {
        Preferences preferences = Gdx.app.getPreferences(SOUND_NAME);
        return preferences.getBoolean(SOUND);
    }

    public float getEasyRecord() {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        return preferences.getFloat(EASY_RECORD);
    }

    public float getMediumRecord() {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        return preferences.getFloat(MEDIUM_RECORD);
    }

    public float getHardRecord() {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        return preferences.getFloat(HARD_RECORD);
    }

    public void setRecord(String key, float val) {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        preferences.putFloat(key, val);
        preferences.flush();
    }

    public float getRecord(String key) {
        Preferences preferences = Gdx.app.getPreferences(RECORD_NAME);
        return preferences.getFloat(key);
    }
}
