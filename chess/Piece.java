package chess;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;

public class Piece extends GameObject {
    public Side side;
    public PieceType type;
    public boolean out = false;

    public Piece(Side side, Point position, PieceType type){
        super();
        this.side = side;
        this.type = type;
        this.position = position;
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

    @Override
    public void update() {
        if(!out) {
            setBounds();
            setPosition(position);
        }
    }

}
