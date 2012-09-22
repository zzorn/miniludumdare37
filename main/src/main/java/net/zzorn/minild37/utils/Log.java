package net.zzorn.minild37.utils;

import com.badlogic.gdx.Gdx;

/**
 * Simple logging utility.
 */
public class Log {

    private final String systemName;

    public Log() {
        this("Log");
    }

    public Log(Class<?> c) {
        this(c.getSimpleName());
    }

    public Log(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemName() {
        return systemName;
    }

    public final void debug(String message) {
        Gdx.app.debug(getSystemName(), message);
    }
    public final void debug(String message, Throwable exception) {
        Gdx.app.debug(getSystemName(), message, exception);
    }
    public final void info(String message) {
        Gdx.app.log(getSystemName(), message);
    }
    public final void info(String message, Exception exception) {
        Gdx.app.log(getSystemName(), message, exception);
    }
    public final void error(String message) {
        Gdx.app.error(getSystemName(), message);
    }
    public final void error(String message, Throwable exception) {
        Gdx.app.error(getSystemName(), message, exception);
    }

}
