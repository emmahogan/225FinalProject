package gameutils;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * An abstract Game class to make creating games simpler
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public abstract class Game implements Runnable {
    public static int FRAME_WIDTH;
    public static int FRAME_HEIGHT;
    private static JFrame frame;
    private static Screen screen;

    /**
     * Generic constructor for a Game
     * @param name the name of the game
     */
    public Game(String name) {
        frame = new JFrame(name);
        FRAME_WIDTH = 600;
        FRAME_HEIGHT = 600;
        screen = null;
    }

    /**
     * Constructor for a Game specifying the size
     * of the game window
     * @param name the name of the game
     * @param width the width of the window
     * @param height the height of the window
     */
    public Game(String name, int width, int height) {
        frame = new JFrame(name);
        FRAME_WIDTH = width;
        FRAME_HEIGHT = height;
    }

    /**
     * Runs this game in a new JFrame window
     */
    @Override
    public void run(){
        Thread gameManager = new Thread(() -> {
            while (true) {
                try { Thread.sleep(16); }
                catch (InterruptedException e) {}

                screen.update();
                if (screen.controller != null) {
                    screen.controller.handleKeyInput();
                }
                screen.repaint();
            }
        });
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT + 21));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                gameManager.stop();
            }
        });
        frame.pack();
        frame.setVisible(true);
        gameManager.start();
    }

    /**
     * Switches the screens and adds the new one
     * to the JFrame
     * @param newScreen the new screen to change to
     */
    public static void changeScreen(Screen newScreen) {
        if (screen != null) {
            frame.remove(screen);
        }
        screen = newScreen;
        frame.add(newScreen);
        if (newScreen.controller != null) {
            frame.addKeyListener(newScreen.controller);
            newScreen.addMouseListener(newScreen.controller);
            newScreen.addMouseMotionListener(newScreen.controller);
        }
    }

    private static void setExitOnClose(boolean exitOnClose) {
        if (exitOnClose) {
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } else {
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
    }
}
