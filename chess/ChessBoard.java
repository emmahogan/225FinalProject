package chess;

import gameutils.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
import javax.swing.JPanel;
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

    //Top and bottom panels that hold pieces knocked out
    private JPanel topPanel = new JPanel(new FlowLayout());
    private JPanel bottomPanel = new JPanel(new FlowLayout());

    //Each side's pieces currently knocked out
    public ArrayList<Piece> blackPiecesOut = new ArrayList<Piece>();
    public ArrayList<Piece> whitePiecesOut = new ArrayList<Piece>();

    //All pieces on board
    public ArrayList<Piece> blackPieces = new ArrayList<Piece>();
    public ArrayList<Piece> whitePieces = new ArrayList<Piece>();


    Point[][] positions = new Point[8][8];
    
    /**
     * Constructor for objects of class ChessBoard
     */
    public ChessBoard()
    {
        super();
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
    private void initGame(){
        //Initialize all the Points in positions array
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                positions[i][j] = new Point(i*SQUARE_SIZE, j*SQUARE_SIZE);
            }
        }

        //Call separate method to create piece objects and put at starting positions

    }

    /**
     * Create all pieces for given side and set to starting positions for new game
     *
     * @param s which side (black or white)
     */
    public void initPieces(Side s){
        int backRow;
        int frontRow;
        ArrayList<Piece> pieceArr;
        String color;

        //depending on which side, set index of rows and color for texture
        if(s.equals(Side.BLACK)){
            backRow = 0;
            frontRow = 1;
            color = "black";
            pieceArr = blackPieces;
        } else {
            backRow = 7;
            frontRow = 6;
            color = "white";
            pieceArr = whitePieces;
        }

        //Construct pieces and set starting positions
        //make Pawns
        for(int i = 0; i < 8; i++){
            Piece p = new Piece(s, positions[frontRow][i], PieceType.PAWN);

            pieceArr.add(p);
        }
    }

    public void render(Graphics g){
        drawBoard(g);
    }
    public void update(){}
    public void dispose(){}
}
