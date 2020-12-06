package com.emreozgenc.brainpractice.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
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
    public static final String selectSound = "sounds/select.ogg";
    public static final String successSound = "sounds/success.ogg";

    private static final float scale = Gdx.graphics.getWidth() / 1080f;


    public static void load() {

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        FreetypeFontLoader.FreeTypeFontLoaderParameter fontBig = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontBig.fontFileName = "Chewy-Regular.ttf";
        fontBig.fontParameters.size = Math.round(scale * 96);
        manager.load("fontBig.ttf", BitmapFont.class, fontBig);

        FreetypeFontLoader.FreeTypeFontLoaderParameter fontSmall = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontSmall.fontFileName = "Chewy-Regular.ttf";
        fontSmall.fontParameters.size = Math.round(scale * 48);
        manager.load("fontSmall.ttf", BitmapFont.class, fontSmall);


        manager.load(cardsAtlas, TextureAtlas.class);
        manager.load(uiskinAtlas, TextureAtlas.class);
        manager.load(logo, Texture.class);
        manager.load(selectSound, Sound.class);
        manager.load(successSound, Sound.class);
    }

    public static void dispose() {
        manager.dispose();
    }
}
