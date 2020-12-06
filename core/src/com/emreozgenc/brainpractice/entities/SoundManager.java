package com.emreozgenc.brainpractice.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.emreozgenc.brainpractice.managers.Assets;

public class SoundManager {

    public static SoundManager soundManager;
    private Sound selectSound;
    private Sound successSound;

    public SoundManager() {
        soundManager = this;
        selectSound = Assets.manager.get(Assets.selectSound);
        successSound = Assets.manager.get(Assets.successSound);
    }

    public void playSelectSound() {
        selectSound.play();
    }

    public void playSuccessSound() {
        successSound.play();
    }
}
