package chess;
import gameutils.Game;
import gameutils.Controller;
import gameutils.Screen;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * Write a description of class Chess here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Chess extends Game
{
    private ChessBoard board;

    /**
     * Constructor for objects of class Chess
     */
    public Chess(String name)
    {
        super(name);
        this.board = new ChessBoard();
        //this.controller = new ChessController();
        changeScreen(board);
    }

    
}
