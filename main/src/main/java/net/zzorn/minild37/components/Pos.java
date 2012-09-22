package net.zzorn.minild37.components;

import com.artemis.Component;

/**
 * A world position
 */
public class Pos extends Component {

    public float x = 0;
    public float y = 0;
    public float z = 0;
    public float layer = 0;

    public Pos() {
    }

    public Pos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Pos(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Pos(float x, float y, float z, float layer) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.layer = layer;
    }

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", layer=" + layer +
                '}';
    }


    public float getDrawOrder() {
        return x + y + z - layer * 1000;
    }
}
