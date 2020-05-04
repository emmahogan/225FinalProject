package arcade;

import gameutils.Game;

public class Arcade extends Game {

    private ArcadeScreen arcadeScreen;

    public Arcade(String name) {
        super(name);
        arcadeScreen = new ArcadeScreen();
        changeScreen(arcadeScreen);
    }

    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Arcade("Arcade"));
    }
}
