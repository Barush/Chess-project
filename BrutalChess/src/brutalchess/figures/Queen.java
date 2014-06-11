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
public class Queen extends Figure{
 
    public Queen(Position p, int color){
       super(p, color);
    }

    @Override
    public boolean canMove(Position p) {
       //rook style moves
       if((this.getPosition().getCol() == p.getCol()) || (this.getPosition().getRow() == p.getRow())){
           return true;
       }
       //bishop style moves
       else if(abs(this.getPosition().getCol() - p.getCol()) == abs(this.getPosition().getRow() - p.getRow())){
           return true;
       }
       return false;
    }

	@Override
	public String getPathToPic() {
            if (this.col == BLACK){
                return "/images/queen_black_unp.png";
            } else {
                return "/images/queen_white_unp.png";
            }
	}
}

