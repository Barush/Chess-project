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
public class King extends Figure{
 
    public King(Position p, int color){
       super(p, color);
    }

    @Override
    public boolean canMove(Position p) {
       if((abs(this.getPosition().getCol() - p.getCol()) <= 1) && (abs(this.getPosition().getRow() - p.getRow()) <= 1)){
           return true;
       }
       return false;
    }

	@Override
	public String getPathToPic() {
            if (this.col == BLACK){
                return "/images/king_black_unp.png";
            } else {
                return "/images/king_white_unp.png";
            }
	}
}

