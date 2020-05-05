package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * This class represents a Square on a ChessBoard, containing information such as whether the
 * square is occupied and if so which piece is on it, and also containing methods to add or remove
 * a piece and lots of accessors used throughout other classes in this game
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */

public class BoardSquare {
    //whether square has piece in it
    boolean occupied;
    //upper left corner of square
    private Point upperLeft;
    //row on board
    public int row;
    //column on board
    public int column;
    //piece currently on square
    private Piece piece;
    //side length of square
    private static final int SIZE = ChessBoard.SQUARE_SIZE;

    //The colors used for the squares
    private static final Color DARK_GOLD = new Color(218,165,32);
    private static final Color LIGHT_GOLD = new Color(255,215,0);

    /**
     * The constructor for the BoardSquare sets the row and column, sets its initial values to represent
     * an empty square with no piece on it yet, and calculates the upper left point
     *
     * @param row The index of the row of the square on the board
     * @param column The index of the column of the square on the board
     */
    public BoardSquare(int row, int column){
        //sets row and column
        this.row = row;
        this.column = column;

        //no piece on the square yet
        occupied = false;
        piece = null;

        //calculates upper left
        upperLeft = new Point(column*SIZE + ChessBoard.BORDER_WIDTH, row*SIZE + ChessBoard.BORDER_WIDTH);
    }


    //////////////////////////////////// RENDER AND UPDATE ///////////////////////////////////////////////
    /**
     * This method is called each time the board is repainted to draw the square with the correct color
     *
     * @param g Graphics object
     */
    public void drawSquare(Graphics g){

        //Decides whether the square should be light or dark gold based on its row and col position
        if((row+column)%2 == 1){
            g.setColor(DARK_GOLD);
        }else{
            g.setColor(LIGHT_GOLD);
        }

        //draws filled square with color
        g.fillRect(ChessBoard.BORDER_WIDTH + row*SIZE, ChessBoard.BORDER_WIDTH + column*SIZE, SIZE, SIZE);

        //draws black border around square
        g.setColor(Color.BLACK);
        g.drawRect(ChessBoard.BORDER_WIDTH + row*SIZE, ChessBoard.BORDER_WIDTH + column*SIZE, SIZE, SIZE);
    }


    /**
     * This method is called each time the board is repainted to draw the correct piece on a square,
     * if it is currently occupied
     *
     * @param g The graphics object
     */
    public void drawPiece(Graphics g){

        //if square is occupied
        if(occupied) {
            //draw the piece on it
            g.drawImage(this.piece.getTexture(), upperLeft.x, upperLeft.y, null);

        }
    }


    /**
     * The update method for this class checks if there is a piece on the square, and if so
     * updates that piece to be on the given square
     */
    public void update(){
        //if the piece attribute is not null
        if(piece != null){
            //set the piece's position to this square's position and set the bounds
            piece.setPosition(new Point(upperLeft.x, upperLeft.y));
            piece.setBounds();
            piece.setRowCol(row, column);
        }
    }


    ////////////////////////////////////////// ADDING AND REMOVING PIECES ////////////////////////////////////////

    /**
     * This method is called to add a piece to an empty square
     *
     * @param p The piece to add
     */
    public void addPiece(Piece p){
        //sets occupied to true
        this.occupied = true;
        //sets piece to the input piece
        this.piece = p;
    }

    /**
     * This method is called to replace a piece on a square with the input piece
     *
     * @param pieceMoved The new piece
     */
    public void replacePiece(Piece pieceMoved){
        //sets piece to the input piece
        this.piece = pieceMoved;
    }

    /**
     * This method is called to remove a piece from a square
     */
    public void removePiece(){
        //sets occupied to false
        this.occupied = false;
        //sets the piece attribute of the square to null
        this.piece = null;
    }


    //////////////////////////////////////// ACCESSORS /////////////////////////////////////////////////////

    /**
     * Accessor for whether the square is occupied
     *
     * @return true if currently occupied, false otherwise
     */
    public boolean isOccupied(){
        return occupied;
    }

    /**
     * Accessor for the piece currently on square
     *
     * @return The piece if there is a piece, null otherwise
     */
    public Piece getPiece(){
        return piece;
    }

    /**
     * Accessor for the upper left position of the square
     *
     * @return The point representing the upper left corner of the square
     */
    public Point getPos(){
        return upperLeft;
    }

    /**
     * Accessor for the row of the square
     *
     * @return the index of the row of the square
     */
    public int getRow(){
        return row;
    }

    /**
     * Accessor for the column of the square
     *
     * @return The index of the column on the board
     */
    public int getCol(){
        return column;
    }


    /**
     * To string method used in testing
     *
     * @return string representation of a square
     */
    public String toString(){
        return "" + row + " " + column + " Is occopied: " + isOccupied() + " Value of Piece: " +  piece;}






}
