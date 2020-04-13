package gameutils;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Write a description of class TitleScreen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public abstract class Screen extends JPanel {
    protected Controller controller;

    /**
     * Constructor for objects of class TitleScreen
     */
    public Screen() {
        super();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }
    
    public abstract void render(Graphics g);
    
    public abstract void update();
}
