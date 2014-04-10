/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.logic;

import draft.basis.*;

/**
 * CLass report implements all methods necessary to work with report window in the GUI
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Report {

    private Position from;
    private Position to;
    private boolean jump = false; // default value
    private int color;
    private int turn;

    /**
     * Constructor of the class report
     * 
     * @param turn count of turn which is performed
     * @param whiteFrom starting position of a white figure
     * @param whiteTo end position of a white figure
     * @param whiteJump true if the move does some throwing out
     * @param color color of the figure reported
     */
    public Report(int turn, Position whiteFrom, Position whiteTo, boolean whiteJump, int color) {
        this.turn = turn;
        this.from = whiteFrom;
        this.to = whiteTo;
        this.jump = whiteJump;
        this.color = color;
    }

    /**
     * Unsupported constructor of the report object
     * 
     * @param turn 
     * @param tilePosition
     * @param tilePosition0
     * @param jump
     * @param i 
     */
    public Report(Turn turn, Position tilePosition, Position tilePosition0, boolean jump, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the from
     */
    public Position getFrom() {
        return from;
    }

    /**
     * @return the to
     */
    public Position getTo() {
        return to;
    }

    /**
     * @return the jump
     */
    public boolean isJump() {
        return jump;
    }

    /**
     * @return the color
     */
    public int getColor() {
        return color;
    }

    /**
     * @return the turn
     */
    public int getTurn() {
        return turn;
    }
    
    
}
