package brutalchess.basis;

import static brutalchess.Const.*;
import brutalchess.basis.figures.*;
import java.io.IOException;

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
				int color = ((i + j) % 2) == 0 ? WHITE : BLACK;
                this.desk[i][j] = new Position(this, (char) ('a' + j), i + 1, color);
            }
        }
		
		this.initFigures();
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
	
	final public void initFigures() {
		
		Position tempP;
		Figure tempF;
		
		// Bishops: B {C8 F8} W {C1 F1}
		tempP = getPositionAt('c', 8);
		tempF = new Bishop(tempP, BLACK );
		tempP.setFigure(tempF);
		tempP = getPositionAt('f', 8);
		tempF = new Bishop(tempP, BLACK );
		tempP.setFigure(tempF);
		tempP = getPositionAt('c', 1);
		tempF = new Bishop(tempP, WHITE );
		tempP.setFigure(tempF);
		tempP = getPositionAt('f', 1);
		tempF = new Bishop(tempP, WHITE );
		tempP.setFigure(tempF);
		
		// Bishops: B {C8 F8} W {C1 F1}
		
		
//		for (int i = 0; i < dim; i++) {
//            for (int j = 0; j < dim; j++) {
//				int color = ((i + j) % 2) == 0 ? WHITE : BLACK;
//                this.desk[i][j] = new Position(this, (char) ('a' + j), i + 1, color);
//            }
//        }
	}
}
