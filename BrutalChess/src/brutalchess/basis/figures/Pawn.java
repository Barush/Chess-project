/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brutalchess.basis.figures;

import brutalchess.basis.Figure;
import brutalchess.basis.Position;

/**
 *
 * @author Canes
 */
public class Pawn extends Figure{
	
	public Pawn(Position pos, int col){
		super(pos, col);
	}
	
	@Override
	public boolean canMove(Position p){
		return false;
	}

	@Override
	public String getPathToPic() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
