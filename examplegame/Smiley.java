package examplegame;

import java.awt.Point;

import gameutils.GameObject;
import gameutils.Texture;

public class Smiley extends GameObject {

    private Point center;

    public Smiley() {
        super();
        this.texture = new Texture("assets/examplegame/smileyface.png");
        this.texture.scale(.1f, .1f);
        setBounds();

        center = new Point((ExampleGame.FRAME_WIDTH / 2) - (texture.getWidth() / 2),
                (ExampleGame.FRAME_HEIGHT / 2) - (texture.getHeight() / 2));

        setPosition(center);
    }

    @Override
    public void update() {
        position.y += 2;
    }
}
