/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.figures;

import draft.basis.Position;
import draft.basis.Figure;
import java.util.ArrayList;
import java.util.*;

/**
 * Class draft implements all the queens actions
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Draft extends Figure {

    private List<Position> draftJump = new ArrayList<Position>();
    private List<Position> draftMove = new ArrayList<Position>();

    /**
     * Constructor of a new draft 
     * 
     * @param p position where the new draft will stand on
     * @param color color of the new draft
     */
    public Draft(Position p, int color) {
        super(p, color);
    }

    
    /**
     * this method get Lists of positions (move without jumps and jump = option of jumps)
     * @param cdir column difference between new and this position
     * @param rdir row difference between new and this position
     */
    private void canDraftMove(int cdir, int rdir) {
        int emptyWay = 0; // value witch represent number of figures in diagonal
        int color = 0; // 0 indicate different color, 1 same color
        for (int i = 1; this.getPosition().nextPosition(i * cdir, i * rdir) != null; i++) {
            // move in diagonal without jump
            if (emptyWay == 0 && this.getPosition().nextPosition(i * cdir, i * rdir).getFigure() == null) {
                System.out.println("magic");
                draftMove.add(this.getPosition().nextPosition(i * cdir, i * rdir));
            } // move in diagonal with jump
            else if (color == 0 && emptyWay == 1 && this.getPosition().nextPosition(i * cdir, i * rdir).getFigure() == null) {
                draftJump.add(this.getPosition().nextPosition(i * cdir, i * rdir));
            } // jumped figure
            else if (this.getPosition().nextPosition(i * cdir, i * rdir).getFigure() != null) {
                if (this.getPosition().getFigure().getColor() == this.getPosition().nextPosition(i * cdir, i * rdir).getFigure().getColor()) {
                    color = 1;
                } else {
                    color = 0;
                }
                emptyWay++;
            }
        }
        System.out.println("can draft MOve "+ draftMove);
    }

    /**
     * 
     * @return list of positions which the draft can jump to
     */
    @Override
    public List<Position> getJump() {
        draftJump.clear();
        draftMove.clear();
        this.canDraftMove(1, 1);
        this.canDraftMove(1, -1);
        this.canDraftMove(-1, 1);
        this.canDraftMove(-1, -1);
/*
        if (draftJump.isEmpty() == true) {
            System.out.println("nic na vhodenie");
            return null;
        }
*/
        return draftJump;
    }

    /**
     * 
     * @return list of positions where the draft can move
     */
    @Override
    public List<Position> getMove() {
        draftJump.clear();
        draftMove.clear();
        this.canDraftMove(1, 1);
        
        this.canDraftMove(1, -1);
        this.canDraftMove(-1, 1);
        this.canDraftMove(-1, -1);

        /*if (draftMove.isEmpty() == true) {
            System.out.println("nic na pohyb");
            return null;
        }*/
        //System.out.println("can draft MOve "+ draftMove);
        return draftMove;
    }

    /**
     * 
     * @param p new position of the draft
     * @return true if draft can move from this to p, false otherwise
     */
    @Override
    public boolean canMove(Position p) {
        draftJump.clear();
        draftMove.clear();
        // is new position valid ??
        if (this.getPosition().getDesk().getPositionAt(p.getColumn(), p.getRow()) == null) {
            return false;
        }
        // is new position on diagonal ??
        if (this.getPosition().isOnDiagonal(p) != true) {
            return false;
        }

        // get all direction of move
        this.canDraftMove(1, 1);
        this.canDraftMove(1, -1);
        this.canDraftMove(-1, 1);
        this.canDraftMove(-1, -1);

        // if you must jump
        if (draftJump.isEmpty() != true) {
            Iterator itr = draftJump.listIterator();
            while (itr.hasNext()) {
                Position element = (Position) itr.next();
                if (element.equals(p)) {
                    return true;
                }
            }
        } else { // you can only move without jump
            Iterator itr = draftMove.listIterator();
            while (itr.hasNext()) {
                Position element = (Position) itr.next();
                if (element.equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Throwing out figures during the move
     * 
     * @param p new position of the draft
     * @return figure which was thrown out during the turn from this to p, null
     * if there was no figure on the way
     */
    @Override
    public Figure killFigure(Position p) {
        // get all direction of move
        this.canDraftMove(1, 1);
        this.canDraftMove(1, -1);
        this.canDraftMove(-1, 1);
        this.canDraftMove(-1, -1);

        if (draftJump.isEmpty() != true) {
            for (Position element : draftJump) {
                if (element.equals(p)) {
                    int cdir, rdir;

                    // get direction on row
                    if ((this.getPosition().getRow() - p.getRow()) < 0) {
                        rdir = 1;
                    } else {
                        rdir = -1;
                    }
                    if ((this.getPosition().getColumn() - p.getColumn()) < 0) {
                        cdir = 1;
                    } else {
                        cdir = -1;
                    }

                    for (int i = 1; this.getPosition().nextPosition(i * cdir, i * rdir) != null; i++) {
                        if (this.getPosition().nextPosition(i * cdir, i * rdir).getFigure() != null) {
                            return this.getPosition().nextPosition(i * cdir, i * rdir).removeFigure();
                        }
                    }
                }
            }
        }
        return null;
    }
}
