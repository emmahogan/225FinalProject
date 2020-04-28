package chess;

import gameutils.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Write a description of class ChessController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ChessController extends Controller
{
    private ArrayList<Piece> white;
    private ArrayList<Piece> black;

    //true for black, false for white
    public boolean isBlackTurn = true;

    /**
     * Constructor for objects of class ChessController
     */
    public ChessController(ArrayList<Piece> white, ArrayList<Piece> black)
    {
        super();
        this.white = white;
        this.black = black;
        for(Piece p: white){

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void handleKeyInput(){

    }
}
