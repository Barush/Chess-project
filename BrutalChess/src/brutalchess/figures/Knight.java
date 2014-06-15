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
    public boolean isFigureBetween(Position p){
        return false;
    }
    
    
    @Override
    public boolean canMove(Position p) {
        if(p == null){
            return false;
        }
        if( ((abs(p.getCol() - this.getPosition().getCol()) == 2) && (abs(p.getRow() - this.getPosition().getRow()) == 1)) ||
            ((abs(p.getCol() - this.getPosition().getCol()) == 1) && (abs(p.getRow() - this.getPosition().getRow()) == 2)) ){
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
                    return "/images/knight_black_pre.png";
                } else {
                    return "/images/knight_white_pre.png";
                }    
            }else if("dead".equals(state)){
                if (this.col == BLACK){
                    return "/images/knight_black_pre_unm.png";
                } else {
                    return "/images/knight_white_pre_unm.png";
                }             
            } else {
                if (this.col == BLACK){
                    return "/images/knight_black_unp.png";
                } else {
                    return "/images/knight_white_unp.png";
                }            
            }
	}

    @Override
    public void markCanMovePositions(boolean mark) {
        boolean canMove = false;
        if(mark){
            if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() + 2))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() + 2).getTile().markTile();
            }
            if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() + 2))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() + 2).getTile().markTile();
            }
            if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() - 2))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() - 2).getTile().markTile();
            }
            if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() - 2))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() - 2).getTile().markTile();
            }
            if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 2), this.pos.getRow() + 1))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 2), this.pos.getRow() + 1).getTile().markTile();
            }
            if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 2), this.pos.getRow() + 1))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 2), this.pos.getRow() + 1).getTile().markTile();
            }
            if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 2), this.pos.getRow() - 1))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 2), this.pos.getRow() - 1).getTile().markTile();
            }
            if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 2), this.pos.getRow() - 1))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 2), this.pos.getRow() - 1).getTile().markTile();
            }
            if(!canMove){
                this.paintFigure("dead");
            }
        }else{
            if((this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() + 2)) != null)
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() + 2).getTile().repaintColor();
            if((this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() + 2)) != null)
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() + 2).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() - 2) != null)
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() - 2).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() - 2) != null)
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() - 2).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 2), this.pos.getRow() + 1) != null)
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 2), this.pos.getRow() + 1).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 2), this.pos.getRow() + 1) != null)
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 2), this.pos.getRow() + 1).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 2), this.pos.getRow() - 1) != null)
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 2), this.pos.getRow() - 1).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 2), this.pos.getRow() - 1) != null)
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 2), this.pos.getRow() - 1).getTile().repaintColor();
        }
    }
}
