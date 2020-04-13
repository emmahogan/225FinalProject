package examplegame;

import gameutils.Game;

public class ExampleGame extends Game {
    private PlayScreen playScreen;

    public ExampleGame(String name) {
        super(name);
        playScreen = new PlayScreen();
        changeScreen(playScreen);
    }
}
