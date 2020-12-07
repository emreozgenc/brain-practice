package com.emreozgenc.brainpractice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import com.emreozgenc.brainpractice.entities.SoundManager;
import com.emreozgenc.brainpractice.managers.Assets;
import com.emreozgenc.brainpractice.screens.SplashScreen;

public class BrainPractice extends Game {

    public static final int WIDTH = 450;
    public static final int HEIGHT = 800;

    public static final int V_WIDTH = 9;
    public static final int V_HEIGHT = 16;

    public SoundManager soundManager;

    @Override
    public void create() {
        Assets.load();
        Assets.manager.finishLoading();
        soundManager = new SoundManager();

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                setScreen(new SplashScreen(BrainPractice.this));
            }
        }, .5f);
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }
}
