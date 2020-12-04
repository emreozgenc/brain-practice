package com.emreozgenc.brainpractice.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.emreozgenc.brainpractice.BrainPractice;
import com.emreozgenc.brainpractice.screens.MenuScreen;
import com.emreozgenc.brainpractice.screens.PlayScreen;

import java.util.Random;

public class Board {

    private static final float MARGIN_BOT = 1f;
    private static final float MARGIN_LEFT = .5f;
    private static final float DELAY = 1.5f;
    private static final float DELAY_SOUND = .5f;


    public Stage stage;
    private TextureAtlas atlas;
    private Card[][] cards;
    private int count, width, height;
    private float cardSize;
    private Label timeLabel;
    private float time;
    private boolean isFinished;

    public static Array<Card> selectedCards;

    public Board(Label timeLabel, int width, int height) {
        this.width = width;
        this.height = height;
        this.timeLabel = timeLabel;
        time = 0f;
        isFinished = false;
        stage = new Stage(new ExtendViewport(BrainPractice.V_WIDTH, BrainPractice.V_HEIGHT));
        stage.getViewport().apply();
        cards = new Card[height][width];
        cardSize = (BrainPractice.V_WIDTH - 2 * MARGIN_LEFT) / width;
        selectedCards = new Array<>();
        count = width * height;
        atlas = new TextureAtlas(Gdx.files.internal("cards.atlas"));

        initCards();
    }

    public void render(float delta) {
        time += delta;
        stage.act(delta);
        stage.draw();

        if (!isFinished) {
            String str = String.format("Time : %.1f" + "s", time);
            timeLabel.setText(str);
        }
    }

    public void controlCards() {
        Card first = selectedCards.get(0);
        Card second = selectedCards.get(1);

        if (first.value == second.value) {
            first.isSolved = true;
            second.isSolved = true;
            selectedCards.clear();
            SoundManager.soundManager.playSelectSound();
            controlFinish();
        } else {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    for (Card card : selectedCards) {
                        card.reverse();
                    }
                    selectedCards.clear();
                }
            }, DELAY);
        }
    }

    private void controlFinish() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!cards[i][j].isSolved)
                    return;
            }
        }
        final BrainPractice game = (BrainPractice) Gdx.app.getApplicationListener();

        isFinished = true;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                SoundManager.soundManager.playSuccessSound();
            }
        }, DELAY_SOUND);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new MenuScreen(game));
            }
        }, DELAY);
    }

    private void initCards() {

        int val = 0;

        for (int i = 0; i < height; i++) {

            if (val >= count / 2)
                val = 0;

            for (int j = 0; j < width; j++) {
                TextureRegion frontFace = atlas.findRegion("card" + val);
                TextureRegion backFace = atlas.findRegion("cardbg");
                Card card = new Card(frontFace, backFace, val, this);
                card.setSize(cardSize, cardSize);
                cards[i][j] = card;
                val++;
            }
        }

        mixCards();
    }

    private void mixCards() {
        Random random = new Random();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int h = random.nextInt(height);
                int w = random.nextInt(width);

                Card temp = cards[i][j];
                cards[i][j] = cards[h][w];
                cards[h][w] = temp;
            }
        }

        posCards();
    }

    private void posCards() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cards[i][j].setPosition(
                        MARGIN_LEFT + j * cardSize,
                        MARGIN_BOT + i * cardSize
                );
                stage.addActor(cards[i][j]);
            }
        }
    }
}