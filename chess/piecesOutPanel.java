package chess;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.util.ArrayList;

public class piecesOutPanel extends JPanel {
    //Height and width of panel
    private int height;
    private int width;

    ArrayList<Piece> piecesOut = new ArrayList<Piece>();

    public piecesOutPanel(int height, int width){
        super();
        this.height = height;
        this.width = width;
        this.setPreferredSize(new Dimension(width, height));
    }
}
