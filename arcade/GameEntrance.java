package arcade;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;


/**
 * This class is part of the experimental fancier version of the original arcade menu that we created, which we
 * did not end up fixing the main issue of not knowing how to have the window open in the back with the listener
 * still there to open another game if you close the window of the current game. Since we did not end up using this
 * in the final presentation, I am not including extensive javadoc comments for these classes (much of it is
 * similar to Arcade)
 * Please see "ArcadeMenu" class header for a more extensive description of what we were trying to do with
 * these classes.
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
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
