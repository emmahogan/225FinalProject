package arcade;

import gameutils.Controller;

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
public class ArcadeController extends Controller {
    private Walker walker;


    public ArcadeController(Walker walker){
        super();
        this.walker = walker;
    }


    @Override
    public void handleKeyInput() {
        if (isKeyPressed(W))
            walker.position.y -= 4;
        if (isKeyPressed(A))
            walker.position.x -= 4;
        if (isKeyPressed(S))
            walker.position.y += 4;
        if (isKeyPressed(D))
            walker.position.x += 4;
        if (isKeyPressed(ENTER))
            walker.startGame();
    }
}
