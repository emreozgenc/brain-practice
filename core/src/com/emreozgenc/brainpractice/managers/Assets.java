package com.emreozgenc.brainpractice.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {
    public static final AssetManager manager = new AssetManager();
    public static final String cardsAtlas = "cards.atlas";
    public static final String uiskinAtlas = "ui/uiskin.atlas";
    public static final String logo = "logo.png";
    public static final String gameLogo = "gamelogo.png";
    public static final String selectSound = "sounds/select.ogg";
    public static final String successSound = "sounds/success.ogg";
    public static final String themeMusic = "sounds/music.ogg";

    private static final float scale = Gdx.graphics.getWidth() / 1080f;


    public static void load() {

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter chewy_96_border = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        chewy_96_border.fontFileName = "fonts/chewy.ttf";
        chewy_96_border.fontParameters.borderWidth = 4f * scale;
        chewy_96_border.fontParameters.borderColor = Color.BLACK;
        chewy_96_border.fontParameters.size = Math.round(scale * 96);
        manager.load("chewy-96-border.ttf", BitmapFont.class, chewy_96_border);

        FreetypeFontLoader.FreeTypeFontLoaderParameter chewy_64_border = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        chewy_64_border.fontFileName = "fonts/chewy.ttf";
        chewy_64_border.fontParameters.size = Math.round(scale * 64);
        chewy_64_border.fontParameters.borderWidth = 3.5f * scale;
        chewy_64_border.fontParameters.borderColor = Color.BLACK;
        manager.load("chewy-64-border.ttf", BitmapFont.class, chewy_64_border);

        FreetypeFontLoader.FreeTypeFontLoaderParameter chewy_48_border = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        chewy_48_border.fontFileName = "fonts/chewy.ttf";
        chewy_48_border.fontParameters.size = Math.round(scale * 48);
        chewy_48_border.fontParameters.borderWidth = 3f * scale;
        chewy_48_border.fontParameters.borderColor = Color.BLACK;
        manager.load("chewy-48-border.ttf", BitmapFont.class, chewy_48_border);


        manager.load(cardsAtlas, TextureAtlas.class);
        manager.load(uiskinAtlas, TextureAtlas.class);
        manager.load(logo, Texture.class);
        manager.load(gameLogo, Texture.class);
        manager.load(selectSound, Sound.class);
        manager.load(successSound, Sound.class);
        manager.load(themeMusic, Music.class);
    }

    public static void dispose() {
        manager.dispose();
    }
}
