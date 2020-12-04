package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.emreozgenc.brainpractice.BrainPractice;
import com.emreozgenc.brainpractice.entities.Board;

public class PlayScreen implements Screen {

    private BrainPractice game;
    private Board board;
    private Stage stage;
    public Label timeLabel;

    public PlayScreen(BrainPractice game) {
        stage = new Stage(new ScreenViewport());
        stage.getViewport().apply();
        initUI();
        this.game = game;
        board = new Board(timeLabel, 4, 4);

        InputMultiplexer multiplexer = new InputMultiplexer(board.stage, stage);
        Gdx.input.setInputProcessor(multiplexer);

    }

    private void initUI() {
        final float scale = Gdx.graphics.getWidth() / 1080f;
        final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
        final Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), atlas);
        skin.getFont("fontBig").getData().setScale(scale);
        skin.getFont("fontSmall").getData().setScale(scale);

        final Table table = new Table();
        table.setFillParent(true);
        table.align(Align.top | Align.center);

        timeLabel = new Label("Time : 0s", skin, "big");
        final TextButton returnButton = new TextButton("Return Menu", skin);

        table.add(timeLabel).padTop(50f);
        table.row();
        table.add(returnButton);


        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
            }
        });


        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl20.glClearColor(.95f, .95f, .95f, 1);


        stage.act(delta);
        stage.draw();
        board.render(delta);
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
    }
}
