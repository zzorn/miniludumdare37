package net.zzorn.minild37.components;

import com.artemis.Component;

/**
 * A world position
 */
public class Pos extends Component {

    public float x = 0;
    public float y = 0;
    public float z = 0;

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

    @Override
    public String toString() {
        return "Pos{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
