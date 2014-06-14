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
    public boolean isFigureBetween(Position p){
        return false;
    }
    
    @Override
    public boolean canMove(Position p) {
       if((abs(this.getPosition().getCol() - p.getCol()) <= 1) && (abs(this.getPosition().getRow() - p.getRow()) <= 1)){
            if((p.getFigure() == null) || (p.getFigure().getColor() != this.getColor())){
                return true;
            }
       }
       return false;
    }

	@Override
	public String getPathToPic(String state) {
            if("active".equals(state)){
                if (this.col == BLACK){
                    return "/images/king_black_pre.png";
                } else {
                    return "/images/king_white_pre.png";
                }    
            }
            else {
                if (this.col == BLACK){
                    return "/images/king_black_unp.png";
                } else {
                    return "/images/king_white_unp.png";
                }            
            }
	}
}

