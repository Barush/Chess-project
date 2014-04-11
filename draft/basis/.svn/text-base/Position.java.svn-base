/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.basis;

/**
 * Class position implements position on the desk
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */
public class Position {

    private int row;
    private char column;
    private Desk d;
    private Figure figure = this.getFigure();

    /**
     * Constructor of the position object
     * 
     * @param d desk which teh position should lie on
     * @param c column which the position should lie in
     * @param r row which the position should lie in
     */
    public Position(Desk d, char c, int r) {
        this.d = d;
        this.column = c;
        this.row = r;
    }

    /**
     * 
     * @param p position with which this position should be samerowed
     * @return true if this and p are in the same row, false otherwise
     */
    public boolean sameRow(Position p) {
        if (p.row == row) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param p position which this position should be samecolumned
     * @return true if this and p are in the same column, false otherwise
     */
    public boolean sameColumn(Position p) {
        if (p.column == column) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @return row which this position lies in
     */
    public int getRow() {
        return this.row;
    }
    
    /**
     * 
     * @return column which this position lies in
     */
    public char getColumn() {
        return this.column;
    }

    /**
     * 
     * @return desk which this position lies in
     */
    public Desk getDesk() {
        return this.d;
    }

    /**
     * 
     * @return a figure standing on this position
     */
    public Figure getFigure() {
        return this.figure;
    }

    /*
     * Puts a figure to this position
     * 
     * @return allways null
     */
    public Figure putFigure(Figure f) {
        //if (figure == null) {
        this.figure = f;
        return null;
        //}
        //Figure pom = figure;
        //this.figure = f;
        //return pom;
    }

    /**
     * 
     * @return removed figure from this position or null if none
     */
    public Figure removeFigure() {
        if (figure != null) {
            Figure pom = figure;
            figure = null;
            return pom;
        }
        return null;
    }

    /**
     * increments position 
     * @param dC column difference between this and new position
     * @param dR row difference between this and new position
     * @return new position after applying the differences
     */
    public Position nextPosition(int dC, int dR) {
        return this.getDesk().getPositionAt((char)(column + dC), row + dR);
    }

    /**
     * 
     * @param p position which should be checked
     * @return true if p lies on diagonal with this position, false otherwise
     */
    public boolean isOnDiagonal(Position p) {
        int rowDif, colDif;
        rowDif = Math.abs(this.getRow() - p.getRow());
        colDif = Math.abs((int) (this.getColumn() - 'a') - (int) (p.getColumn() - 'a'));
        if (rowDif == colDif) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position) {
            Position p = (Position) o;
            if (this.column == p.column && this.row == p.row) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.row;
        hash = 53 * hash + this.column;
        return hash;
    }
}
