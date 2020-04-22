package chess;

import gameutils.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Write a description of class ChessBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ChessBoard extends Screen
{
    //Panel dimensions
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 400;
    //Board measurements
    private static final int BORDER_WIDTH = 20;
    private static final int SQUARE_SIZE = 45;
    //Colors for Board
    private static final Color DARK_GOLD = new Color(218,165,32);
    private static final Color LIGHT_GOLD = new Color(255,215,0);
    private static final Color BACKGROUND_COLOR = new Color(184,134,11);
    
    
    /**
     * Constructor for objects of class ChessBoard
     */
    public ChessBoard()
    {
        super();
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        repaint();
    }
    
    public void drawBoard(Graphics g){
        setBackground(BACKGROUND_COLOR);
        g.setColor(Color.BLACK);
        g.drawRect(BORDER_WIDTH, BORDER_WIDTH, FRAME_WIDTH - 2*BORDER_WIDTH, FRAME_WIDTH - 2*BORDER_WIDTH);
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++){
                g.setColor(Color.BLACK);
                g.drawRect(BORDER_WIDTH + row*SQUARE_SIZE, BORDER_WIDTH + col*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                if((row+col)%2 == 1){
                    g.setColor(DARK_GOLD);
                }else{
                    g.setColor(LIGHT_GOLD);
                }
                g.fillRect(BORDER_WIDTH + row*SQUARE_SIZE, BORDER_WIDTH + col*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }

    public void render(Graphics g){
        drawBoard(g);
    }
    public void update(){}
    public void dispose(){}
}
