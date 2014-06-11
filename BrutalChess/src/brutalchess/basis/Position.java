/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

import brutalchess.ui.Tile;
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
    
    public void setFigure(Figure fig) {
        this.fig = fig;
		this.fig.paintFigure();
    }
    
    public Figure getFigure(){
        return this.fig;
    }
    
    public Tile getTile(){
        return this.tile;
    }
    
    public Position nextPosition(Desk desk, int dC, int dR){
        char nextCol;
        int nextRow;
        int color = 0;
        
        nextCol = (char)(this.col + dC);
        nextRow = this.row + dR;
        
        Position nextPos = new Position(desk, nextCol, nextRow, color);
        
        return nextPos;
    }
}
