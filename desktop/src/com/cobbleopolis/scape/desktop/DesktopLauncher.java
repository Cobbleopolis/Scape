package com.cobbleopolis.scape.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cobbleopolis.scape.Scape;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Scape";
//		config.useGL30 = true;
		config.height = 720;
		config.width = 1280;
//		config.fullscreen = true;
		new LwjglApplication(new Scape(), config);
	}
}
