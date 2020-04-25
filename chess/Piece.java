package chess;

import gameutils.GameObject;

import java.awt.*;

public class Piece extends GameObject {
    public Side side;
    public Point currentPosition;
    public PieceType type;
    public boolean out = false;

    public Piece(Side side, Point position, PieceType type){
        super();
        this.side = side;
        this.currentPosition = position;
        this.type = type;
    }

    @Override
    public void update() {
        if(!out) {
            setPosition(currentPosition);
        }
    }

    public void drawPiece(Graphics g){
        g.drawImage(getTexture(), currentPosition.x, currentPosition.y, null);
    }
}
