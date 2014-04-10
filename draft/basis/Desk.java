/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package draft.basis;

/**
 * Class Desk implements the basic facts about desk which is played on. 
 * 
 * @author xserec00@stud.fit.vutbr.cz
 *         xskriv01@stud.fit.vutbr.cz
 */

public class Desk {

    private int dim;
    private Position[][] desk;
    private Figure figure;

    /**
     * Constructor of the desk class
     * 
     * @param dim dimension of the desk - it is a square dim x dim tiles
     */
    public Desk(int dim) {
        this.dim = dim;
        this.desk = new Position[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                this.desk[i][j] = new Position(this, (char) ('a' + j), i + 1);
            }
        }
    }

    /**
     * 
     * @return  whole matrice of positions on the desk
     */
    public Position[][] getDeskPositions() {
        return this.desk;
    }

    /**
     * 
     * @param c column which the required position lies in
     * @param r row which the required position lies in
     * @return Position from the desk lying in required indexes or null if overflow
     */
    public Position getPositionAt(char c, int r) {
        if ((int) c >= (int) ('a' + this.dim) || (int) c < (int) ('a')) {
            return null;
        }
        if (r > this.dim || r < 1) {
            return null;
        }
        return this.desk[r - 1][(int) (c - 'a')];
    }

    /**
     * 
     * @param c column which the required figure lies in
     * @param r row which the required figure lies in
     * @return figure lying in the required position on null if none
     */
    public Figure getFigureAt(char c, int r) {
        if (this.getPositionAt(c, r) != null) {
            return this.getPositionAt(c, r).getFigure();
        }

        return null;

    }
}
