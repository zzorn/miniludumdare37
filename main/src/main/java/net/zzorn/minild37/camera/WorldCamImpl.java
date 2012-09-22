package net.zzorn.minild37.camera;

import net.zzorn.minild37.components.Pos;

import static net.zzorn.minild37.utils.ParameterChecker.checkPositiveNonZeroNormalNumber;

/**
 * Isometric world camera
 */
public class WorldCamImpl implements WorldCam {

    private int screenW = 100;
    private int screenH = 100;

    private Pos worldFocusPos = new Pos(0,0);
    private float worldToScreenScaleFactor = 1;

    private float tileImageWidth = 128;
    private float tileImageHeight = 64;

    public void update(float deltaSeconds) {
        // TODO: Implement inertia and such to camera movements
    }

    public float transformWorldToScreen(Pos worldPos, Pos screenPos) {
        float screenCenterX = screenW * 0.5f;
        float screenCenterY = screenH * 0.5f;

        screenPos.x = screenCenterX +
                worldToScreenScaleFactor * 0.5f * tileImageWidth * (worldPos.x - worldFocusPos.x) -
                worldToScreenScaleFactor * 0.5f * tileImageWidth * (worldPos.y - worldFocusPos.y);
        screenPos.y = screenCenterY +
                worldToScreenScaleFactor * 0.5f * tileImageHeight * (worldPos.x - worldFocusPos.x) +
                worldToScreenScaleFactor * 0.5f * tileImageHeight * (worldPos.y - worldFocusPos.y);

        screenPos.z = screenPos.y;

        return worldToScreenScaleFactor;
    }

    public float transformScreenToWorld(Pos screenPos, Pos worldPos) {
        // TODO: Implement

        throw new UnsupportedOperationException("Not yet implemented");

        //worldPos.z = 0;
        //return 1.0f / worldToScreenScaleFactor;
    }

    public int getScreenWidth() {
        return screenW;
    }

    public int getScreenHeight() {
        return screenH;
    }

    public Pos getFocusPoint() {
        return worldFocusPos;
    }

    public void setFocusPoint(Pos focusPoint) {
        worldFocusPos = focusPoint;
    }

    public void onResize(int width, int height) {
        screenW = width;
        screenH = height;
    }

    public void setZoom(float worldToScreenScaleFactor) {
        checkPositiveNonZeroNormalNumber(worldToScreenScaleFactor, "worldToScreenScaleFactor");

        this.worldToScreenScaleFactor = worldToScreenScaleFactor;
    }
}
