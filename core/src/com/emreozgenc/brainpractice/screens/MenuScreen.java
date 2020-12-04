package com.emreozgenc.brainpractice.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.emreozgenc.brainpractice.BrainPractice;

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

        final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas"));
        final Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"), atlas);
        skin.getFont("fontBig").getData().setScale(scale);
        skin.getFont("fontSmall").getData().setScale(scale);

        final Table table = new Table();
        table.setFillParent(true);
        table.defaults().pad(20f);

        final TextButton startButton = new TextButton("Start Game", skin);
        final TextButton exitButton = new TextButton("Exit", skin);
        final Label titleLabel = new Label("BRAIN PRACTICE v0.0.1", skin, "big");
        final Label scoreLabel = new Label("Time Record : " + 10, skin, "big");

        table.add(titleLabel).padBottom(height*.1f);
        table.row();
        table.add(startButton).width(width*.8f);
        table.row();
        table.add(exitButton).width(width*.8f);
        table.row();
        table.add(scoreLabel).padTop(height*.1f);




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


        stage.addActor(table);

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
