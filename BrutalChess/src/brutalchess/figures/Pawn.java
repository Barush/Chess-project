/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brutalchess.figures;

import brutalchess.basis.Figure;
import brutalchess.basis.Position;
import static brutalchess.Const.*;

/**
 *
 * @author xskriv01
 */
public class Pawn extends Figure{
 
    public Pawn(Position p, int color){
       super(p, color);
    }

    @Override
    public boolean canMove(Position p) {
        //normal moves (one or two forward)
        if(p.getCol() == this.getPosition().getCol()){
            //white figures go from bottom to top
            if((this.getColor() == WHITE) && (p.getRow() >= (this.getPosition().getRow() + 1)) && (p.getRow() <= (this.getPosition().getRow() + 2))){
                return true;
            }
            //black figures go from top to bottom
            else if((this.getColor() == BLACK) && (p.getRow() >= (p.getRow() - 1)) && (p.getRow() <= (p.getRow() - 2))){
                return true;
            }
        }
        //throwing moves
        else if((this.getColor() == WHITE) && (p.getRow() == (this.getPosition().getRow() + 1))){
            if((p.getCol() == (this.getPosition().getCol() + 1)) && (p.getCol() == (this.getPosition().getCol() - 1))){
                //throw figure()
                return true;
            }
        }
        else if((this.getColor() == BLACK) && (p.getRow() == (this.getPosition().getRow() - 1))){
            if((p.getCol() == (this.getPosition().getCol() + 1)) && (p.getCol() == (this.getPosition().getCol() - 1))){
                //throw figure()
                return true;
            }
        }
        return false;
    }
}
