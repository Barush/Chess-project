package brutalchess.basis;

import static brutalchess.Const.*;

public class Desk {

    private final int dim;
    private final Position[][] desk;

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
                this.desk[i][j] = new Position(this, (char) ('a' + j), i + 1, WHITE);
            }
        }
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
	
	public Position[][] getPositions() {
        return this.desk;
    }
}
