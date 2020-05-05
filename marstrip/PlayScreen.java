package marstrip;

import java.awt.Graphics;
import java.util.ArrayList;

import gameutils.Screen;
import gameutils.Sound;
import gameutils.SoundManager;

/**
 * Justin added this game Mars Trip he had created previously for fun as an experimental addition, playing around
 * with the game engine we made from scratch. Since it was just for fun I am not adding javadoc comments.
 *
 * @author Justin Marotta
 * @version Spring 2020
 */
public class PlayScreen extends Screen {
    ArrayList<Background> bgImgs;
    Ship ship;

    public PlayScreen() {
        initBackground();
        MarsTrip.soundManager.play("assets/marstrip/music.wav");
        this.ship = new Ship(100, 100);
        this.controller = new ShipController(ship);
    }

    @Override
    public void render(Graphics g) {
        for (Background bg : bgImgs) {
            g.drawImage(bg.getTexture(), bg.getPosition().x, bg.getPosition().y, null);
        }
        for (Bullet bullet : ship.bullets) {
            g.drawImage(bullet.getTexture(), bullet.position.x, bullet.position.y, null);
        }
        g.drawImage(ship.getTexture(), ship.getPosition().x, ship.getPosition().y, null);
    }

    @Override
    public void update() {
        for (Background bg : bgImgs) {
            bg.update();
        }
        ship.update();
        for (Bullet bullet : ship.bullets) {
            bullet.update();
        }
    }

    @Override
    public void dispose() {

    }

    public void initBackground() {
        bgImgs = new ArrayList<Background>();
        bgImgs.add(new Background("assets/marstrip/stars.png", 1, 0));
        bgImgs.add(new Background("assets/marstrip/stars.png", 1, 800));
        bgImgs.add(new Background("assets/marstrip/hills.png", 6, 0));
        bgImgs.add(new Background("assets/marstrip/hills.png", 6, 800));
        bgImgs.add(new Background("assets/marstrip/ground.png", 8, 0));
        bgImgs.add(new Background("assets/marstrip/ground.png", 8, 800));
    }
}
