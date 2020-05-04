package chess;

import gameutils.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Write a description of class ChessBoard here.
 *
 * @author Justin, Andrew, Emma, Tim, Nick
 * @version Spring 2020
 */
public class ChessBoard extends Screen implements ActionListener, MouseListener
{
    //Panel dimensions
    public static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 600;

    //Board measurements
    public static final int SQUARE_SIZE = 50;
    public static final int BORDER_WIDTH = (FRAME_WIDTH - (8*SQUARE_SIZE))/2;
    //Colors for Board

    private static final Color BACKGROUND_COLOR = new Color(184,134,11);

    //Main panel and board panel
    private JPanel mainPanel = new JPanel(new BorderLayout());
    private JPanel boardPanel = new JPanel(new GridLayout(8, 8));

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
    private static Point[][] positions = new Point[8][8];

    //Array of Squares
    public static BoardSquare[][] squares = new BoardSquare[8][8];

    //true for black, false for white
    public boolean isBlackTurn;

    //are sides in check
    public boolean blackInCheck = false;
    public boolean whiteInCheck = false;

    //for each move, index 0 is the BoardSquare with the piece they chose to move, index 1 is the destination
    //they choose to move it to
    private BoardSquare[] move = new BoardSquare[2];

    //possible moves to highlight
    ArrayList<BoardSquare> highlight = new ArrayList<BoardSquare>();

    //restart button
    JButton restartButton = new JButton("Restart");


    /**
     * Constructor for objects of class ChessBoard
     */
    public ChessBoard()
    {
        super();
        initGame();
        this.controller = new ChessController();
        repaint();
        this.addMouseListener(this);
    }
    
    /////////////////////////////////// INITIALIZING NEW GAME /////////////////////////////////////////////

    /**
     *
     */
    private void initGame(){

        //Initialize all 16 squares on board as new BoardSquare objects, and
        //also initialize array of points representing the square's upper left corners
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                //Initialize all the BoardSquare objects
                squares[i][j] = new BoardSquare(i,j);

                //use BoardSquare's method to calculate Point of upper left for each square
                positions[i][j] = squares[i][j].getPos();
            }
        }

        //Call separate method to create piece objects and put at starting positions
        initPieces(Side.BLACK);
        initPieces(Side.WHITE);

        //Restart button
        topPanel.add(restartButton);

        //White turn first
        isBlackTurn = false;
    }

    /**
     * Create all pieces for given side and set to starting positions for new game, as
     * well as adding them to the side's piece array and adding the piece to the
     * BoardSquare object where it starts
     *
     * @param s which side (black or white)
     */
    public void initPieces(Side s){

        //Index of front and back row depending on which team
        int backRow;
        int frontRow;

        //used to reference the correct Side's piece array
        ArrayList<Piece> pieceArr;

        //depending on which side, set index of front row and back row, and
        //set pieceArr to reference the correct team's piece array
        if(s.equals(Side.BLACK)){
            backRow = 0;
            frontRow = 1;
            pieceArr = blackPieces;
        } else {
            backRow = 7;
            frontRow = 6;
            pieceArr = whitePieces;
        }

        //Construct pieces and set starting positions, as well as add piece to the
        //correct square using that BoardSquare's add piece method

        //make all Pawns
        Piece p;
        for(int i = 0; i < 8; i++){
            p = new Piece(s, frontRow, i, PieceType.PAWN);
            pieceArr.add(p);
            squares[frontRow][i].addPiece(p);
        }


        //Make rooks
        Piece rook1 = new Piece(s, backRow, 0, PieceType.ROOK);
        pieceArr.add(rook1);
        squares[backRow][0].addPiece(rook1);

        Piece rook2 = new Piece(s, backRow, 7, PieceType.ROOK);
        pieceArr.add(rook2);
        squares[backRow][7].addPiece(rook2);


        //Make bishops
        Piece bishop1 = new Piece(s, backRow, 1, PieceType.BISHOP);
        pieceArr.add(bishop1);
        squares[backRow][2].addPiece(bishop1);

        Piece bishop2 = new Piece(s,backRow, 6, PieceType.BISHOP);
        pieceArr.add(bishop2);
        squares[backRow][5].addPiece(bishop2);


        //Knights
        Piece knight1 = new Piece(s,backRow,2, PieceType.KNIGHT);
        pieceArr.add(knight1);
        squares[backRow][1].addPiece(knight1);

        Piece knight2 = new Piece(s,backRow,5, PieceType.KNIGHT);
        pieceArr.add(knight2);
        squares[backRow][6].addPiece(knight2);


        //Queen & King
        Piece queen = new Piece(s, backRow,3, PieceType.QUEEN);
        pieceArr.add(queen);
        squares[backRow][3].addPiece(queen);

        Piece king = new Piece(s, backRow, 4, PieceType.KING);
        pieceArr.add(king);
        squares[backRow][4].addPiece(king);
    }


    ///////////////////////////////// REPAINTING AND UPDATING //////////////////////////////////////


    public void render(Graphics g){
        for(int i = 0; i <8; i++){
            for(int j = 0; j < 8; j++){
                //Initialize all the squares
                squares[i][j].update();
            }
        }
        drawBoard(g);

        if(move[0] != null && move[1] == null){
            move[0].getPiece().update();
            highlight.clear();
            highlight = move[0].getPiece().getPossibleMoves();
            for(BoardSquare s: highlight){
                highlightPossMoves(s, g);
            }
        }

    }

    public void drawBoard(Graphics g) {

        setBackground(BACKGROUND_COLOR);
        g.setColor(Color.BLACK);
        g.drawRect(BORDER_WIDTH, BORDER_WIDTH, FRAME_WIDTH - 2 * BORDER_WIDTH, FRAME_WIDTH - 2 * BORDER_WIDTH);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].drawSquare(g);
            }
        }

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                squares[row][col].drawPiece(g);
            }
        }

        topPanel.add(restartButton);
        restartButton.setVisible(true);
    }


    /**
     * Method to highlight a square on the board that is a possible move with a blue border
     *
     * @param s Square to highlight
     * @param g the Graphics object
     */
    public void highlightPossMoves(BoardSquare s, Graphics g){
        g.setColor(new Color(150,255,255, 100));
        g.fillRect(positions[s.getRow()][s.getCol()].x + 1, positions[s.getRow()][s.getCol()].y + 1, SQUARE_SIZE-2, SQUARE_SIZE-2);
    }


    public void update(){
        for(int i = 0; i <8; i++){
            for(int j = 0; j < 8; j++){
                //Initialize all the squares
                squares[i][j].update();
            }
        }
        for(Piece p: blackPieces){
            p.update();
        }
        for(Piece p: whitePieces){
            p.update();
        }

    }


    ////////////////////////////////////// ACCESSORS ////////////////////////////////////////

    /**
     * Accessor method for whose turn it is
     *
     * @return the Side whose turn it is
     */
    public Side getTurn(){
        if(isBlackTurn){
            return Side.BLACK;
        } else{
            return Side.WHITE;
        }
    }

    /**
     * Returns whether the input side is in check
     * TODO take out methods never used, or finish javadoc
     * @param side
     * @return
     */
    public boolean isInCheck(Side side){
        if(side.equals(Side.BLACK)){
            return blackInCheck;
        } else {
            return whiteInCheck;
        }
    }

    ////////////////////////////////// MOVE VALIDATION ////////////////////////////////////////////

    /**
     * TODO javadoc
     * TODO add special cases
     * @param s
     */
    public void validateMove(BoardSquare s) {
        //if square is occupied and piece can be chosen
        if (move[0] == null) {
            if (s.isOccupied()){
                Piece p = s.getPiece();
                if (canBeChosen(p)) {
                    //set square to first index in move array
                    move[0] = s;
                    System.out.print(p.getPossibleMoves());
                }
            }
        //a valid piece has been chosen
        } else {
            //if destination selected is not occupied
            if (!s.isOccupied()) {
                //if destination is in the possible moves arraylist of piece that was chosen
                if (move[0].getPiece().getPossibleMoves().indexOf(s) != -1) {
                    //set destination square and complete move
                    move[1] = s;
                    completeMove();
                }
            //if destination selected is occupied
            } else {
                //if it was on the same team, replace choice for move[0] with chosen piece
                Piece p = s.getPiece();
                if (canBeChosen(p)) {
                    move[0] = s;
                //if it was on the opposite team
                } else {
                    //knock out piece and complete move
                    System.out.println(move[0].toString());
                    if (move[0].getPiece().getPossibleMoves().contains(s)) {
                        move[1] = s;
                        completeMove();
                    }
                }
            }
        }
    }


    /**
     * TODO javadoc and inline
     * @param p
     * @return
     */
    public boolean canBeChosen(Piece p){
        if(!p.isOut()){
            if(isBlackTurn){
                if(p.getSide().equals(Side.BLACK)){
                    return true;
                }
            } else {
                if(p.getSide().equals(Side.WHITE)){
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * TODO javadoc for method
     * TODO add special cases
     */
    private void completeMove(){
        //the piece chosen to move
        Piece pieceMoved = move[0].getPiece();

        //set piece's hasBeenMoved to true
        pieceMoved.setHasBeenMoved(true);

        //remove piece from its current spot
        move[0].removePiece();

        //if it is taking out another piece call separate method
        if(move[1].isOccupied()){
            Piece pieceOut = move[1].getPiece();
            move[1].replacePiece(pieceMoved);
            knockout(pieceOut);
        }
        //move piece to destination
        else {
            move[1].addPiece(pieceMoved);
        }
        if(isBlackTurn){ isBlackTurn = false; }
        else { isBlackTurn = true; }

        //call method to check all special cases
        checkSpecialCases(pieceMoved);


        move[0] = null;
        move[1] = null;
        System.out.println(squares[0][5]);
        System.out.println(squares[2][4]);
    }


    private void checkSpecialCases(Piece p){

        //for pawns, call method to check special case
        if(p.getType().equals(PieceType.PAWN)){
            checkSpecialCasePawn(p);
        }

        //if king, check if castling was executed by seeing whether it is being
        //moved more than one spot over
        if(move[0] != null) {
            int moveRow = move[0].getRow();
            if (p.getType().equals(PieceType.KING)) {

                //check castling left
                if (move[1].getCol() < (move[0].getCol() - 1)) {

                    //create new move and complete move of left rook to column 3
                    move[0] = squares[moveRow][0];
                    move[1] = squares[moveRow][3];
                    completeMove();

                    //switch back to other team's turn even though 2 pieces were moved
                    if (isBlackTurn) {
                        isBlackTurn = false;
                    } else {
                        isBlackTurn = true;
                    }
                }

                //check castling right
                else if (move[1].getCol() > (move[0].getCol() + 1)) {

                    //create new move and complete move of right rook to column 5
                    move[0] = squares[moveRow][7];
                    move[1] = squares[moveRow][5];
                    completeMove();

                    //switch back to other team's turn even though 2 pieces were moved
                    if (isBlackTurn) {
                        isBlackTurn = false;
                    } else {
                        isBlackTurn = true;
                    }
                }

            }
        }
    }

    /**
     * Check if a pawn has reached the other side of the board, and if so, then
     * replace with a queen
     *
     * @param p The pawn
     */
    private void checkSpecialCasePawn(Piece p){

            //Check if the pawn has reached the opposite side of the board
            //if so, make it a queen
            if(p.getSide().equals(Side.BLACK)){
                if(move[1].getRow() == 7){
                    p.setTexture(Side.BLACK, PieceType.QUEEN);
                    p.setType(PieceType.QUEEN);
                }
            } else {
                if (move[1].getRow() == 0) {
                    p.setTexture(Side.WHITE, PieceType.QUEEN);
                    p.setType(PieceType.QUEEN);
                }
            }
    }

    /**
     * Method to knock out piece that sets the piece's attribute to out, and adds it to the
     * correct team's arraylist of pieces knocked out
     *
     * Also checks if piece being knocked out is King, and if so ends game
     *
     * @param p the piece knocked out
     */
    private void knockout(Piece p){

        //If a king was knocked out, game is over
        if(p.getType().equals(PieceType.KING)) {
            gameOver();
        }

        //set piece's out attribute to true
        p.setOut(true);

        //add the piece to its team's arraylist of pieces knocked out
        if(p.getSide().equals(Side.WHITE)){
            whitePiecesOut.add(p);
        } else { blackPiecesOut.add(p); }
    }


    /**
     * Method called when a King is taken to clear all information from
     * current game and restart a new game
     */
    private void gameOver(){
        //Clear all piece arrays
        whitePieces.clear();
        blackPieces.clear();
        whitePiecesOut.clear();
        blackPiecesOut.clear();

        //clear move information
        move[0] = null;
        move[1] = null;

        //initialize new game
        initGame();
    }
    /**
     * TODO add method for check
     * @param e
     */

    ////////////////////////////// CALCULATIONS FOR CLICKS //////////////////////////////////

    /**
     * Check if a point is on the board
     * @param p the point
     * @return true if on board, false otherwise
     */
    public boolean isOnBoard(Point p){
        int xStart = positions[0][0].x;
        int xEnd = xStart + 8*SQUARE_SIZE;
        int yStart = positions[0][0].y;
        int yEnd = yStart + 8*SQUARE_SIZE;
        System.out.println(p);
        return p.x > xStart && p.x < xEnd && p.y > yStart && p.y < yEnd;
    }


    /**
     * Once it is known that a click happened somewhere on the board, this
     * method is called to tell which square the click was in
     *
     * @param p The point clicked
     *
     * @return The square the click was in
     */
    public BoardSquare whichSquareClicked(Point p){
        int col = (p.x - BORDER_WIDTH)/SQUARE_SIZE;
        int row = (p.y - BORDER_WIDTH)/SQUARE_SIZE;
        System.out.println(row + ", " + col);
        return squares[row][col];
    }


    ////////////////////////////////// EVENT HANDLING ///////////////////////////////////////


    /**
     * When the mouse is clicked, checks if the click was on the board and calls methods to
     * validate move
     *
     * @param e The MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //if click is on board
        Point p = e.getPoint();
        System.out.println("Mouse clicked somewhere atleast");
        if(isOnBoard(p)){
            BoardSquare s = whichSquareClicked(p);
            validateMove(s);
        }
    }

    /**
     * TODO if figured out add javadoc, else delete
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(restartButton)){
            initGame();
        }
    }

    //Other methods not used, but needed to be included

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void dispose(){}

}
