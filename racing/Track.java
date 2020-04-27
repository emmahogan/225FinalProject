package racing;

import java.awt.Point;
import java.awt.Rectangle;

import gameutils.GameObject;

public class Track extends GameObject {
    public static final int SIZE = 50;

    public Track(int x, int y) {
        position = new Point(x, y);
        setBounds(new Rectangle(position.x, position.y, 50, 50));
    }

    @Override
    public void update() {
        if (bounds.getLocation() != position) {
            bounds.setLocation(position);
        }
    }
}
