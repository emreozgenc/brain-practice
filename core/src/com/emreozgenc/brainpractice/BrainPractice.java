package com.emreozgenc.brainpractice;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Timer;
import com.emreozgenc.brainpractice.managers.Preference;
import com.emreozgenc.brainpractice.managers.Sounds;
import com.emreozgenc.brainpractice.managers.Assets;
import com.emreozgenc.brainpractice.screens.SplashScreen;

public class BrainPractice extends Game {
    
    public static final int WIDTH = 450;
    public static final int HEIGHT = 800;

    public static final int V_WIDTH = 9;
    public static final int V_HEIGHT = 16;

    public Sounds sounds;
    public Preference preference;
    public AdController adController = null;

    public BrainPractice() {}
    public BrainPractice(AdController adController) {
        this.adController = adController;
    }

    @Override
    public void create() {
        Assets.load();
        Assets.manager.finishLoading();
        preference = new Preference();
        sounds = new Sounds();
        showSplashScreen();
        showAd();
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
    }

    private void showAd() {
        if(adController != null)
            Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                adController.showAd();
            }
        },5f);
    }

    private void showSplashScreen() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                setScreen(new SplashScreen(BrainPractice.this));
            }
        }, .5f);
    }
}
