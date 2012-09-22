package net.zzorn.minild37.services.options;

import com.badlogic.gdx.utils.ObjectMap;
import net.zzorn.minild37.utils.Log;

/**
 * Simple implementation of OptionsService that just stores things in memory.
 */
public class InMemoryOptionsService implements OptionsService {

    private final ObjectMap<String, Object> properties = new ObjectMap<String, Object>();

    private final Log log = new Log(getClass());

    public void set(String key, Object value) {
        properties.put(key, value);
    }

    public <T> T get(String key, T defaultValue) {
        if (!properties.containsKey(key)) return defaultValue;
        else return (T) properties.get(key);
    }

    public boolean has(String key) {
        return properties.containsKey(key);
    }
}
