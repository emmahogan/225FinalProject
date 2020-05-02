package chess;

import java.awt.Point;

public class BoardSquare {
    //whether square has piece in it
    boolean occupied;
    //upper left corner of square
    private Point upperLeft;
    //row on board
    private int row;
    //column on board
    private int column;
    //piece currently on square
    private Piece piece;
    //side length of square
    private static final int SIZE = ChessBoard.SQUARE_SIZE;

    public BoardSquare(int row, int column){
        this.row = row;
        this.column = column;
        occupied = false;
        piece = null;
        upperLeft = new Point(column*SIZE + ChessBoard.BORDER_WIDTH, row*SIZE + ChessBoard.BORDER_WIDTH);
    }

    public void addPiece(Piece p){
        occupied = true;
        piece = p;
    }

    public void removePiece(Piece p){
        occupied = false;
        piece = null;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public Piece getPiece(){
        return piece;
    }

    public Point getPos(){
        return upperLeft;
    }










}
