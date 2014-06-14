/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

import brutalchess.ui.Tile;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
/**
 *
 * @author Barush
 */
public class Position {
    private int row;
    private char col;
    private Figure fig;
	private final Tile tile;
    private final Desk desk;
    
    public Position(Desk d, char c, int r, int color){
        this.desk = d;
        this.col = c;
        this.row = r;
		this.tile = new Tile(color, this);
    }
    
    public void setRow(int r){
        this.row = r;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public void setCol(char c){
        this.col = c;
    }
    
    public char getCol(){
        return this.col;
    }
    
    public void setFigure(Figure fig, String state) {
        this.fig = fig;
        if(fig != null){
            System.out.println("setting figure to "+col+row);
            this.fig.paintFigure(state);
        }
    }
    
    public Figure getFigure(){
        return this.fig;
    }
    
    public Tile getTile(){
        return this.tile;
    }
    
    public Desk getDesk(){
        return this.desk;
    }
    
    public void moveActiveFigure(){
        Figure activeFig = this.getDesk().getActive().getFigure();
        //figure moving
        if(activeFig.canMove(this)){
            System.out.println("Can move from "+activeFig.getPosition().getCol()+activeFig.getPosition().getRow()+" to "+this.getCol()+this.getRow());
            //unmark the tile
            activeFig.getPosition().getTile().removeAll();
            this.getDesk().getActive().getTile().repaintColor();
            //set position of figure
            activeFig.setPosition(this);
            //set figure to position
            this.setFigure(activeFig, "inactive");
            //deactivate figure
            activeFig.Activate(false);
            //delete figure from previous position
            this.getDesk().getActive().setFigure(null, "");
            this.getDesk().setActive(null);
        } 
    }
}
