/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

import brutalchess.ui.Tile;
/**
 *
 * @author Barush
 */
public class Position {
    private int row;
    private char col;
    private Figure fig;
	private Tile tile;
    private final Desk desk;
    
    public Position(Desk d, char c, int r, int color){
        this.desk = d;
        this.col = c;
        this.row = r;
		this.tile = new Tile(color);
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
    
    public void setFigure(Figure fig){
        this.fig = fig;
    }
    
    public Figure getFigure(){
        return this.fig;
    }
}
