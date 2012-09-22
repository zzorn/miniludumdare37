package net.zzorn.minild37.generation;

import com.artemis.Entity;
import com.artemis.World;
import net.zzorn.minild37.components.Pic;
import net.zzorn.minild37.components.Pos;

import java.util.Random;

import static net.zzorn.minild37.generation.Terrains.*;

/**
 *
 */
public class WorldGeneratorImpl implements WorldGenerator {

    int worldSizeX = 20;
    int worldSizeY = 20;


    public void generateWorld(World world) {

        Random random = new Random(42);

        for (int y = 0; y < worldSizeY; y++) {
            for (int x = 0; x < worldSizeX; x++) {
                createTile(world, random, x, y);
            }
        }

    }

    private void createTile(World world, Random random, int x, int y) {
        final Entity entity = world.createEntity();
        entity.addComponent(new Pos(x, y));
        entity.addComponent(new Pic(GROUND.getRandomImageName(random), 0.5f, 0.75f));
        entity.addToWorld();
    }
}
