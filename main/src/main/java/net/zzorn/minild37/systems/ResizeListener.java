package net.zzorn.minild37.systems;

/**
 * Should be notified when the screen is first constructed, and when it is resized after that.
 */
// TODO: Call systems that implement this
public interface ResizeListener {

    public void resize(int width, int height);

}
