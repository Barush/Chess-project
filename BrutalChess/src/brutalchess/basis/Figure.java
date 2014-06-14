/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brutalchess.basis;

import brutalchess.ui.Tile;
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
    
    public Figure(Position pos, int col){
        this.pos = pos;
        this.col = col;
        this.active = false;
//		pos.setFigure(this);
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
	
	public void paintFigure(String state) {
		Tile tile = this.pos.getTile();
		BufferedImage image;
                
                System.out.println("Painting figure to "+this.getPosition().getCol()+this.getPosition().getRow());
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

}
