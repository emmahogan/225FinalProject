package gameutils;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * An abstract Game class to make creating games simpler
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public abstract class Game implements Runnable {
    public static final int FRAME_WIDTH = 600;
    public static final int FRAME_HEIGHT = 600;
    private JFrame frame;
    private Screen screen;

    /**
     * Generic constructor for a Game
     * @param name
     */
    public Game(String name) {
        this.frame = new JFrame(name);
    }

    /**
     * Runs this game in a new JFrame window
     */
    @Override
    public void run(){
        Thread gameManager = new Thread() {
            public void run() {
                while (true) {
                    try { sleep(16); }
                    catch (InterruptedException e) {}

                    screen.controller.handleKeyInput();
                    screen.update();
                    screen.repaint();
                }
            }
        };
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
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
     * @param screen the new screen to change to
     */
    public void changeScreen(Screen screen) {
        if (this.screen != null) {
            frame.remove(this.screen);
        }
        this.screen = screen;
        frame.add(this.screen);
        screen.addKeyListener(this.screen.controller);
        screen.addMouseListener(this.screen.controller);
        screen.addMouseMotionListener(this.screen.controller);
    }
}
