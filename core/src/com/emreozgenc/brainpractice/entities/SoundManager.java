package com.emreozgenc.brainpractice.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.emreozgenc.brainpractice.managers.Assets;

public class SoundManager {

    public static SoundManager soundManager;
    public boolean isThemeOpen = false;
    public boolean isSFXOpen = true;
    public Sound selectSound;
    public Sound successSound;
    public Music themeMusic;

    public SoundManager() {
        soundManager = this;
        selectSound = Assets.manager.get(Assets.selectSound);
        successSound = Assets.manager.get(Assets.successSound);
        themeMusic = Assets.manager.get(Assets.themeMusic);
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
    }
}
