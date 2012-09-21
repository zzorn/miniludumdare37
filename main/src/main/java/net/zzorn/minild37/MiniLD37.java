package net.zzorn.minild37;

import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 *
 */
public class MiniLD37 extends Game {

    private World world = new World();

    @Override
    public void create() {

        // world.setSystem();

        world.initialize();
    }

    @Override
    public void render() {


        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }


    private void update(float delta) {
        world.setDelta(delta);
        world.process();
    }

}

