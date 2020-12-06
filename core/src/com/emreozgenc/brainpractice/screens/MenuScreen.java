package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.emreozgenc.brainpractice.BrainPractice;
import com.emreozgenc.brainpractice.managers.Assets;

public class MenuScreen implements Screen {

    private BrainPractice game;
    private Stage stage;


    public MenuScreen(BrainPractice game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        initUI();
    }

    private void initUI() {
        final float scale = Gdx.graphics.getWidth() / 1080f;
        final int width = Gdx.graphics.getWidth();
        final int height = Gdx.graphics.getHeight();

        final TextureAtlas atlas = Assets.manager.get(Assets.uiskinAtlas);
        final Skin skin = new Skin();

        BitmapFont fontBig = Assets.manager.get("fontBig.ttf");
        BitmapFont fontSmall = Assets.manager.get("fontSmall.ttf");

        skin.add("fontBig", fontBig, BitmapFont.class);
        skin.add("fontSmall", fontSmall, BitmapFont.class);
        skin.addRegions(atlas);
        skin.load(Gdx.files.internal("ui/uiskin.json"));

        final Table table = new Table();
        table.setFillParent(true);
        table.defaults().pad(20f);

        final TextButton startButton = new TextButton("START THE GAME", skin);
        final TextButton scoreButton = new TextButton("TIME RECORDS", skin);
        final TextButton exitButton = new TextButton("CLOSE THE GAME", skin);
        final Label titleLabel = new Label("BRAIN PRACTICE v0.0.1", skin, "big");
        final Label scoreLabel = new Label("", skin, "big");

        Preferences preferences = Gdx.app.getPreferences("TimeRecord");

        float timeRecord = preferences.getFloat("4x4-Time");
        String str = String.format("Best Time : %.1f seconds", timeRecord);
        scoreLabel.setText(str);

        table.add(titleLabel).padBottom(height*.1f);
        table.row();
        table.add(startButton).width(width*.8f).height(width*.16f);
        table.row();
        table.add(scoreButton).width(width*.8f).height(width*.16f);
        table.row();
        table.add(exitButton).width(width*.8f).height(width*.16f);
        table.row();
        table.add(scoreLabel).padTop(height*.1f);

        final Window window = new Window("", skin);
        window.setWidth(width*.9f);
        window.setHeight(width*.9f);
        window.setMovable(false);
        window.setPosition(width/2f, height/2f, Align.center);
        window.setLayoutEnabled(false);
        window.setColor(1, 1, 1, 0);
        window.setVisible(false);

        final TextButton closeWindow = new TextButton("CLOSE WINDOW", skin, "inWindow");
        closeWindow.setSize(window.getWidth()*.9f, window.getWidth()*.16f);
        closeWindow.setPosition(window.getWidth()/2f, 20f * scale, Align.bottom);

        window.add(closeWindow);




        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });

        scoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.setVisible(true);
                window.addAction(Actions.fadeIn(.5f));
            }
        });

        closeWindow.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                window.addAction(new SequenceAction(
                        Actions.fadeOut(.5f),
                        new Action() {
                            @Override
                            public boolean act(float delta) {
                                window.setVisible(false);
                                return true;
                            }
                        }
                ));
            }
        });



        stage.addActor(table);
        stage.addActor(window);

    }

    @Override
    public void show() {

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
