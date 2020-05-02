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
    private BoardSquare[][] squaresArr = ChessBoard.squares;

    public Piece(Side side, BoardSquare square, PieceType type){
        super();
        this.side = side;
        this.type = type;
        this.row = square.row;
        this.col = square.column;
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
            //whether piece is moving up or down
            int direction = -1;
            if(side.equals(Side.BLACK)){
                direction = 1;
            }
            //if the square right in front is empty
            if(!squaresArr[row + direction][col].isOccupied()){
                possibleMoves.add(squaresArr[row+direction][col]);
            }
            //if diagonals are occupied by opposite team's piece
            if(col != 0 && squaresArr[row+direction][col-1].isOccupied()){
                if(!squaresArr[row+direction][col-1].getPiece().side.equals(this.side)){
                    possibleMoves.add(squaresArr[row+direction][col-1]);
                }
            }
            if(col != 7 && squaresArr[row+direction][col+1].isOccupied()){
                if(!squaresArr[row+direction][col+1].getPiece().side.equals(this.side)){
                    possibleMoves.add(squaresArr[row+direction][col+1]);
                }
            }
            //if pawn is still in its initial position, add the spot two rows ahead
            if(side.equals(Side.WHITE)){
                if(row == 6){ possibleMoves.add(squaresArr[row+ 2*direction][col]); }
            } else {
                if(row == 1){ possibleMoves.add(squaresArr[row+ 2*direction][col]); }
            }

        }
        //return arraylist of possible moves
        return possibleMoves;
    }

    public boolean isOut(){
        return out;
    }

    public Side getSide(){
        return side;
    }

    @Override
    public void update() {
        if(!out) {
            setPosition(position);
            setBounds();

        }
    }


}
