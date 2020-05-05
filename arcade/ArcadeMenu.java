package arcade;

import gameutils.Game;

/**
 * This class represents a fancier version of the original arcade menu that we created, where you use the
 * keyboard to move the ball over the icon for the game you want to play, and once you are over an icon for a game
 * it highlights that icon, and if you press "Enter" the game opens up
 *
 * We did not have this working quite right for the presentation, so the simpler Arcade was used instead on
 * Monday night, however this is working now except for when you close the game window, it closes all of the windows
 * whereas with the other Arcade class we created, the window with the buttons is still open in the background and
 * you can choose another game
 *
 * We suspected that our problems that we had with this implementation were because we created it as
 * an extension of the game class, and realized that it was essentially having the constant thread running
 * for both the "Game" ArcadeMenu as well as the game-inside-a-game, whichever actual game was chosen
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */

public class ArcadeMenu extends Game {

    private ArcadeScreen arcadeScreen;

    /**
     * The constructor creates a new instance of the arcade screen
     * @param name The name of the window
     */
    public ArcadeMenu(String name) {
        super(name);
        arcadeScreen = new ArcadeScreen();
        changeScreen(arcadeScreen);
    }

    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new ArcadeMenu("Arcade"));
    }
}
