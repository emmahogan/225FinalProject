package racing;

import java.awt.Point;
import java.awt.Rectangle;

import gameutils.GameObject;

public class Wall extends GameObject {
    public static final int SIZE = 50;

    public Wall(int x, int y) {
        x = (x * SIZE);
        y = SpeedRacers.FRAME_HEIGHT - (y * SIZE) - SIZE;
        position = new Point(x, y);
        setBounds(new Rectangle(position.x, position.y, 50, 50));
    }

    @Override
    public void update() {

    }
}

