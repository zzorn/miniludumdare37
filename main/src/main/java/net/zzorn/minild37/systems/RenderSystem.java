package net.zzorn.minild37.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import net.zzorn.minild37.camera.WorldCam;
import net.zzorn.minild37.camera.WorldCamImpl;
import net.zzorn.minild37.components.Pic;
import net.zzorn.minild37.components.Pos;
import net.zzorn.minild37.utils.Log;

import java.util.Comparator;

/**
 * Renders things.
 */
public class RenderSystem extends EntitySystem implements ResizeListener, Disposable {
    @Mapper ComponentMapper<Pos> posMapper;
    @Mapper ComponentMapper<Pic> picMapper;

    private WorldCam worldCam = new WorldCamImpl();
    private Pos tempPos = new Pos();

    private SpriteBatch batch;
    private TextureAtlas atlas;
    private final String imagePackPath;

    private final Array<Entity> sortedEntities = new Array<Entity>();

    private final Comparator<Entity> entityDrawOrderComparator = new Comparator<Entity>() {
        public int compare(Entity o1, Entity o2) {
            Pos pos1 = posMapper.get(o1);
            Pos pos2 = posMapper.get(o2);

            if (pos1.z < pos2.z) return -1;
            else if (pos1.z > pos2.z) return 1;
            else return 0;
        }
    };

    private final Log log = new Log(RenderSystem.class);

    public RenderSystem(final String imagePackPath) {
        //noinspection unchecked
        super(Aspect.getAspectForAll(Pos.class, Pic.class));

        this.imagePackPath = imagePackPath;
    }

    @Override
    protected void initialize() {
        batch = new SpriteBatch();
        atlas = new TextureAtlas(Gdx.files.internal(imagePackPath));
    }

    public void resize(int width, int height) {
        worldCam.onResize(width, height);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    /**
     * @return the camera used to project pictures from world to screen coordinates.
     */
    public WorldCam getWorldCam() {
        return worldCam;
    }

    @Override
    protected void inserted(Entity e) {
        sortedEntities.add(e);
    }

    @Override
    protected void removed(Entity e) {
        sortedEntities.removeValue(e, true);
    }

    @Override
    protected void begin() {
        // Update camera
        worldCam.update(world.getDelta());
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        // Sort the entities by depth
        sortedEntities.sort(entityDrawOrderComparator);

        // Clear the screen
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Start batch
        batch.begin();

        // Render entities
        for (Entity sortedEntity : sortedEntities) {
            process(sortedEntity);
        }

        // Do the render
        batch.end();
    }

    private void process(Entity e) {
        Pos pos = posMapper.get(e);
        Pic pic = picMapper.get(e);

        // Project
        final float worldToScreenScale = worldCam.transformWorldToScreen(pos, tempPos);

        // Render
        pic.render(tempPos.x, tempPos.y, worldToScreenScale, batch, atlas);
    }

    @Override
    protected boolean checkProcessing() {
        return true;
    }

    public final void dispose() {
        batch.dispose();
        atlas.dispose();
    }


    public TextureAtlas getAtlas() {
        return atlas;
    }
}
