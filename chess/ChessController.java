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

    //are sides in check
    public boolean blackInCheck = false;
    public boolean whiteInCheck = false;

    /**
     * Constructor for objects of class ChessController
     */
    public ChessController(ArrayList<Piece> white, ArrayList<Piece> black)
    {
        super();
        this.white = white;
        this.black = black;

        removeListeners();
        for(Piece p: white){
            if(p.out == false) {
                ChessBoard.getButton(p.row, p.col).addMouseListener(this);
            }
        }
    }

    public void removeListeners(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                ChessBoard.getButton(i,j).removeMouseListener(this);
            }
        }
    }

    public boolean isInCheck(Side side){
        if(side.equals(Side.BLACK)){
            return blackInCheck;
        } else {
            return whiteInCheck;
        }
    }

    public Side getTurn(){
        if(isBlackTurn){
            return Side.BLACK;
        } else{
            return Side.WHITE;
        }
    }


    public boolean canBeChosen(Piece p){
        if(!p.out){
            if(isBlackTurn){
                if(p.side.equals(Side.BLACK)){
                    return true;
                }
            } else {
                if(p.side.equals(Side.WHITE)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void handleKeyInput(){

    }


}
