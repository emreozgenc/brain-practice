package com.emreozgenc.brainpractice;


import com.badlogic.gdx.Gdx;

public class Constants {
    // UI Constants
    public static final float WIDTH = Gdx.graphics.getWidth();
    public static final float HEIGHT = Gdx.graphics.getHeight();
    public static final float DEFAULT_WIDTH = 1080f;
    public static final float SCALE = WIDTH / DEFAULT_WIDTH;
    public static final float BTN_SCREEN_RATIO = .8f;
    public static final float BTN_WIDTH_HEIGHT_RATIO = .2f;
    public static final float BTN_DEFAULT_WIDTH = WIDTH * BTN_SCREEN_RATIO;
    public static final float BTN_DEFAULT_HEIGHT = BTN_DEFAULT_WIDTH * BTN_WIDTH_HEIGHT_RATIO;
    public static final float TABLE_DEFAULT_PAD = 30f * SCALE;
    public static final float GAME_LOGO_WIDTH = WIDTH * .8f;
    public static final float GAME_LOGO_HEIGHT = GAME_LOGO_WIDTH * .5f;
}
