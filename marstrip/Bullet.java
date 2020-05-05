package marstrip;

import java.awt.Point;

import gameutils.GameObject;
import gameutils.Texture;

/**
 * Justin added this game Mars Trip he had created previously for fun as an experimental addition, playing around
 * with the game engine we made from scratch. Since it was just for fun I am not adding javadoc comments.
 *
 * @author Justin Marotta
 * @version Spring 2020
 */
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
