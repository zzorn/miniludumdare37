package net.zzorn.minild37.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Picture appearance
 */
public class Pic extends Component implements Appearance {

    private String name;
    private float picOriginX = 0.5f;
    private float picOriginY = 0f;
    private TextureAtlas.AtlasRegion atlasRegion = null;



    public Pic(String name) {
        this(name, 0.5f, 0f);
    }

    public Pic(String name, float picOriginX, float picOriginY) {
        this.name = name;
        this.picOriginX = picOriginX;
        this.picOriginY = picOriginY;
    }

    public void render(float screenX, float screenY, float scaleWorldToScreen, SpriteBatch batch, TextureAtlas atlas) {
        if (atlasRegion == null) atlasRegion = atlas.findRegion(name);

        float screenW = atlasRegion.getRegionWidth()  * scaleWorldToScreen;
        float screenH = atlasRegion.getRegionHeight() * scaleWorldToScreen;

        float screenBotLeftX = screenX - picOriginX * screenW;
        float screenBotLeftY = screenY - picOriginY * screenH;

        batch.draw(atlasRegion, screenBotLeftX, screenBotLeftY, screenW, screenH);
    }

    public float getWidth() {
        if (atlasRegion != null) return atlasRegion.getRegionWidth();
        else return 128;
    }

    public float getHeight() {
        if (atlasRegion != null) return atlasRegion.getRegionHeight();
        else return 128;
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


    @Override
    public String toString() {
        return "Pic{" +
                "name='" + name + '\'' +
                ", picOriginX=" + picOriginX +
                ", picOriginY=" + picOriginY +
                '}';
    }
}
