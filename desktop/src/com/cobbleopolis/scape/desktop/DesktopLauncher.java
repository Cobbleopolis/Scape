package com.cobbleopolis.scape.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cobbleopolis.scape.Scape;

public class DesktopLauncher {

	public static void main (String[] arg) {
//		arg[0] = "--fullscreen";
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Scape";
//		config.useGL30 = true;
		config.height = 720;
		config.width = 1280;
		for(int i = 0; i < arg.length; i++){
			if(arg[i] == "--fullscreen"){
				config.height = 1920;
				config.width = 1080;
				config.fullscreen = true;
			} else {
				config.height = 720;
				config.width = 1280;
			}
		}

//		config.fullscreen = true;
		new LwjglApplication(new Scape(), config);
	}
}
