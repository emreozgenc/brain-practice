package com.emreozgenc.brainpractice.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

    public static SoundManager soundManager;
    private Sound selectSound;
    private Sound successSound;

    public SoundManager() {
        soundManager = this;
        selectSound = Gdx.audio.newSound(Gdx.files.internal("sounds/select.ogg"));
        successSound = Gdx.audio.newSound(Gdx.files.internal("sounds/success.ogg"));

    }

    public void playSelectSound() {
        selectSound.play();
    }

    public void playSuccessSound() {
        successSound.play();
    }
}
