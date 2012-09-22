package net.zzorn.minild37.camera;

import net.zzorn.minild37.components.Pos;

/**
 * Projects positions from the world to the screen and the other way.
 */
public interface WorldCam {

    /**
     * Sets the screen pos object to the screen position corresponding to the specified world pos.
     * @return the scaling factor from world to screen coordinates.
     */
    float transformWorldToScreen(Pos worldPos, Pos screenPos);

    /**
     * Sets the world pos object to the world position corresponding to the specified screen pos.
     * @return the scaling factor from screen to world coordinates.
     */
    float transformScreenToWorld(Pos screenPos, Pos worldPos);

    /**
     * @return width of screen in pixels.
     */
    int getScreenWidth();

    /**
     * @return height of screen in pixels.
     */
    int getScreenHeight();

    /**
     * @return the world position that is in the middle of the screen.
     */
    Pos getFocusPoint();

    /**
     * @param focusPoint the world position that should be in the middle of the screen.
     *                   The camera will track the specified focus pos object.
     */
    void setFocusPoint(Pos focusPoint);

    /**
     * Update camera position.
     */
    void update(float deltaSeconds);

    /**
     * Should be called to tell the initial screen size, and whenever the screen is resized.
     */
    void onResize(int width, int height);

    /**
     * Sets the zoom.  1 = 1 unit in the world = 1 pixel on screen, 100 = 1 unit in world, 100 pixels on screen.
     */
    void setZoom(float worldToScreenScaleFactor);

}
