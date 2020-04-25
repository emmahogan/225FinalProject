package racing;

import java.awt.Graphics;

import gameutils.Screen;

/**
 * Write a description of class PlayScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayScreen extends Screen {
    private Car p1;
    private Car p2;

    public PlayScreen() {
        super();
        this.p1 = new Car("blue");
        this.p2 = new Car("red");
        this.controller = new SRController(p1, p2);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(p1.getTexture(), p1.getPosition().x, p1.getPosition().y, null);
        g.drawImage(p2.getTexture(), p2.getPosition().x, p2.getPosition().y, null);
    }

    @Override
    public void update() {
        p1.update();
        p2.update();
    }

    public void dispose() {

    }
}
