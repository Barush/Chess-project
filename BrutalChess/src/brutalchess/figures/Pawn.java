/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package brutalchess.figures;

import brutalchess.basis.Figure;
import brutalchess.basis.Position;
import static brutalchess.Const.*;
import javafx.scene.paint.Color;
import javax.swing.*;

/**
 *
 * @author xskriv01
 */
public class Pawn extends Figure{
 
    public Pawn(Position p, int color){
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
        //normal moves (one or two forward)
        if(p.getCol() == this.pos.getCol()){
            //white figures go from top to bottom
            if(this.getColor() == WHITE){
                //normal moves
                if(p.getRow() == (this.pos.getRow() + 1)){
                    if(p.getFigure() == null){
                        return true;
                    }
                }
                //starting moves - two positions
                else if( (this.pos.getRow() == 2)  && (p.getRow() == (this.pos.getRow() + 2))){
                    if(p.getFigure() == null){
                        if(this.pos.getDesk().getFigureAt(this.pos.getCol(), this.pos.getRow()+1) == null){
                            return true;
                        }
                    }
                }
            }  
            //black figures go from bottom to top
            else if(this.getColor() == BLACK){
                //normal move
                if(p.getRow() == (this.pos.getRow() - 1)){
                    if(p.getFigure() == null){
                        return true;
                    }
                }
                //starting moves - two positions
                else if((this.pos.getRow() == 7)  && (p.getRow() == (this.pos.getRow() - 2))){
                    if(p.getFigure() == null){
                        if(this.pos.getDesk().getFigureAt(this.pos.getCol(), this.pos.getRow()- 1) == null){
                            return true;
                        }
                    }
                }
            }
        }
        //throwing moves
        else if((this.getColor() == WHITE) && (p.getRow() == (this.pos.getRow() + 1))){
            if((p.getCol() == (this.pos.getCol() + 1)) || (p.getCol() == (this.pos.getCol() - 1))){
                if((p.getFigure() != null) && (p.getFigure().getColor() != this.getColor())){
                    //opponents figure is there
                    return true;
                }
            }
        }
        else if((this.getColor() == BLACK) && (p.getRow() == (this.pos.getRow() - 1))){
            if((p.getCol() == (this.pos.getCol() + 1)) || (p.getCol() == (this.pos.getCol() - 1))){
                if((p.getFigure() != null) && (p.getFigure().getColor() != this.getColor())){
                    //opponents figure is there
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
                    return "/images/pawn_black_pre.png";
                } else {
                    return "/images/pawn_white_pre.png";
                }    
            }else if("dead".equals(state)){
                if(this.col == BLACK){
                    return "/images/pawn_black_pre_unm.png";
                }else{
                    return "/images/pawn_white_pre_unm.png";
                }
            }else {
                if (this.col == BLACK){
                    return "/images/pawn_black_unp.png";
                } else {
                    return "/images/pawn_white_unp.png";
                }            
            }
	}

    @Override
    public void markCanMovePositions(boolean mark) {
        boolean canMove = false;
        if(mark){
            //white move +1
            if((this.col == WHITE) && (this.canMove(this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() +1)))){
                canMove = true;
                this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() + 1).getTile().markTile();
            }
            //white move +2
            if((this.col == WHITE) && (this.pos.getRow() == 2) && (this.canMove(this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() + 2)))){
                canMove = true;
                this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() + 2).getTile().markTile();
            }
            //black move -1
            if((this.col == BLACK) && (this.canMove(this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() - 1)))){
                canMove = true;
                this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() - 1).getTile().markTile();
            }
            //black move -2
            if((this.col == BLACK) && (this.pos.getRow() == 7) && (this.canMove(this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() - 2)))){
                canMove = true;
                this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() - 2).getTile().markTile();
            }
            //white throwing move
            if((this.col == WHITE) && (this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() + 1)))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() + 1).getTile().markTile();
            }
            //white throwing move
            if((this.col == WHITE) && (this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() + 1)))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() + 1).getTile().markTile();
            }
            //black throwing move
            if((this.col == BLACK) && (this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() - 1)))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() - 1).getTile().markTile();
            }
            //black throwing move
            if((this.col == BLACK) && (this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() - 1)))){
                canMove = true;
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() - 1).getTile().markTile();
            }  
            if(!canMove){
                this.paintFigure("dead");
            }
        }
        else{
            if(this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() + 1) != null) 
                this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() + 1).getTile().repaintColor();
            if((this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() + 2) != null) && (this.pos.getRow() == 2)) 
                this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() + 2).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() - 1) != null) 
                this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() - 1).getTile().repaintColor();
            if(( this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() - 2) != null) && (this.pos.getRow() == 7)) 
                this.pos.getDesk().getPositionAt(this.pos.getCol(), this.pos.getRow() - 2).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() + 1) != null) 
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() + 1).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() + 1) != null) 
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() + 1).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() - 1) != null) 
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - 1), this.pos.getRow() - 1).getTile().repaintColor();
            if(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() - 1) != null) 
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + 1), this.pos.getRow() - 1).getTile().repaintColor();
        }
    }
}
