package net.zzorn.minild37.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.annotations.Mapper;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ClickListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import net.zzorn.minild37.components.HudPart;
import net.zzorn.minild37.utils.Log;

/**
 * Handles UIs and HUD elements
 */
public class UiSystem extends EntitySystem implements ResizeListener, Disposable {

    @Mapper
    private ComponentMapper<HudPart> hudMapper;

    private BitmapFont font;
    private Stage stage;
    private Skin skin;

    private InputMultiplexer inputMultiplexer = new InputMultiplexer();
    private final String skinJsonPath;
    private final String skinImagePath;

    private final Log log = new Log(UiSystem.class);

    public UiSystem(String skinJsonPath, String skinImagePath) {
        //noinspection unchecked
        super(Aspect.getAspectForAll(HudPart.class));

        this.skinJsonPath = skinJsonPath;
        this.skinImagePath = skinImagePath;
    }

    public void resize(int width, int height) {
        // Resize the stage
        stage.setViewport(
                (int) (width),
                (int) (height),
                true);
    }

    @Override
    protected void initialize() {
        font = new BitmapFont();
        stage = new Stage(0, 0, true);
        skin = new Skin( Gdx.files.internal(skinJsonPath), Gdx.files.internal(skinImagePath) );
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    protected void inserted(Entity e) {
        final HudPart hudPart = hudMapper.get(e);
        stage.addActor(hudPart.getActor());
    }

    @Override
    protected void removed(Entity e) {
        final HudPart hudPart = hudMapper.get(e);
        stage.removeActor(hudPart.getActor());
    }

    @Override
    protected void processEntities(ImmutableBag<Entity> entities) {
        // Update the actors
        stage.act(world.getDelta());

        // Draw the stage actors
        stage.draw();
    }


    @Override
    protected boolean checkProcessing() {
        return true;
    }

    public final void dispose() {
        font.dispose();
        stage.dispose();
        if (skin != null) {
            skin.dispose();
        }
    }


    public TextButton createButton(String text, ClickListener listener) {
        TextButton button = new TextButton(skin);
        button.setText(text);
        button.setClickListener(listener);
        return button;
    }

    public ImageButton createImageButton(String icon,ClickListener listener){
        final RenderSystem renderSystem = world.getSystem(RenderSystem.class);
        final TextureAtlas atlas = renderSystem.getAtlas();

        ImageButton imageButton = new ImageButton(
                atlas.findRegion(icon + "NotPressScaled"),
                atlas.findRegion(icon+"PressScaled"),
                atlas.findRegion(icon + "PressScaled"));
        imageButton.setClickListener(listener);
        return imageButton;
    }


}
