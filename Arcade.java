import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import gameutils.Screen;

/**
 * Write a description of class MainScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Arcade extends MouseAdapter implements Runnable {
    private JFrame frame = new JFrame();
    private Screen currentScreen;
    public Screen nextScreen;
    private final int FRAME_WIDTH = 400;
    private final int FRAME_HEIGHT = 400;
    
    public void run(){
        frame.setDefaultLookAndFeelDecorated(true);
        frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setScreen(new TitleScreen());
        frame.add(currentScreen);
        
        frame.pack();
        frame.setVisible(true);
        
        new Thread() {
            public void run() {
                while (true) {
                    try {
                        sleep(33);
                    }
                    catch (InterruptedException e) {}
                    
                    currentScreen.update();
                    currentScreen.render(currentScreen.g);
                    
                    // if(currentScreen != nextScreen) {
                        // setScreen(nextScreen);
                    // }
                }
            }
        }.start();
    }
    
    public void setScreen(Screen screen) {
        this.currentScreen = screen;
    }
    
    public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Arcade());
    }
}
