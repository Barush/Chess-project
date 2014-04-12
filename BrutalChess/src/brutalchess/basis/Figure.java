/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

/**
 *
 * @author Barush
 */
public class Figure {
    private Position pos;
    private int col;
    
    public Figure(Position pos, int col){
        this.pos = pos;
        this.col = col;
    }
    
    public void setPosition(Position pos){
        this.pos = pos;
    }
    
    public Position getPosition(){
        return this.pos;
    }
    
    public void setColor(int col){
        if((col > 1) || (col < 0)){
            //ERROR
        }
        this.col = col;
    }
    
    public int getColor(){
        return this.col;
    }
}
