package chess;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;
import java.util.ArrayList;

public class Piece extends GameObject {
    public Side side;
    public PieceType type;
    public boolean out = false;
    private ArrayList<BoardSquare> possibleMoves = new ArrayList<BoardSquare>();
    public int row;
    public int col;
    private BoardSquare square;

    public Piece(Side side, BoardSquare square, PieceType type){
        super();
        this.side = side;
        this.type = type;
        this.row = row;
        this.col = col;
        this.square = square;
        this.position = square.getPos();
        setTexture(side,type);
        texture.scale((double)ChessBoard.SQUARE_SIZE/(double)texture.getWidth(),(double)ChessBoard.SQUARE_SIZE/(double)texture.getWidth());
        //setPosition(position);
        setBounds();
        setPosition(position);
    }

    public void setTexture(Side side, PieceType type){
        String filepath = "assets/chess/";
        if(side.equals(Side.BLACK)){
            filepath += "black";
        } else {
            filepath += "white";
        }
        switch(type){
            case PAWN:
                filepath += "pawn";
                break;
            case KING:
                filepath += "king";
                break;
            case ROOK:
                filepath += "rook";
                break;
            case QUEEN:
                filepath += "queen";
                break;
            case BISHOP:
                filepath += "bishop";
                break;
            case KNIGHT:
                filepath += "knight";
                break;
        }
        filepath += ".png";
        texture = new Texture(filepath);
    }

    public ArrayList<BoardSquare> getPossibleMoves(){
        //if pawn
        if(type.equals(PieceType.PAWN)) {
            //black pieces
            if (side.equals(Side.BLACK)) {

            }
            //white piece
            else {

            }
        }
        //return arraylist of possible moves
        return possibleMoves;
    }

    @Override
    public void update() {
        if(!out) {
            setBounds();
            setPosition(position);
        }
    }


}
