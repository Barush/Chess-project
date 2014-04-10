 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.basis;


import java.util.*;

/**
 * Class Figure implements the basic facts about figures in the game. It is 
 * abstract class. 
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */


public abstract class Figure {

    private Position pos;
    private int color;   // black = 0 white = 1
    private List<Position> jump = new ArrayList<Position>();
    private List<Position> move = new ArrayList<Position>();

    public void setPosition(Position p) {
        this.pos = p;
    }

    /**
     * Constructor of the Figure object
     * 
     * @param p position where the new figure will lie on
     * @param color color of the new figure - black = 0, white = 1
     */
    public Figure(Position p, int color) {
        p.putFigure(this);
        this.pos = p;
        this.color = color;
    }

    /**
     * 
     * @return position of this figure
     */
    public Position getPosition() {
        Position p = this.pos;
        return p;
    }

    /**
     * 
     * @param p position we are asking about
     * @return bool - true if this figure is at position p
     *              - false if this figure is not at position p
     */
    public boolean isAtPosition(Position p) {
        if (p.sameColumn(this.pos) && p.sameRow(this.pos)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to move the figure to new position
     * 
     * @param p position where the figure should be after the move
     * @return if there is a figure in position p, it is thrown out and returned 
     * by this method, otherwise returns null
     */
    public Figure move(Position p) {
        if (canMove(p) == true) {
            Figure thrownFig = this.killFigure(p);
            //System.out.println(thrownFig.getPosition().getColumn() + " " + thrownFig.getPosition().getRow());
            this.pos.removeFigure();
            p.putFigure(this);
            this.pos = p;
            return thrownFig;
        }
        return null;
    }

    /**
     * 
     * @return int - black = 0, white = 1
     */
    public int getColor() {
        return this.color;
    }

    /**
     *  abstract methods implemented in draft and pawn classes
     */
    
    public abstract boolean canMove(Position p);

    public abstract Figure killFigure(Position next);
    
    public abstract List<Position> getJump();
    
    public abstract List<Position> getMove();
    
    
}
