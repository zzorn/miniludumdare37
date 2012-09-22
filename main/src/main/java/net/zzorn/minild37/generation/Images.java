package net.zzorn.minild37.generation;

import net.zzorn.minild37.components.Pic;

import java.util.Random;

/**
 *
 */
public enum Images {

    GROUND("ground", 5, 0.5, 0),
    PINE("pine", 5, 0.5, 0),

    ;

    private final String baseName;
    private final int tileCount;
    private final double originX;
    private final double originY;

    private Images(String baseName, int tileCount, double originX, double originY) {
        this.baseName = baseName;
        this.tileCount = tileCount;
        this.originX = originX;
        this.originY = originY;
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

    public Pic getRandomPic(Random random) {
        return new Pic(getRandomImageName(random), (float) originX, (float) originY);
    }

    public float getOriginX() {
        return (float) originX;
    }

    public float getOriginY() {
        return (float) originY;
    }
}
