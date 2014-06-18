/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

import static brutalchess.Const.*;
import brutalchess.ui.Tile;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Barush
 */
public abstract class Figure {
    protected Position pos;
    protected int col; // color
    protected boolean active;
	
	public abstract boolean canMove(Position p);
	public abstract String getPathToPic(String state);
	public abstract boolean isFigureBetween(Position p);
	public abstract void markCanMovePositions(boolean mark);
    
    public Figure(Position pos, int col){
        this.pos = pos;
        this.col = col;
        this.active = false;        
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
    
    public boolean isActive(){
        if(this.active){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void Activate(boolean state){
        this.active = state;
    }
    
    public void markFigure(){
		if (!this.pos.getDesk().getGame().canPlayWithColor(this.col)){
			dbg("Can't play with this figure!");
			return;
		}
        if(this.active){
            //inactivate
            this.pos.setFigure(this, "inactive");
            this.active = false;
            this.pos.getDesk().setActive(null);
            this.markCanMovePositions(false);
        }
        else if(this.pos.getDesk().getActive() == null){
            //activate
            this.pos.setFigure(this, "active");
            this.active = true;
            this.pos.getDesk().setActive(this.pos);
            this.markCanMovePositions(true);
        }
    }
	
    public void paintFigure(String state) {
            Tile tile = this.pos.getTile();
            BufferedImage image;

            try {
                    image = ImageIO.read(getClass().getResource( this.getPathToPic(state) ));
            } catch (IOException ex) {
                    Logger.getLogger(Figure.class.getName()).log(Level.SEVERE, null, ex);
                    return;
            }
            Image dimg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon( dimg );
            JLabel picLabel = new JLabel( imgIcon );
            tile.removeAll();
            tile.add(picLabel);
            tile.revalidate();
    }
	
	public void deathCallback() {}
        
}
