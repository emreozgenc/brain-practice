package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
import com.emreozgenc.brainpractice.entities.Board;
import com.emreozgenc.brainpractice.managers.Assets;

public class PlayScreen implements Screen {

    private BrainPractice game;
    private Board board;
    private Skin skin;
    public Stage stage;
    public Label timeLabel;

    public PlayScreen(BrainPractice game, int height, int width) {
        stage = new Stage(new ScreenViewport());
        stage.getViewport().apply();
        skin = new Skin();
        initUI();
        this.game = game;
        board = new Board(this, height, width);

        InputMultiplexer multiplexer = new InputMultiplexer(board.stage, stage);
        Gdx.input.setInputProcessor(multiplexer);

    }

    private void initUI() {
        final float scale = Gdx.graphics.getWidth() / 1080f;
        final float width = Gdx.graphics.getWidth();
        final float height = Gdx.graphics.getHeight();
        final TextureAtlas atlas = Assets.manager.get(Assets.uiskinAtlas);

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
        table.align(Align.top | Align.center);

        timeLabel = new Label("", skin, "title-border");
        timeLabel.setAlignment(Align.center);
        final Label timeLabelInfo = new Label("Time Passed", skin, "title-orange-border");
        timeLabelInfo.setAlignment(Align.center);
        final TextButton returnButton = new TextButton("X", skin, "btn-red-sm");
        returnButton.setSize(100f * scale, 100f * scale);
        returnButton.setPosition(Gdx.graphics.getWidth() - 5f * scale, Gdx.graphics.getHeight() - 5f * scale, (Align.top | Align.right));

        table.add(timeLabelInfo).expandX().fillX().colspan(2);
        table.row();
        table.add(timeLabel).expandX().fillX().colspan(2);

        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
            }
        });


        stage.addActor(table);
        stage.addActor(returnButton);
    }

    @Override
    public void show() {

    }

    public void showResult(float time) {
        final String timeStr = String.format("%.1f seconds", time);

        final Table table = new Table();
        table.defaults().pad(Constants.TABLE_DEFAULT_PAD);

        final Label title = new Label("Practice Completed", skin, "title-orange-border");
        final Label timeLabel = new Label(timeStr, skin, "title-border");
        final TextButton button = new TextButton("RETURN", skin, "btn-red-rg");

        table.add(title);
        table.row();
        table.add(timeLabel);
        table.row();
        table.add(button).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        table.pack();

        table.setColor(1, 1, 1, 0);
        table.setPosition(Constants.WIDTH/2f, Constants.HEIGHT/2f, Align.center);

        TextureAtlas atlas = Assets.manager.get(Assets.uiskinAtlas);

        table.setBackground(new NinePatchDrawable(new NinePatch(
                atlas.findRegion("window"), 44, 44, 44, 44
        )));

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        table.addAction(Actions.fadeIn(.5f));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    public void showRecordResult(float time) {
        final String timeStr = String.format("%.1f seconds", time);

        final Table table = new Table();
        table.defaults().pad(Constants.TABLE_DEFAULT_PAD);

        final Label title = new Label("Practice Completed", skin, "title-orange-border");
        final Label timeLabel = new Label(timeStr, skin, "title-border");
        final Label recordLabel = new Label("TIME RECORD !", skin, "title-green-border");
        final TextButton button = new TextButton("Return", skin, "btn-red-rg");

        table.add(title);
        table.row();
        table.add(timeLabel);
        table.row();
        table.add(recordLabel);
        table.row();
        table.add(button).width(Constants.BTN_DEFAULT_WIDTH).height(Constants.BTN_DEFAULT_HEIGHT);
        table.pack();

        table.setColor(1, 1, 1, 0);
        table.setPosition(Constants.WIDTH/2f, Constants.HEIGHT/2f, Align.center);

        TextureAtlas atlas = Assets.manager.get(Assets.uiskinAtlas);

        table.setBackground(new NinePatchDrawable(new NinePatch(
                atlas.findRegion("window"), 44, 44, 44, 44
        )));

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        table.addAction(Actions.fadeIn(.5f));

        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(.95f, .95f, .95f, 1);

        board.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        board.stage.getViewport().update(width, height);
        board.stage.getViewport().apply();
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
        board.dispose();
    }
}
