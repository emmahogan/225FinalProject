package chess;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;
import java.util.ArrayList;

public class Piece extends GameObject {
    private Side side;
    public PieceType type;
    public boolean out = false;
    private ArrayList<BoardSquare> possibleMoves = new ArrayList<BoardSquare>();
    public int row;
    public int col;
    private BoardSquare[][] squaresArr = ChessBoard.squares;

    public Piece(Side side,int row, int col, PieceType type){
        super();
        this.side = side;
        this.type = type;
        this.row = row;
        this.col = col;
        this.position = new Point(ChessBoard.BORDER_WIDTH + col*ChessBoard.SQUARE_SIZE, ChessBoard.BORDER_WIDTH + row*ChessBoard.SQUARE_SIZE);
        setTexture(side,type);
        texture.scale((double)ChessBoard.SQUARE_SIZE/(double)texture.getWidth(),(double)ChessBoard.SQUARE_SIZE/(double)texture.getWidth());
        setBounds();
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
        possibleMoves.clear();

        switch (type){
            case PAWN:
                getMovesPawn();
                break;
            case ROOK:
                getMovesRook();
                break;
            case KNIGHT:
                getMovesKnight();
                break;
            case BISHOP:
                getMovesBishop();
                break;
            case KING:
                break;
            case QUEEN:
                break;
        }

        //return arraylist of possible moves
        return possibleMoves;
    }

    private boolean isOnBoard(int row, int col){
        if(row < 0 || row > 7 || col < 0 || col > 7){
            return false;
        }
        return true;
    }




    private void getMovesRook(){
        //in each diagonal direction, while square is not occupied or off board, add to possible moves
        helperBishop(1, 0, row, col);
        helperBishop(0, -1, row, col);
        helperBishop(0, 1, row, col);
        helperBishop(-1, 0, row, col);
    }





    private void getMovesBishop(){
        //in each diagonal direction, while square is not occupied or off board, add to possible moves
        helperBishop(1, 1, row, col);
        helperBishop(1, -1, row, col);
        helperBishop(-1, 1, row, col);
        helperBishop(-1, -1, row, col);
    }

    private void helperBishop(int rowAdd, int colAdd, int row, int col){
        boolean stop = false;
        int i = rowAdd;
        int j = colAdd;
        while(!stop){
            if(isOnBoard(row+i, col+j)){
              if(!squaresArr[row+i][col+j].isOccupied()) {
                  possibleMoves.add(squaresArr[row + i][col + j]);
                  i += rowAdd;
                  j += colAdd;
              } else{
                  if(!squaresArr[row + i][col + j].getPiece().getSide().equals(side)) {
                      possibleMoves.add(squaresArr[row + i][col + j]);
                  }
                  stop = true;
              }
            }else { stop = true; }
        }
    }

    private void getMovesKnight(){
        //array list to hold all l shapes on board
        ArrayList<BoardSquare> couldMove = new ArrayList<BoardSquare>();
        couldMove.clear();

        //add l moves to couldMove
        if(isOnBoard(row -2, col -1)){ couldMove.add(squaresArr[row-2][col-1]); }
        if(isOnBoard(row -2, col +1)){ couldMove.add(squaresArr[row-2][col+1]); }

        if(isOnBoard(row -1, col -2)){ couldMove.add(squaresArr[row-1][col-2]); }
        if(isOnBoard(row +1, col -2)){ couldMove.add(squaresArr[row+1][col-2]); }

        if(isOnBoard(row +2, col -1)){ couldMove.add(squaresArr[row+2][col-1]); }
        if(isOnBoard(row +2, col +1)){ couldMove.add(squaresArr[row+2][col+1]); }

        if(isOnBoard(row +1, col +2)){ couldMove.add(squaresArr[row+1][col+2]); }
        if(isOnBoard(row -1, col +2)){ couldMove.add(squaresArr[row-1][col+2]); }

        for(BoardSquare s: couldMove){
            if(!s.isOccupied() || !s.getPiece().getSide().equals(side)){
                possibleMoves.add(s);
            }
        }

    }

    private void getMovesPawn(){
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
            if(!squaresArr[row+direction][col-1].getPiece().getSide().equals(side)){
                possibleMoves.add(squaresArr[row+direction][col-1]);
            }
        }
        if(col != 7 && squaresArr[row+direction][col+1].isOccupied()){
            if(!squaresArr[row+direction][col+1].getPiece().getSide().equals(this.side)){
                possibleMoves.add(squaresArr[row+direction][col+1]);
            }
        }
        //if pawn is still in its initial position, add the spot two rows ahead
        if(side.equals(Side.WHITE)){
            if(row == 6 && !squaresArr[row+ 2*direction][col].isOccupied()){ possibleMoves.add(squaresArr[row+ 2*direction][col]); }
        } else {
            if(row == 1 && !squaresArr[row+ 2*direction][col].isOccupied()){ possibleMoves.add(squaresArr[row+ 2*direction][col]); }
        }
    }


    public boolean isOut(){
        return out;
    }

    public void setOut(Boolean val){
        out = val;
    }

    public Side getSide(){
        return side;
    }

    public void setRowCol(int row, int col){
        this.row = row;
        this.col = col;
    }

    @Override
    public void update() {
        if(!out) {

        }
    }


}
