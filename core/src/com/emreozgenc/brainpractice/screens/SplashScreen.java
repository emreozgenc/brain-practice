package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.emreozgenc.brainpractice.BrainPractice;
import com.emreozgenc.brainpractice.managers.Assets;

import java.rmi.activation.ActivationID;

public class SplashScreen implements Screen {

    private static final float DURATION = 1.5f;
    private static final float WIDTH = 8f;
    private static final float HEIGHT = 8f;

    private BrainPractice game;
    private Stage stage;
    private Image logo;
    private Texture logoTexture;

    public SplashScreen(BrainPractice game) {
        this.game = game;
        stage = new Stage(new FitViewport(BrainPractice.V_WIDTH, BrainPractice.V_HEIGHT));
        logoTexture = Assets.manager.get(Assets.logo, Texture.class);
        logoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        logo = new Image(logoTexture);
        logo.setColor(1, 1, 1, 0);
        logo.setSize(WIDTH, HEIGHT);
        logo.setPosition(BrainPractice.V_WIDTH / 2f, BrainPractice.V_HEIGHT / 2f + 1, Align.center);
        stage.addActor(logo);

    }

    @Override
    public void show() {
        logo.addAction(new SequenceAction(
                Actions.fadeIn(DURATION),
                Actions.fadeOut(DURATION),
                new Action() {
                    @Override
                    public boolean act(float delta) {
                        game.setScreen(new MenuScreen(game));
                        return true;
                    }
                }
        ));
    }
    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(1, 1, 1, 1);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
        stage.getViewport().apply();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
