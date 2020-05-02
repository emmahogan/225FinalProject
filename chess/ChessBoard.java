package chess;

import gameutils.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import javax.swing.*;

/**
 * Write a description of class ChessBoard here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ChessBoard extends Screen implements MouseListener, MouseMotionListener
{
    //Panel dimensions
    private static final int FRAME_WIDTH = 600;
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
    public boolean isBlackTurn = true;

    //are sides in check
    public boolean blackInCheck = false;
    public boolean whiteInCheck = false;

    //for each move, index 0 is the BoardSquare with the piece they chose to move, index 1 is the destination
    //they choose to move it to
    private BoardSquare[] move = new BoardSquare[2];

    
    /**
     * Constructor for objects of class ChessBoard
     */
    public ChessBoard()
    {
        super();
        initGame();
        this.controller = new ChessController(whitePieces, blackPieces);
        repaint();
        this.addMouseListener(this);
    }
    
    public void drawBoard(Graphics g) {
        /**
         setBackground(BACKGROUND_COLOR);
         g.setColor(Color.BLACK);
         g.drawRect(BORDER_WIDTH, BORDER_WIDTH, FRAME_WIDTH - 2*BORDER_WIDTH, FRAME_WIDTH - 2*BORDER_WIDTH);
         for(int row = 0; row < 8; row++){
         for(int col = 0; col < 8; col++){
         g.setColor(Color.BLACK);
         g.drawRect(BORDER_WIDTH + row*SQUARE_SIZE, BORDER_WIDTH + col*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
         if((row+col)%2 == 1){
         g.setColor(DARK_GOLD);
         }else{
         g.setColor(LIGHT_GOLD);
         }
         g.fillRect(BORDER_WIDTH + row*SQUARE_SIZE, BORDER_WIDTH + col*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
         }
         }
         */
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
    }
    private void initGame(){
        //add panels
        this.add(mainPanel);
        topPanel.setPreferredSize(new Dimension(8*SQUARE_SIZE, BORDER_WIDTH));
        bottomPanel.setPreferredSize(new Dimension(8*SQUARE_SIZE, BORDER_WIDTH));
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        boardPanel.setPreferredSize(new Dimension(8*SQUARE_SIZE, 8*SQUARE_SIZE));
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.setVisible(false);

        //add mouse listeners to board panel, top panel, and bottom panel
        mainPanel.addMouseListener(this);
        boardPanel.addMouseListener(this);
        topPanel.addMouseListener(this);
        bottomPanel.addMouseListener(this);

        //for all squares:
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                //Initialize all the squares
                squares[i][j] = new BoardSquare(i,j);
                positions[i][j] = squares[i][j].getPos();
            }
        }

        //Call separate method to create piece objects and put at starting positions
        initPieces(Side.BLACK);
        initPieces(Side.WHITE);
    }

    /**
     * Create all pieces for given side and set to starting positions for new game
     *
     * @param s which side (black or white)
     */
    public void initPieces(Side s){
        int backRow;
        int frontRow;
        ArrayList<Piece> pieceArr;
        String color;

        //depending on which side, set index of rows and color for texture
        if(s.equals(Side.BLACK)){
            backRow = 0;
            frontRow = 1;
            color = "black";
            pieceArr = blackPieces;
        } else {
            backRow = 7;
            frontRow = 6;
            color = "white";
            pieceArr = whitePieces;
        }

        //Construct pieces and set starting positions
        //make Pawns
        Piece p;
        for(int i = 0; i < 8; i++){
            p = new Piece(s, squares[frontRow][i], PieceType.PAWN);
            pieceArr.add(p);
            squares[frontRow][i].addPiece(p);
        }
        //Make rooks
        Piece rook1 = new Piece(s, squares[backRow][0], PieceType.ROOK);
        pieceArr.add(rook1);
        squares[backRow][0].addPiece(rook1);

        Piece rook2 = new Piece(s,squares[backRow][7], PieceType.ROOK);
        pieceArr.add(rook2);
        squares[backRow][7].addPiece(rook2);

        //Make bishops
        Piece bishop1 = new Piece(s,squares[backRow][1], PieceType.BISHOP);
        pieceArr.add(bishop1);
        squares[backRow][1].addPiece(bishop1);

        Piece bishop2 = new Piece(s,squares[backRow][6], PieceType.BISHOP);
        pieceArr.add(bishop2);
        squares[backRow][6].addPiece(bishop2);
        //Knights
        Piece knight1 = new Piece(s,squares[backRow][2], PieceType.KNIGHT);
        pieceArr.add(knight1);
        squares[backRow][2].addPiece(knight1);

        Piece knight2 = new Piece(s,squares[backRow][5], PieceType.KNIGHT);
        pieceArr.add(knight2);
        squares[backRow][5].addPiece(knight2);
        //Queen & King
        Piece queen = new Piece(s,squares[backRow][3], PieceType.QUEEN);
        pieceArr.add(queen);
        squares[backRow][3].addPiece(queen);

        Piece king = new Piece(s,squares[backRow][4], PieceType.KING);
        pieceArr.add(king);
        squares[backRow][4].addPiece(king);
    }

    public void render(Graphics g){
        drawBoard(g);
        if(move[0] != null && move[1] == null){
            for(BoardSquare s: move[0].getPiece().getPossibleMoves()){
                highlightPossMoves(s, g);
            }
        }

    }
    public void update(){

    }
    public void dispose(){}



    public BoardSquare getSquare(int row, int col){
        return squares[row][col];
    }

    /**
     * Method to highlight a square on the board that is a possible move with a blue border
     * @param s Square to highlight
     * @param g the Graphics object
     */
    public void highlightPossMoves(BoardSquare s, Graphics g){
        g.setColor(new Color(150,255,255, 100));
        g.fillRect(positions[s.getRow()][s.getCol()].x + 1, positions[s.getRow()][s.getCol()].y + 1, SQUARE_SIZE-2, SQUARE_SIZE-2);
    }

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

    public BoardSquare whichSquareClicked(Point p){
        int col = (p.x - BORDER_WIDTH)/SQUARE_SIZE;
        int row = (p.y - BORDER_WIDTH)/SQUARE_SIZE;
        System.out.println(row + ", " + col);
        return squares[row][col];
    }


    public boolean isInCheck(Side side){
        if(side.equals(Side.BLACK)){
            return blackInCheck;
        } else {
            return whiteInCheck;
        }
    }


    public Side getTurn(){
        if(isBlackTurn){
            return Side.BLACK;
        } else{
            return Side.WHITE;
        }
    }



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

    private void completeMove(){
        Piece pieceMoved = move[0].getPiece();
        //remove piece from its current spot
        move[0].removePiece();
        System.out.println(move[1]);
        //if it is taking out another piece call separate method
        if(move[1].isOccupied()){
            Piece pieceOut = move[1].getPiece();
            knockout(pieceOut);
            move[1].replacePiece(pieceMoved);
        }
        //move piece to destination
        move[1].addPiece(pieceMoved);
        if(isBlackTurn){ isBlackTurn = false; }
        else { isBlackTurn = true; }


        move[0] = null;
        move[1] = null;
    }

    /**
     * Method to knock out piece
     * @param p the piece knocked out
     */
    private void knockout(Piece p){
        p.setOut(true);
        if(p.getSide().equals(Side.WHITE)){
            whitePiecesOut.add(p);
        } else { blackPiecesOut.add(p); }
    }


    @Override
    public void mousePressed(MouseEvent e) {
            //mouseClicked(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
