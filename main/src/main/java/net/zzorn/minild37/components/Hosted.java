package net.zzorn.minild37.components;

import com.artemis.Component;
import com.artemis.Entity;

/**
 * Something hosted on a tile.
 */
public class Hosted extends Component {

    Entity tileEntity = null;

    public Hosted() {
    }

    public Hosted(Entity tileEntity) {
        this.tileEntity = tileEntity;
    }

    public Entity getTileEntity() {
        return tileEntity;
    }

    public void setTileEntity(Entity tileEntity) {
        this.tileEntity = tileEntity;
    }
}
