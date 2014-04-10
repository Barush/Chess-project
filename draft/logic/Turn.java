/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.logic;


import draft.basis.*;
/**
 * CLass turn is a data class to store one turn
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Turn {
    private Position positionFrom;
    private Position positionTo;
    
    /**
     * Constructor of the turn object
     * 
     * @param positionFrom starting position of the turn
     * @param positionTo end position of the turn
     */
    public Turn(Position positionFrom, Position positionTo) {
        this.positionFrom = positionFrom;
        this.positionTo = positionTo;
    }

    /**
     * @return the positionFrom
     */
    public Position getPositionFrom() {
        return positionFrom;
    }

    /**
     * @return the positionTo
     */
    public Position getPositionTo() {
        return positionTo;
    }
    
}
