package arcade;

import gameutils.Game;

public class ArcadeMenu extends Game {

    private ArcadeScreen arcadeScreen;

    public ArcadeMenu(String name) {
        super(name);
        arcadeScreen = new ArcadeScreen();
        changeScreen(arcadeScreen);
    }

    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new ArcadeMenu("Arcade"));
    }
}
