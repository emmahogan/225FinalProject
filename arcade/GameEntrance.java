package arcade;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;

public class GameEntrance extends GameObject {


    private boolean containsWalker;

    public GameEntrance(Point position, Texture texture){
        containsWalker = false;
        this.position = position;
        this.texture = texture;
        texture.scale((double) ArcadeScreen.ICON_SIZE/texture.getWidth(), (double) ArcadeScreen.ICON_SIZE/texture.getHeight());
        setBounds();
    }



public boolean isContainsWalker(){
        return containsWalker;
}

    public void setContainsWalker(boolean b){
        containsWalker = b;
    }

    @Override
    public void update() {

    }

    public String toString(){
        return "Contains walker: " + containsWalker;
    }

}
