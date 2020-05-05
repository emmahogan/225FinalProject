package marstrip;

import gameutils.GameObject;
import gameutils.Texture;

/**
 * Justin added this game Mars Trip he had created previously for fun as an experimental addition, playing around
 * with the game engine we made from scratch. Since it was just for fun I am not adding javadoc comments.
 *
 * @author Justin Marotta
 * @version Spring 2020
 */
public class Background extends GameObject {
    int speed;

    public Background(String filePath, int speed, int x) {
        texture = new Texture(filePath);
        texture.scale(2f, 2f);
        this.speed = speed;
        setPosition(x, MarsTrip.FRAME_HEIGHT - texture.getHeight());
    }

    @Override
    public void update() {
        position.x -= speed;
        if (position.x + texture.getWidth() <= 0) {
            position.x = texture.getWidth() - speed;
        }
    }
}
