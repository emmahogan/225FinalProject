package marstrip;

import java.awt.Graphics;
import java.util.ArrayList;

import gameutils.Screen;
import gameutils.Sound;
import gameutils.SoundManager;

public class PlayScreen extends Screen {
    ArrayList<Background> bgImgs;
    Ship ship;

    public PlayScreen(SoundManager soundManager) {
        initBackground();
        this.soundManager = soundManager;
        this.ship = new Ship(100, 100);
        this.controller = new ShipController(ship);
        soundManager.play("assets/marstrip/music.wav");
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
