package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.emreozgenc.brainpractice.BrainPractice;
import com.emreozgenc.brainpractice.entities.SoundManager;
import com.emreozgenc.brainpractice.managers.Assets;

public class MenuScreen implements Screen {

    private BrainPractice game;
    private Stage stage;

    private static final float BTN_WIDTH_SCALE = .8f;
    private static final float BTN_HEIGHT_SCALE = .2f;
    private static final float DEFAULT_SCREEN_WIDTH = 1080f;
    private static final float DEFAULT_TABLE_PAD = 20f;

    public MenuScreen(BrainPractice game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        initUI();
    }

    private void initUI() {
        final float scale = Gdx.graphics.getWidth() / DEFAULT_SCREEN_WIDTH;
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
        table.defaults().pad(DEFAULT_TABLE_PAD * scale);

        final TextButton startButton = new TextButton("START GAME", skin, "btn-red-rg");
        final TextButton scoreButton = new TextButton("TIME RECORDS", skin, "btn-green-rg");
        final TextButton musicButton = new TextButton("UNMUTE MUSIC", skin, "btn-purple-rg");
        final TextButton soundButton = new TextButton("UNMUTE SOUND", skin, "btn-orange-rg");

        final float mainBtnWidth = width * BTN_WIDTH_SCALE;
        final float mainBtnHeight = mainBtnWidth * BTN_HEIGHT_SCALE;

        table.add(startButton).width(mainBtnWidth).height(mainBtnHeight);
        table.row();
        table.add(scoreButton).width(mainBtnWidth).height(mainBtnHeight);
        table.row();
        table.add(musicButton).width(mainBtnWidth).height(mainBtnHeight);
        table.row();
        table.add(soundButton).width(mainBtnWidth).height(mainBtnHeight);

        final TextButton dif_4_3 = new TextButton("4x3 - EASY", skin, "btn-green-rg");
        final TextButton dif_4_4 = new TextButton("4x4 - MEDIUM", skin, "btn-orange-rg");
        final TextButton dif_4_5 = new TextButton("4x5 - HARD", skin, "btn-red-rg");
        final TextButton difClose = new TextButton("CLOSE", skin, "btn-purple-rg");

        final Window difWindow = new Window("", skin);
        difWindow.setSize(width * .9f, height * .6f);
        difWindow.setMovable(false);
        difWindow.setPosition(width / 2f, height / 2f, Align.center);
        difWindow.defaults().pad(20f * scale);
        difWindow.setColor(1, 1, 1, 0);
        difWindow.setVisible(false);

        difWindow.add(dif_4_3).expandX().fillX().height(width * .16f);
        difWindow.row();
        difWindow.add(dif_4_4).expandX().fillX().height(width * .16f);
        difWindow.row();
        difWindow.add(dif_4_5).expandX().fillX().height(width * .16f);
        difWindow.row();
        difWindow.add(difClose).expandX().fillX().height(width * .16f);


        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difWindow.setVisible(true);
                difWindow.addAction(Actions.fadeIn(.5f));
            }
        });

        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundManager.soundManager.switchThemeMusicPlay();

                if (SoundManager.soundManager.isThemeOpen)
                    musicButton.setText("MUTE MUSIC");
                else
                    musicButton.setText("UNMUTE MUSIC");
            }
        });

        difClose.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                difWindow.addAction(new SequenceAction(
                        Actions.fadeOut(.5f),
                        new Action() {
                            @Override
                            public boolean act(float delta) {
                                difWindow.setVisible(false);
                                return true;
                            }
                        }
                ));
            }
        });

        dif_4_3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game, 4, 3));
            }
        });

        dif_4_4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game, 4, 4));
            }
        });

        dif_4_5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game, 4, 5));
            }
        });


        stage.addActor(table);
        stage.addActor(difWindow);

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
