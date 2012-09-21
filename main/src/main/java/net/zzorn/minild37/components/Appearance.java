package net.zzorn.minild37.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 *
 */
public interface Appearance {

    /**
     * Render the appearance
     *
     * @param screenX Screen pos to render to.
     * @param screenY Screen pos to render to.
     * @param scaleWorldToScreen scale to render at.
     * @param batch sprite batch to use.
     * @param atlas image atlas to use to get the image.
     */
    public void render(float screenX, float screenY, float scaleWorldToScreen, SpriteBatch batch, TextureAtlas atlas);

    /**
     * @return width of the appearance, in world units.
     */
    public float getWorldWidth();

    /**
     * @return height of the appearance, in world units.
     */
    public float getWorldHeight();
}
