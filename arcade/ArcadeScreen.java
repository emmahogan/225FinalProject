package arcade;

import java.awt.*;
import java.util.ArrayList;

import gameutils.Screen;

public class ArcadeScreen extends Screen {

    private ArrayList<GameEntrance> games;

    private GameEntrance smiley;
    private GameEntrance speedRacers;
    private GameEntrance chess;

    private Walker walker;

    public ArcadeScreen() {
        super();
        walker = new Walker();
        games = new ArrayList<GameEntrance>();
        this.controller = new ArcadeController();

        speedRacers = new GameEntrance(new Point(0, 0));

        games.add(speedRacers);
    }

    @Override
    public void render(Graphics g) {

        for(GameEntrance game: games){
            g.drawImage(game.getTexture(), game.position.x, game.position.y, null);
        }
            g.drawImage(walker.getTexture(), walker.position.x, walker.position.y, null );

    }

    @Override
    public void update() {
        for(GameEntrance game: games){
            game.update();
        }
            walker.update();


    }

    @Override
    public void dispose() {

    }
}
