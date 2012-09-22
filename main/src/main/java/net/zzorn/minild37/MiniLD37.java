package net.zzorn.minild37;

import com.artemis.EntitySystem;
import com.artemis.World;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.utils.Array;
import net.zzorn.minild37.generation.WorldGenerator;
import net.zzorn.minild37.generation.WorldGeneratorImpl;
import net.zzorn.minild37.systems.*;
import net.zzorn.minild37.utils.Log;

/**
 *
 */
public class MiniLD37 implements ApplicationListener {

    private World world = new World();

    private boolean paused = false;

    private final Log log = new Log(MiniLD37.class);
    private final WorldGenerator worldGenerator = new WorldGeneratorImpl();

    public void create() {
        log.info("Starting game");

        world.setSystem(new RenderSystem("images/images.pack"));
        world.setSystem(new SoundSystem());
        world.setSystem(new UiSystem("uiskin.json", "uiskin.png"));

        world.initialize();

        worldGenerator.generateWorld(world);
    }

    public void render() {
        // TODO: More complicated logic updates could be done here.
        // TODO: See http://gafferongames.com/game-physics/fix-your-timestep/
        update(world.getDelta());
    }


    private void update(float delta) {
        world.setDelta(delta);
        world.process();
    }


    public void resize(int width, int height) {
        log.info("Resized to " + width + ", " + height);



        for (ResizeListener resizeListener : getSystemsImplementing(ResizeListener.class)) {
            resizeListener.resize(width, height);
        }
    }

    public void pause() {
        log.info("Paused");

        // TODO: Save state
        paused = true;
    }

    public void resume() {
        log.info("Resumed");

        paused = false;

    }

    public void dispose() {
        log.info("Stopping game");

        for (Disposable disposableSystem : getSystemsImplementing(Disposable.class)) {
            disposableSystem.dispose();
        }
    }


    public <T> Array<T> getSystemsImplementing(Class<T> c) {
        final Array<T> result = new Array<T>();

        final ImmutableBag<EntitySystem> systems = world.getSystems();
        for (int i = 0; i < systems.size(); i++) {
            final EntitySystem system = systems.get(i);
            if (c.isInstance(system)) result.add((T) system);
        }

        return result;
    }
}

