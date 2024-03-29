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
public class Bishop extends Figure{
 
    public Bishop(Position p, int color){
       super(p, color);
    }

    @Override
    public boolean isFigureBetween(Position newPos){   
        int lenght = Math.abs(this.pos.getCol() - newPos.getCol());
        for(int i = 1; i < lenght; i++){
            if(this.pos.getCol() < newPos.getCol()){
                //-->
                if(this.pos.getRow() < newPos.getRow()){
                    //--> vv
                    if(this.pos.getDesk().getFigureAt((char) (this.pos.getCol()+i), this.pos.getRow() + i) != null){
                        System.out.println("Position "+(char)(this.pos.getCol()+i)+(this.pos.getRow() + i)+" is not clear.");
                        return true;
                    }
                }else{
                    //--> ^^
                    if(this.pos.getDesk().getFigureAt((char) (this.pos.getCol()+i), this.pos.getRow() - i) != null){
                        System.out.println("Position "+(char)(this.pos.getCol()+i)+(this.pos.getRow() - i)+" is not clear.");
                        return true;
                    }
                }
            }else{
                //<--
                if(this.pos.getRow() < newPos.getRow()){
                    //--> vv
                    if(this.pos.getDesk().getFigureAt((char) (this.pos.getCol()-i), this.pos.getRow() + i) != null){
                        System.out.println("Position "+(char)(this.pos.getCol()-i)+(this.pos.getRow() + i)+" is not clear.");
                        return true;
                    }
                }else{
                    //--> ^^
                    if(this.pos.getDesk().getFigureAt((char) (this.pos.getCol()-i), this.pos.getRow() - i) != null){
                        System.out.println("Position "+(char)(this.pos.getCol()-i)+(this.pos.getRow() - i)+" is not clear.");
                        return true;
                    }
                }           
            }
        }
        return false;
    }
    
    @Override
    public boolean canMove(Position p) {
        if(abs(this.getPosition().getCol() - p.getCol()) == abs(this.getPosition().getRow() - p.getRow())){
            if(!this.isFigureBetween(p)){
                if((p.getFigure() == null) || (p.getFigure().getColor() != this.getColor())){
                    if(!this.isFigureBetween(p)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

	@Override
	public String getPathToPic(String state) {
            if("active".equals(state)){
                if (this.col == BLACK){
                    return "/images/bishop_black_pre.png";
                } else {
                    return "/images/bishop_white_pre.png";
                }    
            }else if("dead".equals(state)){
                if (this.col == BLACK){
                    return "/images/bishop_black_pre_unm.png";
                } else {
                    return "/images/bishop_white_pre_unm.png";
                } 
            } else {
                if (this.col == BLACK){
                    return "/images/bishop_black_unp.png";
                } else {
                    return "/images/bishop_white_unp.png";
                }            
            }
	}

    @Override
    public void markCanMovePositions(boolean mark) {
        boolean canMove = false;
        int i = 1;
        if(mark){
            //direction v-->
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() + i) != null){
                if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() + i))){
                   canMove = true;
                   this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() + i).getTile().markTile();
                }
                i++;
            }
            //direction v <--
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() + i) != null){
                if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() + i))){
                   canMove = true;
                   this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() + i).getTile().markTile();
                }
                i++;
            }
            //direction ^-->
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() - i) != null){
                if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() - i))){
                   canMove = true;
                   this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() - i).getTile().markTile();
                }
                i++;
            }
            //direction ^ <--
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() - i) != null){
                if(this.canMove(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() - i))){
                   canMove = true;
                   this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() - i).getTile().markTile();
                }
                i++;
            }
            
            if(!canMove){
                this.paintFigure("dead");
            }
        }else{
            //direction v-->
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() + i) != null){
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() + i).getTile().repaintColor();
                i++;
            }
            //direction v <--
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() + i) != null){
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() + i).getTile().repaintColor();
                i++;
            }
            //direction ^-->
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() - i) != null){
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() + i), this.pos.getRow() - i).getTile().repaintColor();
                i++;
            }
            //direction ^ <--
            i = 1;
            while(this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() - i) != null){
                this.pos.getDesk().getPositionAt((char) (this.pos.getCol() - i), this.pos.getRow() - i).getTile().repaintColor();
                i++;
            }
        }

    }
}

