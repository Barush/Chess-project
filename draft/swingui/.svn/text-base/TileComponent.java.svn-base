     /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.swingui;

import javax.swing.JLabel;
import draft.basis.*;
import draft.figures.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;

/**
 * Class TileComponent implements a tile on the desk
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class TileComponent extends JLabel {

    private Position p;
    private Color color;
    // images of figures
    private static final ImageIcon PAWN_WHITE = new ImageIcon(TileComponent.class.getResource("/img/PawnWhite.gif"));
    private static final ImageIcon PAWN_BLACK = new ImageIcon(TileComponent.class.getResource("/img/PawnBlack.gif"));
    private static final ImageIcon DRAFT_WHITE = new ImageIcon(TileComponent.class.getResource("/img/DraftWhite.gif"));
    private static final ImageIcon DRAFT_BLACK = new ImageIcon(TileComponent.class.getResource("/img/DraftBlack.gif"));
    private static final ImageIcon PAWN_BLACK_MATCH = new ImageIcon(TileComponent.class.getResource("/img/PawnBlackMatched.gif"));
    private static final ImageIcon PAWN_WHITE_MATCH = new ImageIcon(TileComponent.class.getResource("/img/PawnWhiteMatched.gif"));
    private static final ImageIcon DRAFT_BLACK_MATCH = new ImageIcon(TileComponent.class.getResource("/img/DraftBlackMatched.gif"));
    private static final ImageIcon DRAFT_WHITE_MATCH = new ImageIcon(TileComponent.class.getResource("/img/DraftWhiteMatched.gif"));
    

    /**
     * Constuctor of the tile component object
     * 
     * @param p position of the tile in the desk
     * @param color tile color
     */
    public TileComponent(Position p, Color color) {
        this.p = p;
        this.color = color;
        setPreferredSize(new Dimension(50, 50));
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        // set color of label
        if (color == Color.BLACK) {
            setBackground(Color.BLACK);
        } else {
            setBackground(Color.WHITE);
        }
        setOpaque(true);

    }

    /**
     * 
     * @return position of this tile
     */
    public Position getTilePosition() {
        return this.p;
    }

    /**
     * 
     * @return color of this tile
     */
    public Color getTileColor() {
        return this.color;
    }

    /**
     * 
     * @return row of this tile
     */
    public int getTileRow() {
        return this.p.getRow();
    }

    /**
     * 
     * @return column of this tile
     */
    public char getTileColumn() {
        return this.p.getColumn();
    }
    
    /**
     * Changes incon on the tile
     */
    public void setBlackMatchedPawn() {
        setIcon(PAWN_BLACK_MATCH);
    }
    
     /**
     * Changes incon on the tile
     */
    public void setWhiteMatchedPawn() {
        setIcon(PAWN_WHITE_MATCH);
    }
    
    /**
     * Changes incon on the tile
     */   
    public void setWhiteMatchedDraft() {
        setIcon(DRAFT_WHITE_MATCH);
    }
    
    /**
     * Changes incon on the tile
     */    
    public void setBlackMatchedDraft() {
        setIcon(DRAFT_BLACK_MATCH);
    }

    /**
     * this method update style of tile when the figure are there
     */
    public void updateTile() {
        // figure are in tile
        if (this.p.getFigure() != null) {
            // Draft figure
            if (this.p.getFigure() instanceof Draft) {
                if (this.p.getFigure().getColor() == 0) { // black figure
                    setIcon(DRAFT_BLACK);
                } else { // White figure
                    setIcon(DRAFT_WHITE);
                }
            } // Pawnn figure
            else {
                if (this.p.getFigure().getColor() == 0) { // Black figure
                    setIcon(PAWN_BLACK);
                } else { // White figure
                    setIcon(PAWN_WHITE);
                }
            }
        } // figure are not in tile
        else {
            setIcon(null);
        }
    }
}
