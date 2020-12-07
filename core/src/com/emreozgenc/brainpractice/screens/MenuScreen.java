package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.emreozgenc.brainpractice.BrainPractice;
import com.emreozgenc.brainpractice.entities.SoundManager;
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

        BitmapFont chewy_96_border = Assets.manager.get("chewy-96-border.ttf");
        BitmapFont chewy_64_border = Assets.manager.get("chewy-64-border.ttf");
        BitmapFont chewy_48_border = Assets.manager.get("chewy-48-border.ttf");

        skin.add("chewy-96-border", chewy_96_border, BitmapFont.class);
        skin.add("chewy-64-border", chewy_64_border, BitmapFont.class);
        skin.add("chewy-48-border", chewy_48_border, BitmapFont.class);
        skin.addRegions(atlas);
        skin.load(Gdx.files.internal("ui/uiskin.json"));

        final Table table = new Table();
        table.setFillParent(true);
        table.defaults().pad(20f*scale);

        final TextButton startButton = new TextButton("START THE GAME", skin, "btn-red-rg");
        final TextButton scoreButton = new TextButton("TIME RECORDS", skin, "btn-green-rg");
        final TextButton musicButton = new TextButton("UNMUTE MUSIC", skin, "btn-purple-rg");
        final TextButton soundButton = new TextButton("UNMUTE SOUND", skin, "btn-orange-rg");

        table.row();
        table.add(startButton).width(width*.8f).height(width*.16f);
        table.row();
        table.add(scoreButton).width(width*.8f).height(width*.16f);
        table.row();
        table.add(musicButton).width(width*.8f).height(width*.16f);
        table.row();
        table.add(soundButton).width(width*.8f).height(width*.16f);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });

        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.soundManager.switchThemeMusicPlay();

                if(SoundManager.soundManager.isThemeOpen)
                    musicButton.setText("MUTE MUSIC");
                else
                    musicButton.setText("UNMUTE MUSIC");
            }
        });




        stage.addActor(table);

    }

    @Override
    public void show() {
        SoundManager.soundManager.startThemeMusic();
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(.95f, .95f, .95f, 1);

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
