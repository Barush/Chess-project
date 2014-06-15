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
                if(this.pos.getDesk().getFigureAt(this.pos.getCol(), i) != null){
                    System.out.println("Position "+this.pos.getCol()+i+" is not clear.");
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
                if(this.pos.getDesk().getFigureAt(i, this.pos.getRow()) != null){
                    System.out.println("Position "+i+(this.pos.getRow())+" is not clear.");
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
            }else if(state == "dead"){
                if (this.col == BLACK){
                    return "/images/rook_black_pre_unm.png";
                } else {
                    return "/images/rook_white_pre_unm.png";
                }                 
            } else {
                if (this.col == BLACK){
                    return "/images/rook_black_unp.png";
                } else {
                    return "/images/rook_white_unp.png";
                }            
            }
	}

    @Override
    public void markCanMovePositions(boolean mark) {
        boolean canMove = false;
        int i = 1;
        
        if(mark){
            //direction v
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() + i) != null){
                if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() + i))){
                    canMove = true;
                    this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() + i).getTile().markTile();
                }
                i++;
            }
            //direction ^
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() - i) != null){
                if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() - i))){
                    canMove = true;
                    this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() - i).getTile().markTile();
                }
                i++;
            }
            //direction <--
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow()) != null){
                if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow()))){
                    canMove = true;
                    this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow()).getTile().markTile();
                }
                i++;
            }
            //direction -->
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow()) != null){
                if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow()))){
                    canMove = true;
                    this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow()).getTile().markTile();
                }
                i++;
            }
            
            if(!canMove){
                this.paintFigure("dead");
            }
        }else{
            //direction v
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() + i) != null){
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() + i).getTile().repaintColor();
                i++;
            }
            //direction ^
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() - i) != null){
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol()), this.pos.getRow() - i).getTile().repaintColor();
                i++;
            }
            //direction <--
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow()) != null){
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow()).getTile().repaintColor();
                i++;
            }
            //direction -->
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow()) != null){
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow()).getTile().repaintColor();
                i++;
            }        
        }
    }
}
