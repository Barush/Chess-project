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
public class Rook extends Figure{
 
    public Rook(Position p, int color){
       super(p, color);
    }

    @Override
    public boolean canMove(Position p) {
        if( (this.getPosition().getCol() == p.getCol()) || (this.getPosition().getRow() == p.getRow()) ){
            return true;
        }
        return false;
    }
}
