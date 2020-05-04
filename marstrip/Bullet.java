package marstrip;

import java.awt.Point;

import gameutils.GameObject;
import gameutils.Texture;

public class Bullet extends GameObject {
    private static final int SPEED = 15;

    public Bullet(Ship ship) {
        texture = new Texture("assets/marstrip/bullet.png");
        texture.scale(2, 2);
        Point newPos = ship.position.toPoint();
        setPosition(newPos.x + ship.getTexture().getWidth(null),
                newPos.y + (ship.getTexture().getHeight(null) / 2) - (texture.getHeight() / 2));
    }

    @Override
    public void update() {
        position.x += SPEED;
    }
}
