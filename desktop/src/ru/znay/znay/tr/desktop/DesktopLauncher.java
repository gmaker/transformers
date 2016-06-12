package ru.znay.znay.tr.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.znay.znay.tr.TransformersGame;

public class DesktopLauncher {
    public static final int SCALE = 2;

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = TransformersGame.WIDTH * SCALE;
        config.height = TransformersGame.HEIGHT * SCALE;
        new LwjglApplication(new TransformersGame(), config);
    }
}
