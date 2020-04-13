package examplegame;

import java.awt.Graphics;

import gameutils.Screen;

public class PlayScreen extends Screen {
    private Smiley smiley;

    public PlayScreen() {
        super();
        this.smiley = new Smiley();
        this.controller = new ExampleController(this.smiley);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(smiley.getTexture(), smiley.getPosition().x, smiley.getPosition().y, null);
    }

    @Override
    public void update() {
        smiley.update();
    }
}
