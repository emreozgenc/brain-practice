package com.emreozgenc.brainpractice.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.emreozgenc.brainpractice.BrainPractice;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BrainPractice.WIDTH;
		config.height = BrainPractice.HEIGHT;
		new LwjglApplication(new BrainPractice(), config);
	}
}
