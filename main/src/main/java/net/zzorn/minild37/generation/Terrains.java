package net.zzorn.minild37.generation;

import java.util.Random;

/**
 *
 */
public enum Terrains {

    GROUND("ground", 5),

    ;

    private final String baseName;
    private final int tileCount;

    private Terrains(String baseName, int tileCount) {
        this.baseName = baseName;
        this.tileCount = tileCount;
    }

    public int getTileCount() {
        return tileCount;
    }

    public String getBaseName() {
        return baseName;
    }

    public String getRandomImageName(Random random) {
        return baseName + (random.nextInt(tileCount) + 1);
    }
}
