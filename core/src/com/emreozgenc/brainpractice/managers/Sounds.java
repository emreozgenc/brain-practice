package com.emreozgenc.brainpractice.managers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class Sounds {

    public static Sounds manager;
    public boolean isThemeOpen;
    public boolean isSFXOpen;
    public Sound selectSound;
    public Sound successSound;
    public Music themeMusic;

    public Sounds() {
        manager = this;
        selectSound = Assets.manager.get(Assets.selectSound);
        successSound = Assets.manager.get(Assets.successSound);
        themeMusic = Assets.manager.get(Assets.themeMusic);

        isThemeOpen = Preference.manager.getThemeSetting();
        isSFXOpen = Preference.manager.getSoundSetting();
    }


    public void playSelectSound() {
        if(isSFXOpen)
            selectSound.play();
    }

    public void playSuccessSound() {
        if(isSFXOpen)
            successSound.play();
    }

    public void startThemeMusic() {
        if (isThemeOpen) {
            themeMusic.setLooping(true);
            themeMusic.play();
        }
    }

    public void switchThemeMusicPlay() {
        if (isThemeOpen) {
            themeMusic.stop();
            isThemeOpen = false;
        } else {
            themeMusic.setLooping(true);
            themeMusic.play();
            isThemeOpen = true;
        }

        Preference.manager.setThemeSetting(isThemeOpen);
    }

    public void switchSFXPlay() {
        if(isSFXOpen)
            isSFXOpen = false;
        else
            isSFXOpen = true;

        Preference.manager.setSoundSetting(isSFXOpen);
    }
}
