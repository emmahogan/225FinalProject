package chess;

import gameutils.GameObject;
import gameutils.Texture;

import java.awt.*;
import java.util.ArrayList;

/**
 * Write a description of class ChessBoard here.
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */

public class Piece extends GameObject {
    private Side side;
    public PieceType type;
    public boolean out = false;
    private ArrayList<BoardSquare> possibleMoves = new ArrayList<BoardSquare>();
    public int row;
    public int col;
    private BoardSquare[][] squaresArr = ChessBoard.squares;

    //True if piece has been moved at least once during current game
    private boolean hasBeenMoved = false;

    public Piece(Side side,int row, int col, PieceType type){
        super();
        this.side = side;
        this.type = type;
        this.row = row;
        this.col = col;
        this.hasBeenMoved = false;
        this.position = new Point(ChessBoard.BORDER_WIDTH + col*ChessBoard.SQUARE_SIZE, ChessBoard.BORDER_WIDTH + row*ChessBoard.SQUARE_SIZE);
        setTexture(side,type);
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

        //Scale texture to the size of a square on the Chess Board
        texture.scale((double)ChessBoard.SQUARE_SIZE/(double)texture.getWidth(),(double)ChessBoard.SQUARE_SIZE/(double)texture.getWidth());
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
                getMovesKing();
                break;
            case QUEEN:
                getMovesRook();
                getMovesBishop();
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





    public void getMovesKing(){
        movesKingHelper( 1,  0, row, col);
        movesKingHelper(0, 1, row, col);
        movesKingHelper(1, 1, row, col);
        movesKingHelper(0, -1, row, col);
        movesKingHelper(-1, 0, row, col);
        movesKingHelper(-1, -1, row, col);
        movesKingHelper(-1, 1, row, col);
        movesKingHelper(1, -1, row, col);



    }

    private void movesKingHelper(int rowAdd, int colAdd, int row, int col){
        if(isOnBoard(row + rowAdd, col+colAdd)){
            if(!squaresArr[row+rowAdd][col+colAdd].isOccupied()) {
                possibleMoves.add(squaresArr[row + rowAdd][col + colAdd]);
            } else{
                if(!squaresArr[row + rowAdd][col + colAdd].getPiece().getSide().equals(side)) {
                    possibleMoves.add(squaresArr[row + rowAdd][col + colAdd]);
                }
            }
        }
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
        //if pawn is still in its initial position
        if((side.equals(Side.WHITE) && row == 6) || (side.equals(Side.BLACK) && row == 1)){

            //if both of those spots in front of it are not occupied
            if(!squaresArr[row+ direction][col].isOccupied() && !squaresArr[row+ 2*direction][col].isOccupied()){

                //add the spot two rows ahead to possible moves
                possibleMoves.add(squaresArr[row+ 2*direction][col]);
            }
        }
    }


    /**
     * Method to check for King if castling should be an option added in possible moves
     * If so, the move is added to the possible moves array
     */
    private void checkCastling(){
        //the index of the back row
        int backrow;

        //set to appropriate index depending on team
        if(side.equals(Side.WHITE)){
            backrow = 7;
        }
        else {
            backrow = 0;
        }

        //if the king is located in the back row
        if(row == backrow && col == 4){

            //Check left side
            //check if 3 spots to left are not occupied and one all the way to left is occupied
            if(squaresArr[row][0].isOccupied() && !squaresArr[row][1].isOccupied() && !squaresArr[row][2].isOccupied() && !squaresArr[row][3].isOccupied()){

                //if left corner is occupied with a rook of the same side
                Piece p = squaresArr[row][0].getPiece();
                if(p.getType().equals(PieceType.ROOK) && side.equals(p.getSide())){

                    //add 2 columns to the left to possiblemoves array, completeMove method in
                    //chess board class will handle the rest if executed
                    possibleMoves.add(squaresArr[row][2]);
                }

            }

            //Check right side
            //check if 2 spots to right are not occupied and one all the way to right is occupied
            if(squaresArr[row][7].isOccupied() && !squaresArr[row][6].isOccupied() && !squaresArr[row][5].isOccupied()){

                //if left corner is occupied with a rook of the same side
                Piece p = squaresArr[row][0].getPiece();
                if(p.getType().equals(PieceType.ROOK) && side.equals(p.getSide())){

                    //add 2 columns to the left to possiblemoves array, completeMove method in
                    //chess board class will handle the rest if executed
                    possibleMoves.add(squaresArr[row][2]);
                }

            }

        }


    }

    public void setHasBeenMoved(boolean val){
        hasBeenMoved = val;
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

    public PieceType getType(){
        return type;
    }

    public void setType(PieceType type){
        this.type = type;
    }

    @Override
    public void update() {
        if(!out) {

        }
    }


}
