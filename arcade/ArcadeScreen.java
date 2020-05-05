package arcade;

import java.awt.*;
import java.util.ArrayList;

import gameutils.Screen;
import gameutils.Texture;

import javax.swing.*;


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
public class ArcadeScreen extends Screen {

    public static final int ICON_BORDER = 5;
    public static final int ICON_SIZE = 140;
    public static final int PADDING = 21;

    public static ArrayList<GameEntrance> gameEntrances;

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

        initGameEntrences();

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

    private void initGameEntrences() {
        GameEntrance smiley;
        GameEntrance speedRacers;
        GameEntrance chess;
        GameEntrance mars;
        GameEntrance frog;
        GameEntrance rise;

        smiley = new GameEntrance(new Point(PADDING + ICON_BORDER, 100 + PADDING + ICON_BORDER),
                new Texture("assets/arcade/smileyIcon.png"));
        speedRacers = new GameEntrance(new Point(PADDING * 3 + ICON_BORDER * 3 + ICON_SIZE, 100 + PADDING + ICON_BORDER),
                new Texture("assets/arcade/racingIcon.png"));
        chess = new GameEntrance(new Point(PADDING * 5 + ICON_BORDER * 5 + ICON_SIZE * 2, 100 + PADDING + ICON_BORDER),
                new Texture("assets/arcade/chessIcon.png"));
        mars = new GameEntrance(new Point(PADDING + ICON_BORDER, 100 + PADDING * 5 + ICON_BORDER * 3 + ICON_SIZE),
                new Texture("assets/arcade/marsIcon.png"));
        frog = new GameEntrance(new Point(PADDING * 3 + ICON_BORDER * 3 + ICON_SIZE, 100 + PADDING * 5 + ICON_BORDER * 3 + ICON_SIZE),
                new Texture("assets/arcade/froggerIcon.png"));
        rise = new GameEntrance(new Point(PADDING * 5 + ICON_BORDER * 5 + ICON_SIZE * 2, 100 + PADDING * 5 + ICON_BORDER * 3 + ICON_SIZE),
                new Texture("assets/arcade/riseIcon.png"));

        gameEntrances.add(smiley);
        gameEntrances.add(speedRacers);
        gameEntrances.add(chess);
        gameEntrances.add(rise);
        gameEntrances.add(frog);
        gameEntrances.add(mars);
    }
}
