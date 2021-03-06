package com.emreozgenc.brainpractice.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.emreozgenc.brainpractice.BrainPractice;
import com.emreozgenc.brainpractice.managers.Assets;
import com.emreozgenc.brainpractice.managers.Preference;
import com.emreozgenc.brainpractice.managers.Sounds;
import com.emreozgenc.brainpractice.screens.PlayScreen;

import java.util.Random;

public class Board {

    private static final float MARGIN_BOT = 2f;
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
    private PlayScreen playScreen;

    public static Array<Card> selectedCards;

    public Board(PlayScreen playScreen, int height, int width) {
        this.width = width;
        this.height = height;
        this.playScreen = playScreen;
        this.timeLabel = playScreen.timeLabel;
        time = 0f;
        isFinished = false;
        stage = new Stage(new ExtendViewport(BrainPractice.V_WIDTH, BrainPractice.V_HEIGHT));
        stage.getViewport().apply();
        cards = new Card[height][width];
        cardSize = (BrainPractice.V_WIDTH - 2 * MARGIN_LEFT) / height;
        selectedCards = new Array<>();
        count = width * height;
        atlas = Assets.manager.get(Assets.cardsAtlas);

        initCards();
    }

    public void render(float delta) {
        time += delta;
        stage.act(delta);
        stage.draw();

        if (!isFinished) {
            String str = String.format("%.1f", time);
            timeLabel.setText(str + " seconds");
        }
    }

    public void controlCards() {
        Card first = selectedCards.get(0);
        Card second = selectedCards.get(1);

        if (first.value == second.value) {
            first.isSolved = true;
            second.isSolved = true;
            selectedCards.clear();
            Sounds.manager.playSelectSound();
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
        setRecord();
        isFinished = true;
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Sounds.manager.playSuccessSound();
            }
        }, DELAY_SOUND);
    }

    private void setRecord() {
        float currentTime = time;

        String key = height + "x" + width + "-Time";
        float oldTime = Preference.manager.getRecord(key);
        System.out.println(oldTime);

        if(oldTime == 0f) {
            Preference.manager.setRecord(key, currentTime);
            playScreen.showRecordResult(currentTime);
            return;
        }

        if (currentTime < oldTime) {
            Preference.manager.setRecord(key, currentTime);
            playScreen.showRecordResult(currentTime);
            return;
        }

        playScreen.showResult(currentTime);
    }

    private void initCards() {

        int val = 0;

        for (int i = 0; i < height; i++) {

            if (i == height/2)
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
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cards[j][i].setPosition(
                        MARGIN_LEFT + j * cardSize,
                        MARGIN_BOT + i * cardSize
                );
                stage.addActor(cards[j][i]);
            }
        }
    }

    public void dispose() {
        stage.dispose();
    }
}
