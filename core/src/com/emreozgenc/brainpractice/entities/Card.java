package com.emreozgenc.brainpractice.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.AddAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Card extends Image {
    public int value;
    public boolean isSolved;
    private boolean isOpen;
    private Drawable frontFace;
    private Drawable backFace;
    private Board board;

    public Card(TextureRegion frontFace, TextureRegion backFace, int value, Board board) {
        this.frontFace = new TextureRegionDrawable(frontFace);
        this.backFace = new TextureRegionDrawable(backFace);
        this.value = value;
        this.board = board;

        isSolved = false;
        isOpen = false;

        setDrawable(this.backFace);

        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                select();
            }
        });

    }

    private void select() {
        if (!isSolved && Board.selectedCards.size < 2 && !isOpen) {
            Board.selectedCards.add(this);
            isOpen = true;
            addAction(new SequenceAction(
                    Actions.fadeOut(.25f),
                    new Action() {
                        @Override
                        public boolean act(float delta) {
                            setDrawable(frontFace);
                            return true;
                        }
                    },
                    Actions.fadeIn(.25f)
            ));

            if (Board.selectedCards.size == 2)
                board.controlCards();
        }
    }

    public void reverse() {
        if (!isSolved && isOpen) {
            isOpen = false;
            addAction(new SequenceAction(
                    Actions.fadeOut(.25f),
                    new Action() {
                        @Override
                        public boolean act(float delta) {
                            setDrawable(backFace);
                            return true;
                        }
                    },
                    Actions.fadeIn(.25f)
            ));
        }
    }
}
