package arcade;

import java.awt.Graphics;

import gameutils.Screen;

public class ArcadeScreen extends Screen {

    private GameEntrance smiley;
    private GameEntrance speedRacers;
    private GameEntrance chess;

    public ArcadeScreen() {
        super();
        this.controller = new ArcadeController();
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void update() {


    }

    @Override
    public void dispose() {

    }
}
