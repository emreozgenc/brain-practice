package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.emreozgenc.brainpractice.BrainPractice;
import com.emreozgenc.brainpractice.Constants;
import com.emreozgenc.brainpractice.managers.Preference;
import com.emreozgenc.brainpractice.managers.Sounds;
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
        final TextureAtlas atlas = Assets.manager.get(Assets.uiskinAtlas);
        final Skin skin = new Skin();

        final Texture gameLogoTexture = Assets.manager.get(Assets.gameLogo);
        gameLogoTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        final Image gameLogo = new Image(gameLogoTexture);
        gameLogo.setAlign(Align.top | Align.center);

        BitmapFont chewy_96_border = Assets.manager.get("chewy-96-border.ttf");
        BitmapFont chewy_64_border = Assets.manager.get("chewy-64-border.ttf");
        BitmapFont chewy_48_border = Assets.manager.get("chewy-48-border.ttf");

        skin.add("chewy-96-border", chewy_96_border, BitmapFont.class);
        skin.add("chewy-64-border", chewy_64_border, BitmapFont.class);
        skin.add("chewy-48-border", chewy_48_border, BitmapFont.class);
        skin.addRegions(atlas);
        skin.load(Gdx.files.internal("ui/uiskin.json"));

        // MAIN TABLE

        final Table table = new Table();
        table.setFillParent(true);
        table.defaults().pad(Constants.TABLE_DEFAULT_PAD);

        final TextButton startButton = new TextButton("START GAME", skin, "btn-red-rg");
        final TextButton scoreButton = new TextButton("TIME RECORDS", skin, "btn-green-rg");
        final TextButton musicButton = new TextButton("MUSIC ON/OFF", skin, "btn-purple-rg");
        final TextButton soundButton = new TextButton("SOUND ON/OFF", skin, "btn-orange-rg");


        table.add(gameLogo).width(Constants.GAME_LOGO_WIDTH).height(Constants.GAME_LOGO_HEIGHT);
        table.row();
        table.add(startButton).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        table.row();
        table.add(scoreButton).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        table.row();
        table.add(musicButton).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        table.row();
        table.add(soundButton).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);

        // LEVEL TABLE

        final Table levelTable = new Table();
        levelTable.defaults().pad(Constants.TABLE_DEFAULT_PAD);

        final TextButton dif_4_3 = new TextButton("4x3 - EASY", skin, "btn-green-rg");
        final TextButton dif_4_4 = new TextButton("4x4 - MEDIUM", skin, "btn-orange-rg");
        final TextButton dif_4_5 = new TextButton("4x5 - HARD", skin, "btn-red-rg");
        final TextButton difClose = new TextButton("CLOSE", skin, "btn-purple-rg");
        final Label difLabel = new Label("SELECT\nDIFFICULTY", skin, "title-border");
        difLabel.setAlignment(Align.center);

        levelTable.add(difLabel);
        levelTable.row();
        levelTable.add(dif_4_3).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        levelTable.row();
        levelTable.add(dif_4_4).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        levelTable.row();
        levelTable.add(dif_4_5).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        levelTable.row();
        levelTable.add(difClose).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        levelTable.pack();

        levelTable.setPosition(Constants.WIDTH / 2f, Constants.HEIGHT / 2f, Align.center);

        levelTable.setBackground(new NinePatchDrawable(new NinePatch(
                atlas.findRegion("window"), 44, 44, 44, 44
        )));

        levelTable.setColor(1, 1, 1, 0);
        levelTable.setVisible(false);

        // SCORE TABLE

        final String record_4x3 = String.format("%.1fs", Preference.manager.getEasyRecord());
        final String record_4x4 = String.format("%.1fs", Preference.manager.getMediumRecord());
        final String record_4x5 = String.format("%.1fs", Preference.manager.getHardRecord());

        final Table scoreTable = new Table();
        scoreTable.defaults().pad(Constants.TABLE_DEFAULT_PAD);

        final Label scoreTitle = new Label("TIME RECORDS", skin, "title-green-border");
        final Label easyRecordTitle = new Label("EASY : ", skin, "title-orange-border");
        final Label easyRecord = new Label(record_4x3, skin, "title-border");
        final Label medRecordTitle = new Label("MEDIUM : ", skin, "title-orange-border");
        final Label medRecord = new Label(record_4x4, skin, "title-border");
        final Label hardRecordTitle = new Label("HARD : ", skin, "title-orange-border");
        final Label hardRecord = new Label(record_4x5, skin, "title-border");
        final TextButton closeRecord = new TextButton("CLOSE", skin, "btn-red-rg");

        scoreTable.add(scoreTitle).colspan(2);
        scoreTable.row();
        scoreTable.add(easyRecordTitle).align(Align.left);
        scoreTable.add(easyRecord);
        scoreTable.row();
        scoreTable.add(medRecordTitle).align(Align.left);
        scoreTable.add(medRecord);
        scoreTable.row();
        scoreTable.add(hardRecordTitle).align(Align.left);
        scoreTable.add(hardRecord);
        scoreTable.row();
        scoreTable.add(closeRecord).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT).colspan(2);

        scoreTable.pack();

        scoreTable.setPosition(Constants.WIDTH/2f, Constants.HEIGHT/2f, Align.center);


        scoreTable.setBackground(new NinePatchDrawable(new NinePatch(
                atlas.findRegion("window"), 44, 44, 44, 44
        )));

        scoreTable.setColor(1, 1, 1, 0);
        scoreTable.setVisible(false);


        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelTable.setVisible(true);
                levelTable.addAction(Actions.fadeIn(.5f));
                table.setTouchable(Touchable.disabled);
            }
        });

        musicButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sounds.manager.switchThemeMusicPlay();
            }
        });

        soundButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Sounds.manager.switchSFXPlay();
            }
        });

        difClose.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                levelTable.addAction(new SequenceAction(
                        Actions.fadeOut(.5f),
                        new Action() {
                            @Override
                            public boolean act(float delta) {
                                levelTable.setVisible(false);
                                table.setTouchable(Touchable.enabled);
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

        closeRecord.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                scoreTable.addAction(new SequenceAction(
                        Actions.fadeOut(.5f),
                        new Action() {
                            @Override
                            public boolean act(float delta) {
                                scoreTable.setVisible(false);
                                table.setTouchable(Touchable.enabled);
                                return true;
                            }
                        }
                ));
            }
        });

        scoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                scoreTable.setVisible(true);
                scoreTable.addAction(Actions.fadeIn(.5f));
                table.setTouchable(Touchable.disabled);
            }
        });


        stage.addActor(table);
        stage.addActor(levelTable);
        stage.addActor(scoreTable);

    }

    @Override
    public void show() {
        Sounds.manager.startThemeMusic();
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
