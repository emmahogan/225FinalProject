package marstrip;

import gameutils.GameObject;
import gameutils.Texture;

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
