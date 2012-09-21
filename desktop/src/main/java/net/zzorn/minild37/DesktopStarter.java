package net.zzorn.minild37;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 *
 */
public class DesktopStarter {

    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "MiniLD37";
        cfg.useGL20 = true;
        cfg.width = 800;
        cfg.height = 480;
        new LwjglApplication(new MiniLD37(), cfg);
    }
}
