package net.zzorn.minild37.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Picture appearance
 */
public class Pic extends Component implements Appearance {

    private String name;
    private float worldWidth = 1;
    private float worldHeight = 1;
    private float picOriginX = 0.5f;
    private float picOriginY = 0f;
    private TextureAtlas.AtlasRegion atlasRegion = null;

    public Pic(String name) {
        this(name, 1, 1);
    }

    public Pic(String name, float worldWidth, float worldHeight) {
        this(name, worldWidth, worldHeight, 0.5f, 0f);
    }

    public Pic(String name, float worldWidth, float worldHeight, float picOriginX, float picOriginY) {
        this.name = name;
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.picOriginX = picOriginX;
        this.picOriginY = picOriginY;
    }

    public void render(float screenX, float screenY, float scaleWorldToScreen, SpriteBatch batch, TextureAtlas atlas) {
        if (atlasRegion == null) atlasRegion = atlas.findRegion(name);

        float screenW = worldWidth * scaleWorldToScreen;
        float screenH = worldHeight * scaleWorldToScreen;

        float screenBotLeftX = screenX - picOriginX * screenW;
        float screenBotLeftY = screenY - picOriginY * screenH;

        batch.draw(atlasRegion, screenBotLeftX, screenBotLeftY, screenW, screenH);
    }

    public float getWorldWidth() {
        return worldWidth;
    }

    public float getWorldHeight() {
        return worldHeight;
    }

    public void setWorldWidth(float worldWidth) {
        this.worldWidth = worldWidth;
    }

    public void setWorldHeight(float worldHeight) {
        this.worldHeight = worldHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        atlasRegion = null;
    }

    public float getPicOriginX() {
        return picOriginX;
    }

    /**
     * @param picOriginX which point on the picture should be the anchor, 0 = left edge, 1 = right edge.
     */
    public void setPicOriginX(float picOriginX) {
        this.picOriginX = picOriginX;
    }

    public float getPicOriginY() {
        return picOriginY;
    }

    /**
     * @param picOriginY which point on the picture should be the anchor, 0 = bottom edge, 1 = top edge.
     */
    public void setPicOriginY(float picOriginY) {
        this.picOriginY = picOriginY;
    }
}
