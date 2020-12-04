package com.emreozgenc.brainpractice;

import com.badlogic.gdx.Game;
import com.emreozgenc.brainpractice.entities.SoundManager;
import com.emreozgenc.brainpractice.screens.SplashScreen;

public class BrainPractice extends Game {

    public static final int WIDTH = 450;
    public static final int HEIGHT = 800;

    public static final int V_WIDTH = 9;
    public static final int V_HEIGHT = 16;

    public SoundManager soundManager;

    @Override
    public void create() {
        soundManager = new SoundManager();
        setScreen(new SplashScreen(this));
    }
}
