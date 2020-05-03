package chess;

import java.awt.*;

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

    private static final Color DARK_GOLD = new Color(218,165,32);
    private static final Color LIGHT_GOLD = new Color(255,215,0);

    public BoardSquare(int row, int column){
        this.row = row;
        this.column = column;
        occupied = false;
        piece = null;
        upperLeft = new Point(column*SIZE + ChessBoard.BORDER_WIDTH, row*SIZE + ChessBoard.BORDER_WIDTH);
    }

    public void drawSquare(Graphics g){
        if((row+column)%2 == 1){
            g.setColor(DARK_GOLD);
        }else{
            g.setColor(LIGHT_GOLD);
        }
        g.fillRect(ChessBoard.BORDER_WIDTH + row*SIZE, ChessBoard.BORDER_WIDTH + column*SIZE, SIZE, SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(ChessBoard.BORDER_WIDTH + row*SIZE, ChessBoard.BORDER_WIDTH + column*SIZE, SIZE, SIZE);
    }

    public void drawPiece(Graphics g){
        if(occupied) {
            this.piece.setPosition(upperLeft);
            this.piece.update();
            g.drawImage(this.piece.getTexture(), upperLeft.x, upperLeft.y, null);

        }
    }




    public void addPiece(Piece p){
        this.occupied = true;
        this.piece = p;
        p.setSquare(this);
        p.setPosition(getPos());
        p.setBounds();
    }

    public void replacePiece(Piece pieceMoved){
        this.piece = pieceMoved;
        pieceMoved.setSquare(this);
        pieceMoved.setPosition(getPos());
        pieceMoved.setBounds();
    }

    public void removePiece(){
        this.occupied = false;
        this.piece = null;
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

    public int getRow(){
        return row;
    }

    public int getCol(){
        return column;
    }


    public void update(){
        this.piece = getPiece();
        this.occupied = isOccupied();
    }

    public String toString(){
        return "" + row + " " + column + " Is occopied: " + isOccupied() + " Value of Piece: " +  piece;}






}
