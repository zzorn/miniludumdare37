package net.zzorn.minild37.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import net.zzorn.minild37.components.Audible;
import net.zzorn.minild37.utils.LRUCache;
import net.zzorn.minild37.utils.Log;

/**
 *
 */
public class SoundSystem extends EntitySystem implements Disposable{
    @Mapper
    private ComponentMapper<Audible> audibleMapper;

    private static final int MAX_SOUNDS = 30;

    /**
     * The volume to be set on the sound.
     */
    private float volume = 1f;

    /**
     * Whether the sound is enabled.
     */
    private boolean enabled = true;

    /**
     * The sound cache.
     */
    private final LRUCache<String, Sound> soundCache = new LRUCache<String, Sound>(MAX_SOUNDS);

    private final Log log = new Log(SoundSystem.class);

    public SoundSystem() {
        super(Aspect.getAspectForAll(Audible.class));
    }

    @Override
    protected void initialize() {
        soundCache.setEntryRemovedListener(new LRUCache.CacheEntryRemovedListener<String, Sound>() {
            public void notifyEntryRemoved(String key, Sound value) {
                log.debug("Disposing sound: " + key);
                value.dispose();
            }
        });

        // TODO: Preload sounds
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        // TODO: Implement
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }


    /**
     * Plays the specified sound.
     */
    public void play(SoundResourceHandle sound) {
        play(sound.getFileName());
    }

    /**
     * Plays the sound in the specified file.
     */
    public void play(String soundFileName)
    {
        // Check if the sound is enabled
        if( ! enabled ) return;

        // Try and get the sound from the cache
        Sound soundToPlay = soundCache.get(soundFileName);
        if (soundToPlay == null) {
            FileHandle soundFile = Gdx.files.internal(soundFileName);
            soundToPlay = Gdx.audio.newSound( soundFile );
            soundCache.add(soundFileName, soundToPlay);
        }

        // Play the sound
        log.debug("Playing sound: " + soundFileName);
        soundToPlay.play( volume );
    }

    /**
     * Sets the sound volume which must be inside the range [0,1].
     */
    public void setVolume(float volume) {
        log.debug("Adjusting sound volume to: " + volume);

        // Check and set the new volume
        if( volume < 0 || volume > 1f ) {
            throw new IllegalArgumentException( "The volume must be inside the range: [0,1]" );
        }
        this.volume = volume;
    }

    /**
     * Enables or disabled the sound.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void dispose() {
        for( Sound sound : soundCache.retrieveAll() ) {
            sound.stop();
            sound.dispose();
        }
    }

}
