package net.zzorn.minild37.generation;

import com.artemis.Entity;
import com.artemis.World;
import net.zzorn.minild37.components.Harvestable;
import net.zzorn.minild37.components.Hosted;
import net.zzorn.minild37.components.Pos;
import net.zzorn.minild37.utils.PerlinNoise;

import java.util.Random;

import static net.zzorn.minild37.generation.Images.GROUND;
import static net.zzorn.minild37.generation.Images.PINE;
import static net.zzorn.minild37.utils.MathTools.clampToZeroToOne;

/**
 *
 */
public class WorldGeneratorImpl implements WorldGenerator {

    private static final int MAX_TREES_ON_FOREST_TILE = 5;
    int worldSizeX = 20;
    int worldSizeY = 20;


    private World world;
    private final Random random = new Random();

    public WorldGeneratorImpl() {
    }

    public void init(World world) {
        this.world = world;
    }

    public void generateWorld() {
        random.setSeed(42);

        for (int y = 0; y < worldSizeY; y++) {
            for (int x = 0; x < worldSizeX; x++) {
                createTile(x, y);
            }
        }

    }

    private void createTile(int x, int y) {
        final Entity entity = world.createEntity();
        entity.addComponent(new Pos(x, y, 0, -1));
        entity.addComponent(GROUND.getRandomPic(random));
        entity.addToWorld();

        // Add some trees
        final float density = (float) (PerlinNoise.octaveNoise(0.4 * x, 0.4 * y, 2, 41) * 3);

        createForestCover(entity, x, y, density);

    }

    private void createForestCover(Entity hostTile, float x, float y, float density) {
        final int treeCount = (int) (clampToZeroToOne(density) * MAX_TREES_ON_FOREST_TILE);
        for (int i = 0; i < treeCount; i++) {
            float tx = random.nextFloat() + x;
            float ty = random.nextFloat() + y;
            createTree(hostTile, tx, ty);
        }
    }


    private Entity createTree(Entity hostTile, float x, float y) {
        final Entity entity = world.createEntity();
        entity.addComponent(new Pos(x, y));
        entity.addComponent(PINE.getRandomPic(random));
        entity.addComponent(new Harvestable());
        entity.addComponent(new Hosted(hostTile));
        entity.addToWorld();
        return entity;
    }
}
