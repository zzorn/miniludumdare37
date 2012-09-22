package net.zzorn.minild37.generation;

import com.artemis.World;

/**
 *
 */
public interface WorldGenerator {

    void init(World world);

    void generateWorld();

}
