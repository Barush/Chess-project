package brutalchess.basis;

import static brutalchess.Const.*;
import brutalchess.figures.*;
import java.io.IOException;

public class Desk {

    private final int dim;
    private final Position[][] desk;
    private Position active;
    private Game game;

    /**
     * Constructor of the desk class
     * 
     * @param dim dimension of the desk - it is a square dim x dim tiles
     */
    public Desk(int dim, Game game) {
		this.game = game;
        this.dim = dim;
        this.desk = new Position[dim][dim];
		
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
				int color = ((i + j) % 2) == 0 ? BLACK : WHITE;
                this.desk[i][j] = new Position(this, (char) ('a' + j), i, color);
            }
        }
        this.active = null;
		
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
        
    public Position getActive(){
        return this.active;
    }
        
    public Game getGame(){
        return this.game;
	}
    
    public void setActive(Position pos){
        this.active = pos;
    }
    
	public void createFigure(Position pos, String type, int color){
            Figure tempF = null;
            
            switch(type){
                case "Bishop": tempF = new Bishop(pos, color);
                            break;
                case "King": tempF = new King(pos, color);
                            break;
                case "Knight": tempF = new Knight(pos, color);
                            break;
                case "Pawn": tempF = new Pawn(pos, color);
                            break;
                case "Queen": tempF = new Queen(pos, color);
                            break;
                case "Rook": tempF = new Rook(pos, color);
                            break;
            }
                       
            pos.setFigure(tempF, "inactive");
            tempF.setPosition(pos);
            
        }
        
	final public void initFigures() {		
		Position tempP;
		Figure tempF;
                
                //Pawns: B {2ABCDEFGH} W {7ABCDEFGH}
		createFigure(getPositionAt('a', 2), "Pawn", WHITE);
		createFigure(getPositionAt('b', 2), "Pawn", WHITE);
		createFigure(getPositionAt('c', 2), "Pawn", WHITE);
		createFigure(getPositionAt('d', 2), "Pawn", WHITE);
		createFigure(getPositionAt('e', 2), "Pawn", WHITE);
		createFigure(getPositionAt('f', 2), "Pawn", WHITE);
		createFigure(getPositionAt('g', 2), "Pawn", WHITE);
		createFigure(getPositionAt('h', 2), "Pawn", WHITE);
                
		createFigure(getPositionAt('a', 7), "Pawn", BLACK);
		createFigure(getPositionAt('b', 7), "Pawn", BLACK);
		createFigure(getPositionAt('c', 7), "Pawn", BLACK);
		createFigure(getPositionAt('d', 7), "Pawn", BLACK);
		createFigure(getPositionAt('e', 7), "Pawn", BLACK);
		createFigure(getPositionAt('f', 7), "Pawn", BLACK);
		createFigure(getPositionAt('g', 7), "Pawn", BLACK);
		createFigure(getPositionAt('h', 7), "Pawn", BLACK);
                
		// Bishops: B {C8 F8} W {C1 F1}
		createFigure(getPositionAt('c', 8), "Bishop", BLACK);
		createFigure(getPositionAt('f', 8), "Bishop", BLACK);
                
		createFigure(getPositionAt('c', 1), "Bishop", WHITE);
		createFigure(getPositionAt('f', 1), "Bishop", WHITE);
		
                //Kings: W {E1}  B {E8}
		createFigure(getPositionAt('e', 8), "King", BLACK);
		createFigure(getPositionAt('e', 1), "King", WHITE);
                
                //Queens: W {D1} B {D8}
		createFigure(getPositionAt('d', 1), "Queen", WHITE);
		createFigure(getPositionAt('d', 8), "Queen", BLACK);
                
                //Knights: W {B1 G1} B {B8 G8}
		createFigure(getPositionAt('b', 1), "Knight", WHITE);
		createFigure(getPositionAt('g', 1), "Knight", WHITE);
		createFigure(getPositionAt('b', 8), "Knight", BLACK);
		createFigure(getPositionAt('g', 8), "Knight", BLACK);
                
               //Rooks: W {A1 H1} B {A8 B8}
		createFigure(getPositionAt('a', 1), "Rook", WHITE);
		createFigure(getPositionAt('h', 1), "Rook", WHITE);
		createFigure(getPositionAt('a', 8), "Rook", BLACK);
		createFigure(getPositionAt('h', 8), "Rook", BLACK);
	}
}
