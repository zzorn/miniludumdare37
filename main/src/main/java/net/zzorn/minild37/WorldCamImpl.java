package net.zzorn.minild37;

import net.zzorn.minild37.components.Pos;

import static net.zzorn.minild37.utils.ParameterChecker.checkPositiveNonZeroNormalNumber;

/**
 *
 */
public class WorldCamImpl implements WorldCam {

    private int screenW;
    private int screenH;

    private Pos worldFocusPos;
    private float worldToScreenScaleFactor;

    @Override
    public void update(float deltaSeconds) {
        // TODO: Implement inertia and such to camera movements
    }

    @Override
    public float transformWorldToScreen(Pos worldPos, Pos screenPos) {
        // TODO: Implement

        screenPos.z = worldPos.y;

        return worldToScreenScaleFactor;
    }

    @Override
    public float transformScreenToWorld(Pos screenPos, Pos worldPos) {
        // TODO: Implement

        worldPos.z = 0;

        return 1.0f / worldToScreenScaleFactor;
    }

    @Override
    public int getScreenWidth() {
        return screenW;
    }

    @Override
    public int getScreenHeight() {
        return screenH;
    }

    @Override
    public Pos getFocusPoint() {
        return worldFocusPos;
    }

    @Override
    public void setFocusPoint(Pos focusPoint) {
        worldFocusPos = focusPoint;
    }

    @Override
    public void onResize(int width, int height) {
        screenW = width;
        screenH = height;
    }

    @Override
    public void setZoom(float worldToScreenScaleFactor) {
        checkPositiveNonZeroNormalNumber(worldToScreenScaleFactor, "worldToScreenScaleFactor");

        this.worldToScreenScaleFactor = worldToScreenScaleFactor;
    }
}
