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
    public boolean isFigureBetween(Position newPos){
        if(this.pos.getCol() == newPos.getCol()){
            //check rows
            int from, to;
            if(this.pos.getRow() < newPos.getRow()){
                from = this.pos.getRow();
                to = newPos.getRow();
            }
            else{
                from = newPos.getRow();
                to = this.pos.getRow();
            }
            for(int i = (from + 1); i < to; i++){
                if(this.pos.getDesk().getFigureAt(this.pos.getCol(), i+1) != null){
                    System.out.println("Position "+this.pos.getCol()+(i+1)+" is not clear.");
                    return true;
                }
            }
        }
        else{
            //check columns
            char from, to;
            if(this.pos.getCol() < newPos.getCol()){
                from = this.pos.getCol();
                to = newPos.getCol();
            }
            else{
                from = newPos.getCol();
                to = this.pos.getCol();
            }
            for(char i = (char) (from + 1); i < to; i++){
                if(this.pos.getDesk().getFigureAt(i, this.pos.getRow() + 1) != null){
                    System.out.println("Position "+i+(this.pos.getRow()+1)+" is not clear.");
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public boolean canMove(Position p) {
        if( (this.getPosition().getCol() == p.getCol()) || (this.getPosition().getRow() == p.getRow()) ){
            if((p.getFigure() == null) || (p.getFigure().getColor() != this.getColor())){
                if(!isFigureBetween(p)){
                    return true;
                }
            }
        }
        return false;
    }

	@Override
	public String getPathToPic(String state) {
            if("active".equals(state)){
                if (this.col == BLACK){
                    return "/images/rook_black_pre.png";
                } else {
                    return "/images/rook_white_pre.png";
                }    
            }
            else {
                if (this.col == BLACK){
                    return "/images/rook_black_unp.png";
                } else {
                    return "/images/rook_white_unp.png";
                }            
            }
	}
}
