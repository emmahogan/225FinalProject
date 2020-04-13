package gameutils;

import gameutils.Controller;
import gameutils.Screen;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Abstract class GameBoard - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Game implements Runnable {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    private Controller controller;
    private JFrame frame;
    private Screen screen;

    public Game(String name) {
        this.frame = new JFrame(name);
    }

    public void run(){
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.pack();
        frame.setVisible(true);

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        sleep(33);
                    }
                    catch (InterruptedException e) {}

                    controller.handleKeyInput();
                    screen.update();
                    screen.render(screen.g);
                }
            }
        }.start();
    }

    public Screen getScreen(Screen screen) {
        return screen;
    }

    public void changeScreen(Screen screen) {
        if (this.screen == null) {
            this.screen = screen;
            frame.add(this.screen);
        }
        else {
            Screen oldScreen = this.screen;
            this.screen = screen;
            frame.add(this.screen);
            frame.remove(oldScreen);
            screen.addKeyListener(controller);
            screen.addMouseListener(controller);
            screen.addMouseMotionListener(controller);
        }
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
