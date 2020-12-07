package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
import com.emreozgenc.brainpractice.managers.Assets;

public class PlayScreen implements Screen {

    private BrainPractice game;
    private Board board;
    public static Stage stage;
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
        final float width = Gdx.graphics.getWidth();
        final float height = Gdx.graphics.getHeight();
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
        table.align(Align.top | Align.center);

        timeLabel = new Label("Time : 0s", skin, "title-border");
        final Label timeLabelInfo = new Label("Time", skin, "title-orange-border");
        final TextButton returnButton = new TextButton("RETURN MENU", skin, "btn-red-sm");

        table.add(timeLabel).padTop(50f * scale);
        table.row();
        table.add(timeLabelInfo);
        table.row();
        table.add(returnButton).expandX().width(width*.8f).height(width*.08f);


        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));
                dispose();
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
        board.dispose();
    }
}
