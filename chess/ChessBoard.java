package chess;

import gameutils.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.util.*;
import javax.swing.JPanel;

/**
 * Write a description of class ChessBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ChessBoard extends Screen
{
    //Panel dimensions
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;
    //Board measurements
    public static final int SQUARE_SIZE = 50;
    private static final int BORDER_WIDTH = (FRAME_WIDTH - (8*SQUARE_SIZE))/2;
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

    //Upper left points of all of the squares on the chess board
    Point[][] positions = new Point[8][8];
    //Whether the squares are occupied
    Boolean[][] occupied = new Boolean[8][8];
    
    /**
     * Constructor for objects of class ChessBoard
     */
    public ChessBoard()
    {
        super();
        this.controller = new ChessController(whitePieces, blackPieces);
        initGame();
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
                positions[i][j] = new Point(i*SQUARE_SIZE + BORDER_WIDTH, j*SQUARE_SIZE + BORDER_WIDTH);
                //set initial occupied values for all of the positions
                if(j <= 1 || j >= 6){
                    occupied[i][j] = true;
                } else {
                    occupied[i][j] = false;
                }
            }
        }

        //Call separate method to create piece objects and put at starting positions
        initPieces(Side.BLACK);
        initPieces(Side.WHITE);
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
        Piece p;
        for(int i = 0; i < 8; i++){
            p = new Piece(s, positions[i][frontRow], PieceType.PAWN);
            pieceArr.add(p);
        }
        //Make rooks
        pieceArr.add(new Piece(s, positions[0][backRow], PieceType.ROOK));
        pieceArr.add(new Piece(s, positions[7][backRow], PieceType.ROOK));
        //Make bishops
        pieceArr.add(new Piece(s, positions[1][backRow], PieceType.BISHOP));
        pieceArr.add(new Piece(s, positions[6][backRow], PieceType.BISHOP));
        //Knights
        pieceArr.add(new Piece(s, positions[2][backRow], PieceType.KNIGHT));
        pieceArr.add(new Piece(s, positions[5][backRow], PieceType.KNIGHT));
        //Queen & King
        pieceArr.add(new Piece(s, positions[3][backRow], PieceType.QUEEN));
        pieceArr.add(new Piece(s, positions[4][backRow], PieceType.KING));


    }

    public void render(Graphics g){
        drawBoard(g);
        for(Piece p: blackPieces){
            p.update();
            g.drawImage(p.getTexture(), p.getPosition().x, p.getPosition().y, null);
        }
        for(Piece p: whitePieces){
            p.update();
            g.drawImage(p.getTexture(), p.getPosition().x, p.getPosition().y, null);
        }
    }
    public void update(){}
    public void dispose(){}

    /**
     * See if square is currently occupied
     * @param col the square column
     * @param row the square row
     * @return true if occupied, false if not
     */
    public boolean isOccupied(int col, int row){
        return occupied[col][row];
    }

    /**
     * Method to highlight a square on the board that is a possible move with a blue border
     * @param col the column of the square
     * @param row the row of the square
     * @param g the Graphics object
     */
    public void highlightPossMove(int col, int row, Graphics g){
        g.setColor(Color.BLUE);
        g.drawRect(positions[col][row].x, positions[col][row].y, SQUARE_SIZE, SQUARE_SIZE);
    }
}
