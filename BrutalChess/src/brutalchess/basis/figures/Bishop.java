/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brutalchess.basis.figures;

import static brutalchess.Const.*;
import brutalchess.basis.Figure;
import brutalchess.basis.Position;

/**
 *
 * @author Canes
 */
public class Bishop extends Figure{
	
	public Bishop(Position pos, int col){
		super(pos, col);
	}
	
	@Override
	public boolean canMove(Position p){
		return false;
	}

	@Override
	public String getPathToPic() {
		if (this.col == BLACK){
			return "/images/bishop_black.png";
		} else {
			return "/images/bishop_white.png";
		}
	}

}
