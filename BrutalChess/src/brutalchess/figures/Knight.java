/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brutalchess.figures;

import static brutalchess.Const.*;
import brutalchess.basis.Figure;
import brutalchess.basis.Position;
import static java.lang.Math.abs;

/**
 *
 * @author xskriv01
 */
public class Knight extends Figure{
 
    public Knight(Position p, int color){
       super(p, color);
    }

    @Override
    public boolean canMove(Position p) {
        if( ((abs(p.getCol() - this.getPosition().getCol()) == 2) && (abs(p.getRow() - this.getPosition().getRow()) == 1)) ||
            ((abs(p.getCol() - this.getPosition().getCol()) == 1) && (abs(p.getRow() - this.getPosition().getRow()) == 2)) ){
            return true;
        }
        return false;
    }

	@Override
	public String getPathToPic() {
            if (this.col == BLACK){
		return "/images/knight_black_unp.png";
            } else {
		return "/images/knight_white_unp.png";
            }
	}
}
