package com.emreozgenc.brainpractice.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.emreozgenc.brainpractice.managers.Assets;

public class SoundManager {

    public static SoundManager soundManager;
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
        selectSound.play();
    }

    public void playSuccessSound() {
        successSound.play();
    }

    public void startThemeMusic() {
        themeMusic.setLooping(true);
        themeMusic.play();
    }

    public void switchThemeMusicPlay() {
        if(themeMusic.isPlaying()) {
            themeMusic.stop();
        }
        else {
            themeMusic.setLooping(true);
            themeMusic.play();
        }
    }
}
