package examplegame;

import java.awt.Graphics;

import gameutils.Screen;

public class PlayScreen extends Screen {
    public Smiley smiley;

    public PlayScreen() {
        super();
        this.smiley = new Smiley();
        this.controller = new ExampleController(this.smiley);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(smiley.getTexure(), smiley.position.x, smiley.position.y, null);
    }

    @Override
    public void update() {
        smiley.update();
    }
}
