package arcade;

import gameutils.Game;
import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;

public class Walker extends GameObject {

    private boolean isInGame;

    public Walker (){
        isInGame = false;
        position = new Point(ArcadeMenu.FRAME_WIDTH/2, ArcadeMenu.FRAME_HEIGHT/2);
        texture = new Texture("assets/runner/ball1.png");
        texture.scale(0.25, 0.25);
    }

    @Override
    public void update() {
        //Call a method that changes his texture texture for walk animation

    }


}
