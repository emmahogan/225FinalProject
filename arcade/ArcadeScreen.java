package arcade;

import java.awt.*;
import java.util.ArrayList;

import gameutils.Screen;
import gameutils.Texture;

import javax.swing.*;

public class ArcadeScreen extends Screen {

    public static final int ICON_BORDER = 5;
    public static final int ICON_SIZE = 140;
    public static final int PADDING = 21;

    public static ArrayList<GameEntrance> gameEntrances;

    private GameEntrance smiley;
    private GameEntrance speedRacers;
    private GameEntrance chess;
    private GameEntrance mars;
    private GameEntrance frog;
    private GameEntrance rise;


    private Walker walker;

    private JLabel label;

    public ArcadeScreen() {
        super();
        label = new JLabel("ARCADE");
        Font msgFont = new Font("Helvetica", Font.BOLD, 65);
        label.setFont(msgFont);
        label.setForeground(new Color(255, 255, 255, 200));

        walker = new Walker();
        gameEntrances = new ArrayList<GameEntrance>();
        this.controller = new ArcadeController(walker);

        smiley = new GameEntrance(new Point(PADDING + ICON_BORDER, 100 + PADDING + ICON_BORDER),  new Texture("assets/arcade/smileyIcon.png"));
        speedRacers = new GameEntrance(new Point(PADDING * 3 + ICON_BORDER * 3 + ICON_SIZE, 100 + PADDING + ICON_BORDER),  new Texture("assets/arcade/racingIcon.png"));
        chess = new GameEntrance(new Point(PADDING * 5 + ICON_BORDER * 5 + ICON_SIZE * 2, 100 + PADDING + ICON_BORDER),  new Texture("assets/arcade/chessIcon.png"));
        mars = new GameEntrance(new Point(PADDING + ICON_BORDER, 100 + PADDING * 5 + ICON_BORDER * 3 + ICON_SIZE),  new Texture("assets/arcade/marsIcon.png"));
        frog = new GameEntrance(new Point(PADDING * 3 + ICON_BORDER * 3 + ICON_SIZE, 100 + PADDING * 5 + ICON_BORDER * 3 + ICON_SIZE),  new Texture("assets/arcade/froggerIcon.png"));
        rise = new GameEntrance(new Point(PADDING * 5 + ICON_BORDER * 5 + ICON_SIZE * 2, 100 + PADDING * 5 + ICON_BORDER * 3 + ICON_SIZE),  new Texture("assets/arcade/riseIcon.png"));

        gameEntrances.add(smiley);
        gameEntrances.add(speedRacers);
        gameEntrances.add(chess);
        gameEntrances.add(mars);
        gameEntrances.add(frog);
        gameEntrances.add(rise);

        this.add(label);
    }

    @Override
    public void render(Graphics g) {
    g.setColor(new Color(100, 100, 100));
    g.fillRect(0, 0, ArcadeMenu.FRAME_WIDTH, ArcadeMenu.FRAME_HEIGHT);

        for(GameEntrance game: gameEntrances){
            g.setColor(new Color(255, 255, 255, 150));
            g.fillRect(game.position.x - ICON_BORDER, game.position.y - ICON_BORDER, ICON_SIZE + ICON_BORDER * 2, ICON_SIZE + ICON_BORDER * 2);

            g.drawImage(game.getTexture(), game.position.x, game.position.y, null);
            if(game.isContainsWalker()){
                g.fillRect(game.position.x - ICON_BORDER, game.position.y - ICON_BORDER, ICON_SIZE + ICON_BORDER * 2, ICON_SIZE + ICON_BORDER * 2);
            }
        }
            g.drawImage(walker.getTexture(), walker.position.x, walker.position.y, null );

    }

    @Override
    public void update() {

        for(GameEntrance game: gameEntrances){
            if(walker.collidesWith(game)){
                game.setContainsWalker(true);
                walker.setInGame(true, game);
                break;
            }
            else{
                game.setContainsWalker(false);
                if(walker.isInGame() && walker.getGame().equals(game)){
                    walker.setInGame(false, null);
                }
            }
            //game.update();
        }
        walker.update();


    }

    @Override
    public void dispose() {

    }
}
