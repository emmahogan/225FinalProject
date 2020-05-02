package gameutils;

import java.awt.Point;

public class FloatPoint {
    public float x;
    public float y;

    public FloatPoint() {
        this.x = 0;
        this.y = 0;
    }

    public FloatPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Point toPoint() {
        return new Point((int) x, (int) y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
